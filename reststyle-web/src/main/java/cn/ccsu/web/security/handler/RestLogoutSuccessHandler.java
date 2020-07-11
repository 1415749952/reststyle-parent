package cn.ccsu.web.security.handler;

import cn.ccsu.utils.JsonUtils.JacksonUtil;
import cn.ccsu.utils.responseUtils.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:处理注销成功
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-01-30
 * @Time: 13:09
 */
@Component
public class RestLogoutSuccessHandler implements LogoutSuccessHandler
{
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException
    {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=UTF-8");
        //response.getWriter().write(new ObjectMapper().writeValueAsString(ResultUtil.success()));
        response.getWriter().write(JacksonUtil.object2Json(ResultUtil.success()));
    }
}