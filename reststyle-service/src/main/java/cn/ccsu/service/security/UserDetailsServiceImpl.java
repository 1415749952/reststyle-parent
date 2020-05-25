package cn.ccsu.service.security;

import cn.ccsu.domain.security.SysAuthority;
import cn.ccsu.domain.security.SysRole;
import cn.ccsu.domain.security.SysUser;
import cn.ccsu.mapper.security.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:需要自定义UserDetailsService实现spring security的UserDetailsService接口
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-01-30
 * @Time: 13:09
 */
@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService
{

    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        SysUser user = sysUserMapper.findByUsername(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("用户名不存在");
        }
        if (!user.isEnabled())
        {
            throw new BadCredentialsException("账号已停用");
        }
        if (!user.isAccountNonLocked())
        {
            throw new BadCredentialsException("账号已锁定");
        }
        if (!user.isAccountNonExpired())
        {
            throw new BadCredentialsException("账号已过期");
        }
        if (!user.isCredentialsNonExpired())
        {
            throw new BadCredentialsException("凭证已过期");
        }
        List<SysRole> roles = sysUserMapper.findRolesBySysUserId(user.getId());
        user.setRoles(roles);
        List<SysAuthority> authorities = new ArrayList<>();
        //遍历该用户的角色获取所有的权限
        for (SysRole role : roles)
        {
            authorities.addAll(role.getAuthorities());
        }
        user.setGrantedAuthority(authorities);
        return user;
    }
}
