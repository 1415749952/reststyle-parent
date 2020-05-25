package cn.ccsu.security.handler;

import cn.ccsu.utils.JsonUtils.JacksonUtil;
import cn.ccsu.utils.responseUtils.ResultCode;
import cn.ccsu.utils.responseUtils.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:处理登录验证失败的类
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-01-30
 * @Time: 13:09
 */
@Component
public class RestAuthFailureHandler implements AuthenticationFailureHandler
{
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException
    {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=UTF-8");
        //response.getWriter().write(new ObjectMapper().writeValueAsString(ResultUtil.error(ResultCode.USER_LOGIN_FAILURE, exception.getMessage())));
        response.getWriter().write(JacksonUtil.object2Json(ResultUtil.error(ResultCode.USER_LOGIN_FAILURE, exception.getMessage())));
    }
}
