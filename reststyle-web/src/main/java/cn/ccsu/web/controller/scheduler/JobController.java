package cn.ccsu.web.controller.scheduler;

import cn.ccsu.dto.JobInfoDTO;
import cn.ccsu.utils.responseUtils.RestResult;
import cn.ccsu.utils.responseUtils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-29
 * @Time: 9:52
 */
@RestController
@Slf4j
public class JobController
{

    /**
     * 调度器
     */
    @Autowired
    private Scheduler scheduler;

    /**
     * JobManagerService
     */
    /*@Autowired
    private JobManagerService jobManagerService;
*/
    /**
     * JobService
     */
    /*@Autowired
    private JobService jobService;*/

    /**
     * 参数校验
     *
     * @param jobInfoDTO
     * @return
     */
/*
    private String commonValidate(JobInfoDTO jobInfoDTO)
    {
        if (StrUtil.isBlank(jobInfoDTO.getTitle()))
        {
            return "任务名称不能为空";
        }
        if (StrUtil.isBlank(jobInfoDTO.getUrl()))
        {
            return "URL地址不能为空";
        }
        if (StrUtil.isBlank(jobInfoDTO.getMethod()))
        {
            return "请求方式不能为空";
        }
        if (StrUtil.isBlank(jobInfoDTO.getJobGroupName()) && jobInfoDTO.getJobGroupId() == null)
        {
            return "业务部门不能为空";
        }
        // 校验corn表达式
        if (!CronExpression.isValidExpression(jobInfoDTO.getCron()))
        {
            return "非法的任务corn表达式";
        }
        // 有参数，校验参数是否为json格式
        if (StrUtil.isNotBlank(jobInfoDTO.getParams()) && !JSONUtil.isJson(jobInfoDTO.getParams()))
        {
            return "非法的任务参数格式";
        }
        return JobConstant.SUCCESS_CODE;
    }
*/

    /**
     * 新增一个http定时任务
     *
     * @param jobInfoDTO
     * @return
     */
    @RequestMapping("/addJob")
    public RestResult addJob(@RequestBody @Validated JobInfoDTO jobInfoDTO)
    {
        log.info(jobInfoDTO.toString());
        return ResultUtil.success();
       /* String validate = commonValidate(jobInfoDTO);
        if (!JobConstant.SUCCESS_CODE.equals(validate))
        {
            return RestResult.failure(validate);
        }
        String res = jobManagerService.addJob(scheduler, jobInfoDTO);
        if (JobConstant.SUCCESS_CODE.equals(res))
        {
            return RestResult.success("添加成功");
        }
        else
        {
            return RestResult.failure(res);
        }*/
    }

    /**
     * 编辑任务
     *
     * @param jobInfoDTO
     * @return
     */
/*    @RequestMapping("/editJob")
    public RestResult editJob(JobInfoDTO jobInfoDTO)
    {
        String validate = commonValidate(jobInfoDTO);
        if (!JobConstant.SUCCESS_CODE.equals(validate))
        {
            return RestResult.failure(validate);
        }
        String res = jobManagerService.editJob(scheduler, jobInfoDTO);
        if (JobConstant.SUCCESS_CODE.equals(res))
        {
            return RestResult.success("修改成功");
        }
        else
        {
            return RestResult.failure(res);
        }
    }*/

    /**
     * 暂停一个http定时任务
     *
     * @return
     * @throws SchedulerException
     */
/*    @RequestMapping("/pauseJob")
    public RestResult pauseJob(@RequestParam(name = "jobInfoId") Integer jobInfoId)
    {
        String res = jobManagerService.pauseOrRemoveOrRestoreJob(scheduler, jobInfoId, JobEnums.JobStatus.PAUSING.status());
        if (JobConstant.SUCCESS_CODE.equals(res))
        {
            return RestResult.success("操作成功");
        }
        else
        {
            return RestResult.failure(res);
        }
    }*/

    /**
     * 恢复一个http定时任务
     *
     * @param jobInfoId
     * @return
     */
/*
    @RequestMapping("/restoreJob")
    public RestResult restoreJob(@RequestParam(name = "jobInfoId") Integer jobInfoId)
    {
        String res = jobManagerService.pauseOrRemoveOrRestoreJob(scheduler, jobInfoId, JobEnums.JobStatus.RUNNING.status());
        if (JobConstant.SUCCESS_CODE.equals(res))
        {
            return RestResult.success("操作成功");
        }
        else
        {
            return RestResult.failure(res);
        }
    }
*/

    /**
     * 删除一个http定时任务
     *
     * @param jobInfoId
     * @return
     */
/*     @RequestMapping("/removeJob")
   public RestResult removeJob(@RequestParam(name = "jobInfoId") Integer jobInfoId)
    {
        String res = jobManagerService.pauseOrRemoveOrRestoreJob(scheduler, jobInfoId, JobEnums.JobStatus.DELETED.status());
        if (JobConstant.SUCCESS_CODE.equals(res))
        {
            return RestResult.success("删除成功");
        }
        else
        {
            return RestResult.failure(res);
        }
    }*/

    /**
     * 触发执行一次任务
     *
     * @param jobInfoId
     * @return
     */
/*
    @RequestMapping("/executeJob")
    public RestResult executeJob(@RequestParam(name = "jobInfoId") Integer jobInfoId)
    {
        String res = jobManagerService.executeJob(scheduler, jobInfoId);
        if (JobConstant.SUCCESS_CODE.equals(res))
        {
            return RestResult.success("执行成功");
        }
        else
        {
            return RestResult.failure(res);
        }
    }
*/

    /**
     * 分页获取http任务列表
     *
     * @param jobInfoQuery
     * @return
     */
/*    @RequestMapping("/pageJob")
    public RestResult pageJob(JobInfoQuery jobInfoQuery)
    {
        PageVO<JobInfoDTO> page = jobService.selectJob(jobInfoQuery);
        return RestResult.success(page);
    }*/

    /**
     * 分页查询指定任务的执行日志
     *
     * @param jobLogQuery
     * @return
     */
  /*  @RequestMapping("/pageJobLog")
    public RestResult pageJobLog(JobLogQuery jobLogQuery)
    {
        if (jobLogQuery.getJobInfoId() == null)
        {
            return RestResult.failure("param jobInfoId is empty");
        }
        PageVO<JobLogBO> page = jobService.selectJobLog(jobLogQuery);
        return RestResult.success(page);
    }*/

    /**
     * 获取任务执行数据统计
     *
     * @param startTime
     * @param endTime
     * @return
     */
    /*@RequestMapping("/getReportStatistic")
    public RestResult getReportStatistic(String startTime, String endTime)
    {
        Date startDate;
        Date endDate;
        if (StrUtil.isNotBlank(startTime) && StrUtil.isNotBlank(endTime))
        {
            startDate = DateUtil.parseDateTime(startTime);
            endDate = DateUtil.parseDateTime(endTime);
        }
        else if (StrUtil.isBlank(startTime) && StrUtil.isBlank(endTime))
        {
            // 默认统计过去一周数据
            DateTime date = DateUtil.date();
            startDate = DateUtil.beginOfDay(DateUtil.offsetDay(date, -6));
            endDate = DateUtil.endOfDay(date);
        }
        else
        {
            return RestResult.failure("params error");
        }
        Map<String, Object> reportStatistic = jobService.getReportStatistic(startDate, endDate);
        return RestResult.success(reportStatistic);
    }*/
}
