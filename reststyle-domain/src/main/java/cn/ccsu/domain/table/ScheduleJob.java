package cn.ccsu.domain.table;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-09-08
 * @Time: 5:50 下午
 */
@Data
public class ScheduleJob extends BaseEntity
{
    /**
     * 任务的标题
     */
    private String title;


    /**
     * spring bean的名字
     */
    private String beanName;

    /**
     * 方法名字
     */
    private String methodName;


    /**
     * cron表达式
     */
    private String cronExpression;

    /**
     * 请求参数
     */
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
     * 任务状态
     */
    private String status;
}
