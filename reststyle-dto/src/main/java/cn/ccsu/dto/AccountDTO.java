package cn.ccsu.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

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
    @NotBlank(message = "用户名不能为空！")
    private String username;

    //@ApiModelProperty("用户密码")//生成接口文档swagger。
    @NotBlank(message = "密码不能为空")
    private String password;

    private String name;

    private String telephone;

    private String email;
}
