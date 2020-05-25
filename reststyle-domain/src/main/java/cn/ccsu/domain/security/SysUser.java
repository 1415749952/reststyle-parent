package cn.ccsu.domain.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:用户实体类，这里要成为spring security使用的实体类必须要实现UserDetails接口
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-01-30
 * @Time: 13:09
 */
@Data
public class SysUser implements UserDetails
{
    /**
     * 用户id
     */
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 帐户未过期
     */
    private boolean isAccountNonExpired;

    /**
     * 帐户未锁定
     */
    private boolean isAccountNonLocked;

    /**
     * 凭证未过期
     */
    private boolean isCredentialsNonExpired;

    /**
     * 已启用
     */
    private boolean isEnabled;

    /**
     * 角色
     */
    private List<SysRole> roles;
    /**
     * 授权 有哪些权限
     */
    private Collection<? extends GrantedAuthority> grantedAuthority;

    /***
     * 正常情况下，角色和权限是两回事，
     * 所以我们还需要重写getAuthorities方法，将用户的角色和权限关联起来
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return grantedAuthority;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return this.isAccountNonExpired;
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonLocked()
    {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled()
    {
        return this.isEnabled;
    }
}
