package cn.ccsu.web.controller.scheduler;

import cn.ccsu.dto.JobInfoDTO;
import cn.ccsu.service.scheduler.JobManagerService;
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
@RequestMapping("/job")
@Slf4j
public class JobController
{

    @Autowired
    private JobManagerService jobManagerService;

    /**
     * 新增一个http定时任务
     *
     * @param jobInfoDTO
     * @return
     */
    @RequestMapping("/addJob")
    public RestResult addJob(@RequestBody @Validated JobInfoDTO jobInfoDTO)
    {
        String jobId = jobManagerService.creatJob(jobInfoDTO);
        return ResultUtil.success(jobId);
    }

}
