package cn.ccsu.mapper.security;

import cn.ccsu.domain.security.SysRole;
import cn.ccsu.domain.security.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhoukebo
 * @date 2018/9/4
 */
@Repository
@Mapper
public interface SysUserMapper
{
    /**
     * 查询用户
     * @param username
     * @return
     */
    public SysUser findByUsername(String username);

    /**
     * 根据用户id查询角色
     * @param id
     * @return
     */
    List<SysRole> findRolesBySysUserId(String id);
}
