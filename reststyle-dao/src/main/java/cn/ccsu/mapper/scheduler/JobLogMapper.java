package cn.ccsu.mapper.scheduler;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zxq
 */
@Mapper
@Repository
public interface JobLogMapper
{

    /**
     * 查找任务运行日志
     *
     * @param jobLogQuery
     * @return
     */
   // List<JobLogBO> selectJobLog(@Param(value = "query") JobLogQuery jobLogQuery);

}