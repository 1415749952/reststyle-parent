package cn.ccsu.scheduler.job;

import cn.ccsu.utils.uniqueId.SnowFlakeUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-09-09
 * @Time: 4:14 下午
 */
public class test implements Job
{

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        System.out.println("定时任务要干的活");
    }
}
