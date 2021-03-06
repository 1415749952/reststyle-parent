package cn.ccsu.dto;

import cn.ccsu.commom.constraint.validator.enums.ValidCorn;
import cn.ccsu.commom.constraint.validator.enums.ValidJson;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-29
 * @Time: 10:52
 */
@Data
public class JobInfoDTO
{

    /**
     * 标题
     */
    @NotBlank(message = "任务名称不能为空")
    private String title;


    /**
     * spring bean的名字
     */
    @NotBlank(message = "URL地址不能为空")
    private String beanName;

    /**
     * 方法名字
     */
    @NotBlank(message = "方法名字不能为空")
    private String methodName;


    /**
     * cron表达式
     */
    @ValidCorn
    private String cronExpression;

    /**
     * 请求参数
     */
    @ValidJson(message = "参数格式错误")
    private String params;

    /**
     * 备注
     */
    private String remark;

    /**
     * 分组id，关联zxq_job_group
     */
    private String jobGroupId;

}
