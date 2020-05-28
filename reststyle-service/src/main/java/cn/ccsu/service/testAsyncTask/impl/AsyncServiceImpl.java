package cn.ccsu.service.testAsyncTask.impl;

import cn.ccsu.service.testAsyncTask.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-28
 * @Time: 13:46
 */
@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService
{
    @Override
    @Async
    public Future<Integer> doTaskOne(List<Integer> one)
    {
        log.info("开始做任务一（睡眠2s）");
        methodThread(2000);
        Integer sum = Integer.valueOf("0");
        for (int i = 0; i < one.size(); i++)
        {
            sum = sum + one.get(i);
        }

        log.info("任务一完成，当前线程为 {}", Thread.currentThread().getName());
        return new AsyncResult<>(sum);
    }

    @Override
    @Async
    public Future<Integer> doTaskTwo(List<Integer> one)
    {
        log.info("开始做任务二（睡眠2s）");
        methodThread(2000);
        Integer sum = Integer.valueOf("0");
        for (int i = 0; i < one.size(); i++)
        {
            sum = sum + one.get(i);
        }
        long endTime = System.currentTimeMillis();
        log.info("任务二完成，当前线程为 {}", Thread.currentThread().getName());
        return new AsyncResult<>(sum);
    }

    @Override
    @Async
    public void doTaskThree()
    {
        log.info("开始做任务三（睡眠1s）");
        methodThread(1000);
        log.info("任务三完成，当前线程为 {}", Thread.currentThread().getName());
    }


    /**
     * 接口睡眠
     */
    private void methodThread(Integer time)
    {
        try
        {
            Thread.sleep(time);
        }
        catch (InterruptedException e)
        {
            Thread.interrupted();
            log.error("sleep异常：{}", e);
        }
    }

}
