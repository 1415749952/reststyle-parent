package cn.ccsu.domain.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created with IntelliJ IDEA.
 * Description:权限类
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-01-30
 * @Time: 13:09
 */
@Data
public class SysAuthority implements GrantedAuthority
{
    /**
     * 权限id
     */
    private String id;
    /**
     * 权限名称
     */
    private String authorityName;

    /**
     * 权限标识符
     */
    private String authority;

    @Override
    public String getAuthority()
    {
        return this.authority;
    }
}
