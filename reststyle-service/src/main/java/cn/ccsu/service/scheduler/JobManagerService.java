/*
package cn.ccsu.scheduler.service;


import org.quartz.Scheduler;

*/
/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-29
 * @Time: 9:54
 *//*

public interface JobManagerService
{

    */
/**
     * 添加一个任务,并开启执行
     *
     * @param jobInfoBO
     * @return
     *//*

    public String addJob(Scheduler scheduler, JobInfoBO jobInfoBO);

    */
/**
     * 暂停、删除、恢复任务
     *
     * @param scheduler
     * @param jobInfoId
     * @return
     *//*

    public String pauseOrRemoveOrRestoreJob(Scheduler scheduler, Integer jobInfoId, Integer status);

    */
/**
     * 立即执行任务
     *
     * @param scheduler
     * @param jobInfoId
     * @return
     *//*

    public String executeJob(Scheduler scheduler, Integer jobInfoId);

    */
/**
     * 修改任务
     * 每次编辑，都是新增一个调度任务替换旧的调度任务
     *
     * @param scheduler
     * @param jobInfoBO
     * @return
     *//*

    ;

    public String editJob(Scheduler scheduler, JobInfoBO jobInfoBO);
}
*/
