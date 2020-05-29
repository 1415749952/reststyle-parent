package cn.ccsu.domain.table;

import lombok.Data;


import java.util.Date;

/**
 * @author zxq
 */
@Data
public class JobLog
{
    /**
     * 主键
     */
    private String id;

    /**
     * 任务id，关联zxq_job_detail
     */
    private String jobInfoId;

    /**
     * 执行状态:0-执行失败 1-执行成功
     */
    private Integer executeStatus;

    /**
     * 执行参数
     */
    private String executeParams;

    /**
     * 执行失败原因
     */
    private String executeFailMsg;

    /**
     * 执行时间
     */
    private Date createTime;

    /**
     * 任务执行消耗时间 单位：毫秒
     */
    private Long consumeTime;

}