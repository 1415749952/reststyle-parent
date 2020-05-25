package cn.ccsu.domain.security;

import lombok.Data;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * Description:角色实体类
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-01-30
 * @Time: 13:09
 */
@Data
public class SysRole
{
    /**
     * 角色id
     */
    private String id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 拥有的权限
     */
    private List<SysAuthority> authorities;
}
