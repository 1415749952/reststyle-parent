package cn.ccsu.service.scheduler.impl;

import cn.ccsu.commom.enums.DelFlag;
import cn.ccsu.commom.enums.JobStatus;
import cn.ccsu.commom.exception.BusinessException;
import cn.ccsu.domain.table.ScheduleJob;
import cn.ccsu.dto.JobInfoDTO;
import cn.ccsu.mapper.scheduler.ScheduleJobMapper;
import cn.ccsu.scheduler.job.test;
import cn.ccsu.service.scheduler.JobManagerService;
import cn.ccsu.service.security.AccountSecurityUtil;
import cn.ccsu.utils.dataUtils.DateUtil;
import cn.ccsu.utils.uniqueId.SnowFlakeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-29
 * @Time: 9:55
 */

@Slf4j
@Service
public class JobManagerServiceImpl implements JobManagerService
{
    @Autowired
    private ScheduleJobMapper scheduleJobMapper;

    /**
     * 创建一个job
     *
     * @param jobInfoDTO
     * @return
     */
    @Override
    public String creatJob(JobInfoDTO jobInfoDTO)
    {
        List<ScheduleJob> scheduleJobs = scheduleJobMapper.findByTitle(jobInfoDTO.getTitle());
        if (CollectionUtils.isNotEmpty(scheduleJobs))
        {
            throw new BusinessException("该任务title已经存在");
        }
        scheduleJobs = scheduleJobMapper.findByBeanAndMethod(jobInfoDTO.getBeanName(),jobInfoDTO.getMethodName());
        if (CollectionUtils.isNotEmpty(scheduleJobs))
        {
            throw new BusinessException("该任务的beanName和methodName已经存在");
        }
        ScheduleJob scheduleJob = new ScheduleJob();
        BeanUtils.copyProperties(jobInfoDTO, scheduleJob);
        scheduleJob.setId(SnowFlakeUtil.getId());
        scheduleJob.setStatus(JobStatus.RUNNING.status());
        log.info(scheduleJob.toString());
        scheduleJob.setCreateTime(DateUtil.getNowTime());
        scheduleJob.setCreateUser(AccountSecurityUtil.getLoginUserId());
        scheduleJob.setDelFlag(DelFlag.EXIST.status());
        scheduleJobMapper.insertSelective(scheduleJob);

        JobDetail jobDetail = JobBuilder.newJob(cn.ccsu.scheduler.job.test.class).withIdentity("job1","group1").build();
        return scheduleJob.getId();
    }
}
