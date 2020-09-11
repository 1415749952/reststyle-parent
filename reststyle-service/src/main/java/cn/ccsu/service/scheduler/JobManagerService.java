package cn.ccsu.service.scheduler;


import cn.ccsu.dto.JobInfoDTO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-29
 * @Time: 9:54
 */

public interface JobManagerService
{
    /**
     * 创建一个job
     * @param jobInfoDTO
     * @return
     */
    String creatJob(JobInfoDTO jobInfoDTO);
}
