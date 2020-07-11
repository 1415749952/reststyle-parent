package cn.ccsu.web.config;

import cn.ccsu.web.security.SysUserAuthenticationEntryPoint;
import cn.ccsu.web.security.SysUserPermissionEvaluator;
import cn.ccsu.web.security.filter.CustomAuthenticationFilter;
import cn.ccsu.web.security.filter.ValidateCodeFilter;
import cn.ccsu.web.security.handler.RestAuthAccessDeniedHandler;
import cn.ccsu.web.security.handler.RestAuthFailureHandler;
import cn.ccsu.web.security.handler.RestAuthSuccessHandler;
import cn.ccsu.web.security.handler.RestLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * Description:spring Security配置安全控制中心
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-01-30
 * @Time: 13:09
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启方法授权
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    /**
     * 依赖注入自定义的登录成功处理器
     */
    @Autowired
    private RestAuthSuccessHandler restAuthSuccessHandler;
    /**
     * 依赖注入自定义的登录失败处理器
     */
    @Autowired
    private RestAuthFailureHandler restAuthFailureHandler;
    /**
     * 依赖注入自定义的注销成功的处理器
     */
    @Autowired
    private RestLogoutSuccessHandler restLogoutSuccessHandler;
    /**
     * 依赖注入没有权限的处理器
     */
    @Autowired
    private RestAuthAccessDeniedHandler restAuthAccessDeniedHandler;

    /**
     * 依赖注入我们自己的登录逻辑验证器AuthenticationProvider
     */
    @Autowired
    private AuthenticationProvider authenticationProvider;
    /**
     * 依赖注入处理没有登陆处理器AuthenticationEntryPoint
     */
    @Autowired
    private SysUserAuthenticationEntryPoint sysUserAuthenticationEntryPoint;

    @Autowired
    private SysUserPermissionEvaluator sysUserPermissionEvaluator;

    /**
     * 注入自定义验证码filter
     */
    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    /**
     * 注入数据源
     */
    @Autowired
    private DataSource dataSource;

    /**
     * 注入UserDetailsService，rember-me 要用的
     *
     * @Qualifier 注解是多个类实现一个接口，注入接口时要标注注入那个实现类
     */
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 注入自定义的SysUserPermissionEvaluator
     */
/*
    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler()
    {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(sysUserPermissionEvaluator);
        return handler;
    }
*/

    /**
     * 注册自定义的UsernamePasswordAuthenticationFilter(采用json格式登录)
     */
    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception
    {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(restAuthSuccessHandler);
        filter.setAuthenticationFailureHandler(restAuthFailureHandler);
        filter.setFilterProcessesUrl("/login");
        //这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    /**
     * 持久化token
     * <p>
     * Security中，默认是使用PersistentTokenRepository的子类InMemoryTokenRepositoryImpl，将token放在内存中
     * 如果使用JdbcTokenRepositoryImpl，会创建表persistent_logins，将token持久化到数据库
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository()
    {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 启动创建表，创建成功后注释掉
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    /**
     * 启用我们自己的登陆验证逻辑
     *
     * @param auth
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
    {
        //这里可启用我们自己的登陆验证逻辑
        auth.authenticationProvider(authenticationProvider);
    }

    /**
     * 配置spring security的控制逻辑
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                // 配置验证码过滤器，使之在用户名密码过滤器之前生效
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                //"/login"登陆和"/code"生成验证码，不进行权限验证
                .antMatchers("/login", "/code").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .anyRequest().authenticated()   //其他的需要登陆后才能访问
                .and()
                .formLogin()
                //loginProcessingUrl用于指定前后端分离的时候调用后台登录接口的名称
                .loginProcessingUrl("/login")
                //配置登录成功的自定义处理类
                .successHandler(restAuthSuccessHandler)
                //配置登录失败的自定义处理类
                .failureHandler(restAuthFailureHandler)
                .and()
                //记住我功能
                .rememberMe()
                //TokenRepository，登录成功后往数据库存token的
                .tokenRepository(persistentTokenRepository())
                //记住我的时间(秒)
                .tokenValiditySeconds(60 * 60)
                .userDetailsService(userDetailsService)
                .and()
                //loginProcessingUrl用于指定前后端分离的时候调用后台注销接口的名称
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(restLogoutSuccessHandler)
                .and()
                //配置没有权限的自定义处理类
                .exceptionHandling()
                .accessDeniedHandler(restAuthAccessDeniedHandler)
                .and()
                //新加入
                .cors()
                .and()
                // 取消跨站请求伪造防护
                .csrf()
                .disable()
                //配置没有登陆时处理类
                .exceptionHandling()
                .authenticationEntryPoint(sysUserAuthenticationEntryPoint)
        ;
        //以下这句就可以控制单个用户只能创建一个session，也就只能在服务器登录一次
        //http.sessionManagement().maximumSessions(1).expiredUrl("/login");
    }
}
