package cn.ccsu.domain.table;

import lombok.Data;

import java.util.Date;

/**
 * 任务日志报表实体
 *
 * @author zxq
 */
@Data
public class JobLogReport
{
    private String id;

    /**
     * 调度-时间
     */
    private Date day;

    /**
     * 运行中-日志数量
     */
    private Integer runningCount;

    /**
     * 执行成功-日志数量
     */
    private Integer successCount;

    /**
     * 执行失败-日志数量
     */
    private Integer failCount;

}