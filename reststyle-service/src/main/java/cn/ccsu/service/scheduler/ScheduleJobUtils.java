package cn.ccsu.service.scheduler;

import cn.ccsu.domain.table.ScheduleJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-09-09
 * @Time: 11:12 上午
 */
@Slf4j
public class ScheduleJobUtils extends QuartzJobBean
{
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException
    {
        /*String jsonJob = context.getMergedJobDataMap().getString(ScheduleJob.JOB_PARAM_KEY);
        ScheduleJob scheduleJob = JSON.parseObject(jsonJob, ScheduleJob.class);


        //数据库保存执行记录
        ScheduleJobLog scheduleJobLog = new ScheduleJobLog();
        scheduleJobLog.setJobId(scheduleJob.getId());
        scheduleJobLog.setBeanName(scheduleJob.getBeanName());
        scheduleJobLog.setMethodName(scheduleJob.getMethodName());
        scheduleJobLog.setParams(scheduleJob.getParams());
        scheduleJobLog.setCrtTime(new Date());

        //任务开始时间
        long startTime = System.currentTimeMillis();

        try
        {
            //执行任务
            log.info("任务准备执行，任务ID：" + scheduleJob.getId());
            ScheduleRunnableUtil task = new ScheduleRunnableUtil(scheduleJob.getBeanName(),
                    scheduleJob.getMethodName(), scheduleJob.getParams());

            Future<?> future = service.submit(task);
            future.get();

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            scheduleJobLog.setTimes((int) times);
            scheduleJobLog.setStatus(SchedulerConstant.EXECUTE_SUCCESS);
            log.info("任务执行完毕，任务ID：" + scheduleJob.getId() + "  总共耗时：" + times + "毫秒");

        }
        catch (Exception e)
        {
            log.error("任务执行失败，任务ID：" + scheduleJob.getId(), e);

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            scheduleJobLog.setTimes((int) times);
            scheduleJobLog.setStatus(SchedulerConstant.EXECUTE_FAILED);
            scheduleJobLog.setError(StringUtils.substring(e.toString(), 0, 2000));
        }
        finally
        {
            scheduleJobLogService.insertSelective(scheduleJobLog);
        }*/
    }
}
