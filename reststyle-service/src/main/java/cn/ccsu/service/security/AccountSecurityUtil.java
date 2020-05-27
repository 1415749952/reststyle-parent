package cn.ccsu.service.security;

import cn.ccsu.domain.security.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-03-02
 * @Time: 17:49
 */
@Component
public class AccountSecurityUtil
{
    /**
     * 获取当前用户
     *
     * @return
     */
    public static SysUser getLoginUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal == null)
        {
            return null;
        }
        if (principal instanceof SysUser)
        {
            SysUser userDetails = (SysUser) principal;
            return userDetails;
        }
        else
        {
            return null;
        }
    }

    /**
     * 获取当前用户Id
     *
     * @return
     */
    public static String getLoginUserId()
    {
        return getLoginUser().getId();
    }
}
