package cn.ccsu.service.scheduler;

import cn.ccsu.commom.constant.JobConstant;
import cn.ccsu.commom.enums.JobStatus;
import cn.ccsu.domain.table.ScheduleJob;
import cn.ccsu.utils.JsonUtils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

/**
 * 定时任务工具类
 *
 * @author thefei
 */
@Slf4j
public class ScheduleUtils
{

    /**
     * 获取触发器key
     *
     * @param jobId
     * @return
     */
    public static TriggerKey getTriggerKey(String jobId)
    {
        return TriggerKey.triggerKey(JobConstant.TRIGGER_KEY_PREFIX + jobId);
    }

    /**
     * 获取jobKey
     *
     * @param jobId
     * @return
     */
    public static JobKey getJobKey(String jobId)
    {
        return JobKey.jobKey(JobConstant.JOB_KEY_PREFIX + jobId);
    }

    /**
     * 获取表达式触发器
     *
     * @param scheduler
     * @param jobId
     * @return
     * @throws SchedulerException
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, String jobId) throws SchedulerException
    {
        try
        {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(jobId));
        }
        catch (SchedulerException e)
        {
            throw e;
        }
    }

    /**
     * 创建定时任务
     *
     * @param scheduler
     * @param scheduleJob
     * @throws SchedulerException
     */
    public static void createScheduleJob(Scheduler scheduler, ScheduleJob scheduleJob) throws SchedulerException
    {
        try
        {
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(ScheduleJobUtils.class).withIdentity(getJobKey(scheduleJob.getId())).build();

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();

            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(scheduleJob.getId()))
                    .withSchedule(scheduleBuilder).build();

            //放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put("job", JacksonUtil.object2Json(scheduleJob));

            scheduler.scheduleJob(jobDetail, trigger);

            //暂停任务
            if (scheduleJob.getStatus().equals(JobStatus.PAUSING.status()))
            {
                pauseJob(scheduler, scheduleJob.getId());
            }
        }
        catch (SchedulerException e)
        {
            throw e;
        }
    }

    /**
     * 更新定时任务
     *
     * @param scheduler
     * @param scheduleJob
     * @throws SchedulerException
     */
    public static void updateScheduleJob(Scheduler scheduler, ScheduleJob scheduleJob) throws SchedulerException
    {
        try
        {
            TriggerKey triggerKey = getTriggerKey(scheduleJob.getId());

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();

            CronTrigger trigger = getCronTrigger(scheduler, scheduleJob.getId());

            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            //参数
            trigger.getJobDataMap().put("job", JacksonUtil.object2Json(scheduleJob));

            scheduler.rescheduleJob(triggerKey, trigger);

            //暂停任务
            if (scheduleJob.getStatus().equals(JobStatus.PAUSING.status()))
            {
                pauseJob(scheduler, scheduleJob.getId());
            }
        }
        catch (SchedulerException e)
        {
            throw e;
        }
    }

    /**
     * 立即执行
     *
     * @param scheduler
     * @param scheduleJob
     * @throws SchedulerException
     */
    public static void run(Scheduler scheduler, ScheduleJob scheduleJob) throws SchedulerException
    {
        try
        {
            //参数
            JobDataMap dataMap = new JobDataMap();
            dataMap.put("job", JacksonUtil.object2Json(scheduleJob));

            scheduler.triggerJob(getJobKey(scheduleJob.getId()), dataMap);
        }
        catch (SchedulerException e)
        {
            throw e;
        }
    }

    /**
     * 暂停任务
     *
     * @param scheduler
     * @param id
     * @throws SchedulerException
     */
    public static void pauseJob(Scheduler scheduler, String id) throws SchedulerException
    {
        try
        {
            scheduler.pauseJob(getJobKey(id));
        }
        catch (SchedulerException e)
        {
            throw e;
        }
    }

    /**
     * 恢复任务
     *
     * @param scheduler
     * @param id
     * @throws SchedulerException
     */
    public static void resumeJob(Scheduler scheduler, String id) throws SchedulerException
    {
        try
        {
            scheduler.resumeJob(getJobKey(id));
        }
        catch (SchedulerException e)
        {
            throw e;
        }
    }

    /**
     * 删除定时任务
     *
     * @param scheduler
     * @param id
     * @throws SchedulerException
     */
    public static void deleteScheduleJob(Scheduler scheduler, String id) throws SchedulerException
    {
        try
        {
            scheduler.deleteJob(getJobKey(id));
        }
        catch (SchedulerException e)
        {
            throw e;
        }
    }
}
