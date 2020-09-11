package cn.ccsu.mapper.scheduler;

import cn.ccsu.domain.table.ScheduleJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-09-09
 * @Time: 9:44 上午
 */
@Repository
@Mapper
public interface ScheduleJobMapper
{
    int deleteByPrimaryKey(String id);

    int insert(ScheduleJob record);

    int insertSelective(ScheduleJob record);

    ScheduleJob selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScheduleJob record);

    int updateByPrimaryKey(ScheduleJob record);

    List<ScheduleJob> findByTitle(String title);

    List<ScheduleJob> findByBeanAndMethod(@Param("beanName") String beanName, @Param("methodName") String methodName);
}