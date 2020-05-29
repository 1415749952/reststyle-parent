package cn.ccsu.security.filter;

import cn.ccsu.security.MyHttpServletRequestWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:用户名密码通过JSON的方式进行传递，则需要自定义相关过滤器，
 * 通过分析源码我们发现，默认的用户名密码提取在UsernamePasswordAuthenticationFilter过滤器
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-03-19
 * @Time: 14:36
 */


//@Component
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException
    {
        //复制request流
        MyHttpServletRequestWrapper requestWrapper = new MyHttpServletRequestWrapper(request);
        if (!request.getMethod().equals("POST"))
        {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        ObjectMapper mapper = new ObjectMapper();
        String username = null;
        String password = null;
        //如果以json的形式传递参数
        if (StringUtils.isNotEmpty(requestWrapper.getContentType()) && requestWrapper.getContentType().equals(MediaType.APPLICATION_JSON_VALUE))
        {
            try (InputStream is = requestWrapper.getInputStream())
            {
                Map<String, String> authenticationBean = mapper.readValue(is, Map.class);
                username = authenticationBean.get("username");
                password = authenticationBean.get("password");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            if (username == null)
            {
                username = "";
            }

            if (password == null)
            {
                password = "";
            }

            username = username.trim();

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            setDetails(requestWrapper, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
        //如果不是以json的形式传递参数
        else
        {
            return super.attemptAuthentication(requestWrapper, response);
        }
    }
}
