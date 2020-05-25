package cn.ccsu.security.filter;

import cn.ccsu.security.MyHttpServletRequestWrapper;
import cn.ccsu.commom.exception.ImageCodeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 自定义图形验证码的过滤器
 * SpringSecurity是通过过滤器链来进行校验的，我们想要验证图形验证码，
 * 所以可以在认证流程之前，也就是UsernamePasswordAuthenticationFilter之前进行校验
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-02-09
 * @Time: 11:06
 */


@Component
public class ValidateCodeFilter extends OncePerRequestFilter
{
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        //复制request流
        MyHttpServletRequestWrapper requestWrapper = new MyHttpServletRequestWrapper(request);


        //判断请求是否为登陆请求
        if (StringUtils.equalsIgnoreCase(StringUtils.substringAfterLast(requestWrapper.getRequestURI(), "/"), "login") && StringUtils.equalsIgnoreCase(requestWrapper.getMethod(), "post"))
        {
            //用户输入验证码
            String userInputCode = "";
            //如果以json的形式传递参数
            if (StringUtils.isNotEmpty(request.getContentType()) && request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE))
            {
                ObjectMapper mapper = new ObjectMapper();
                try (InputStream is = requestWrapper.getInputStream())
                {
                    Map<String, String> authenticationBean = mapper.readValue(is, Map.class);
                    userInputCode = authenticationBean.get("imageCode");
                }
            }
            else
            {
                userInputCode = request.getParameter("imageCode");
            }
            try
            {
                //如果用户输入验证码为空
                if (StringUtils.isEmpty(userInputCode))
                {
                    throw new ImageCodeException("验证码必须输入");
                }
                //系统生成的验证码
                String vrifyCode = (String) request.getSession().getAttribute("vrifyCode");

                if (StringUtils.isEmpty(vrifyCode))
                {
                    throw new ImageCodeException("验证码不存在");
                }
                if (!StringUtils.equals(userInputCode.trim(), vrifyCode))
                {
                    throw new ImageCodeException("验证码不一致");
                }
            }
            catch (AuthenticationException exception)
            {
                authenticationFailureHandler.onAuthenticationFailure(requestWrapper, response, exception);
                return;
            }
        }
        //满足上诉条件就放行
        if (requestWrapper == null)
        {
            filterChain.doFilter(request, response);
        }
        else
        {
            filterChain.doFilter(requestWrapper, response);
        }
    }
}
