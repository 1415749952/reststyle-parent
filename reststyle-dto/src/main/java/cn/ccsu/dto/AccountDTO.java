package cn.ccsu.dto;

import cn.ccsu.commom.constraint.ValidateEnum;
import cn.ccsu.commom.constraint.group.Update;
import cn.ccsu.commom.enums.Sex;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * Description: 新增和修改时从页面传入的程序的参数
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-20
 * @Time: 15:06
 */
@Data
public class AccountDTO
{
    /*
     * 只有分组为update的时候校验,其他不校验
     * 使用方法 public RestResult update(@PathVariable String id, @Validated(value = {Update.class}) @RequestBody AccountDTO accountDTO)
     *
     * @NotBlank(message = "用户名不能为空！",groups = Update.class)
     * private String id;
     */



    @NotBlank(message = "用户名不能为空！")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String name;

    private String telephone;

    private String email;

    @NotNull(message = "age不能为null")
    @Min(value = 1, message = "年龄不符合要求")
    @Max(value = 200, message = "年龄不符合要求")
    private Integer age;

    @ValidateEnum(clazz = Sex.class, method = "getValue", message = "sex参数错误")
    private String sex;
}
