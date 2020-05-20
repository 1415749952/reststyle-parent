package cn.ccsu.condition;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:查询用户参数封装类，前端页面查询有可能有几个查询条件，将每个查询条件合成一个类
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-01-30
 * @Time: 13:17
 */
@Data
public class AccountCondition
{
    private String username;

    private String name;

    private String telephone;

    private String email;

    private Date createTime;

    private Date modifyTime;

}