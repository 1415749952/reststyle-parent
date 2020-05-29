package cn.ccsu.mapper.scheduler;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zxq
 */
@Mapper
@Repository
public interface JobLogReportMapper
{

    /**
     * 指定报表运行中任务数量+1
     *
     * @param id
     */
    void increaseRunningCount(String id);

    /**
     * 指定报表运行成功任务数量+1,运行中任务数量-1
     *
     * @param id
     */
    void increaseSuccessCount(String id);

    /**
     * 指定报表运行失败任务数量+1,运行中任务数量-1
     *
     * @param id
     */
    void increaseFailCount(String id);

}