package cn.ccsu.table;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-20
 * @Time: 14:53
 */
@Data
public class Account extends BaseEntity
{
    /**
     * 登录名
     */
    private String username;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 姓名
     */
    private String name;
    /**
     * 电话
     */
    private String telephone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 帐户未过期
     */
    private Integer isAccountNonExpired;
    /**
     * 帐户未锁定
     */
    private Integer isAccountNonLocked;
    /**
     * 凭证未过期
     */
    private Integer isCredentialsNonExpired;
    /**
     * 账号已启用
     */
    private Integer isEnabled;
}
