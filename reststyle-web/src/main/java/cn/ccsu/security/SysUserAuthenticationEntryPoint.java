package cn.ccsu.security;

import cn.ccsu.utils.responseUtils.ResultCode;
import cn.ccsu.utils.responseUtils.ResultUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description: 处理没有登陆，就发送请求
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-02-06
 * @Time: 23:24
 */
@Component
public class SysUserAuthenticationEntryPoint implements AuthenticationEntryPoint
{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException
    {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(ResultUtil.error(ResultCode.USER_NOT_LOGGED_IN, ResultCode.USER_NOT_LOGGED_IN.getMessage())));
    }

}
