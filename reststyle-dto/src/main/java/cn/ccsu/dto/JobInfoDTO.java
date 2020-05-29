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
     * 请求路径
     */
    @NotBlank(message = "URL地址不能为空")
    private String url;

    /**
     * 请求方式 GET POST
     */
    @NotBlank(message = "请求方式不能为空")
    private String method;


    /**
     * cron表达式
     */
    @ValidCorn
    private String cron;

    /**
     * 请求参数
     */
    @ValidJson(message = "请求参数错误")
    private String params;

    /**
     * 备注
     */
    private String remark;

    /**
     * 分组id，关联zxq_job_group
     */
    private String jobGroupId;

    /**
     * 任务状态 -1 已删除；0 已暂停； 1 运行中
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 分组名
     */
    private String jobGroupName;

    /**
     * 下一次运行时间
     */
    private Date nextExecuteTime;
}
