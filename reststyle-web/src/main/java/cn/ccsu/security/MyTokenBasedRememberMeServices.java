package cn.ccsu.security;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-03-19
 * @Time: 20:06
 */
public class MyTokenBasedRememberMeServices extends TokenBasedRememberMeServices
{
    private boolean alwaysRemember;

    @Override
    public void setAlwaysRemember(boolean alwaysRemember)
    {
        this.alwaysRemember = alwaysRemember;
    }

    public MyTokenBasedRememberMeServices(String key, UserDetailsService userDetailsService)
    {
        super(key, userDetailsService);
    }

    @Override
    protected boolean rememberMeRequested(HttpServletRequest request, String parameter)
    {
        if (alwaysRemember)
        {
            return true;
        }
        if (request != null && request.getMethod().equalsIgnoreCase("POST") &&
                request.getContentType() != null &&
                (request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_UTF8_VALUE) ||
                        request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)))
        {
            //当认证请求是JSON
            //使用fastjson转换
            ObjectMapper mapper = new ObjectMapper();
            try (InputStream is = request.getInputStream())
            {
                //忽略未知属性
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                //remember-me读到认证Bean
                Map<String, String> authenticationBean = mapper.readValue(is, Map.class);
                String remeberMe = authenticationBean.get("remember-me");
                //如果请求中有remember-me 字段并且值为on则返回true
                if (authenticationBean.containsKey("remember-me"))
                {
                    if (remeberMe.equals("1") || remeberMe.equals("true")|| remeberMe.equals("on"))
                    {
                        return true;
                    }
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        //否则调用原本的自我记住功能
        return super.rememberMeRequested(request, parameter);
    }
}
