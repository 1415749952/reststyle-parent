package cn.ccsu.security.handler;

import cn.ccsu.utils.JsonUtils.JacksonUtil;
import cn.ccsu.utils.responseUtils.ResultCode;
import cn.ccsu.utils.responseUtils.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:处理没有权限的类
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-01-30
 * @Time: 13:09
 */
@Component
public class RestAuthAccessDeniedHandler implements AccessDeniedHandler
{
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException
    {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JacksonUtil.object2Json(ResultUtil.error(ResultCode.PERMISSION_NO_ACCESS, ResultCode.PERMISSION_NO_ACCESS.getMessage())));
    }
}
