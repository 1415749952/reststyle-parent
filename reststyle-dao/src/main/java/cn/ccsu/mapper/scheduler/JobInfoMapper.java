package cn.ccsu.mapper.scheduler;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zxq
 */
@Mapper
@Repository
public interface JobInfoMapper
{

    /**
     * 查询jobInfo
     *
     * @param query
     * @return
     */
    //List<JobInfoBO> selectJobInfo(@Param(value = "query") JobInfoQuery query);

    //JobInfo selectByPrimaryKey(String jobInfoId);
}