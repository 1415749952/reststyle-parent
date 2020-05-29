/*
package cn.ccsu.scheduler.service;

import cn.ccsu.scheduler.model.domain.JobGroup;
import cn.ccsu.scheduler.model.domain.JobInfo;
import cn.ccsu.mapper.scheduler.JobGroupMapper;
import cn.ccsu.mapper.scheduler.JobLogReportMapper;

import java.util.*;

*/
/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-29
 * @Time: 9:57
 *//*

public interface JobService
{
    */
/**
     * 查询http任务列表
     *
     * @param jobInfoQuery
     * @return
     *//*

    public PageVO<JobInfoBO> selectJob(JobInfoQuery jobInfoQuery);

    */
/**
     * 查询指定任务的日志
     *
     * @param jobLogQuery
     * @return
     *//*

    public PageVO<JobLogBO> selectJobLog(JobLogQuery jobLogQuery);

    */
/**
     * 获取数据库中已有任务分组
     *
     * @return
     *//*

    public List<JobGroup> selectJobGroup();

    */
/**
     * 获取任务数统计
     *
     * @return
     *//*

    public Map<String, Integer> getJobInfoAmountStatistic();

    */
/**
     * 查询指定时间范围内的任务执行数据报表
     *
     * @param startDate
     * @param endDate
     * @return
     *//*

    public Map<String, Object> getReportStatistic(Date startDate, Date endDate);

    */
/**
     * 根据id查找指定任务
     *
     * @param jobInfoId
     *//*

    public JobInfo selectJobInfoById(String jobInfoId);
}
*/
