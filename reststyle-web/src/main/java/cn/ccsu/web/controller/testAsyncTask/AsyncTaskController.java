package cn.ccsu.web.controller.testAsyncTask;

import cn.ccsu.service.testAsyncTask.AsyncService;
import cn.ccsu.utils.responseUtils.RestResult;
import cn.ccsu.utils.responseUtils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * Description:异步任务控制器
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-28
 * @Time: 13:41
 */
@RestController
@RequestMapping("/async")
@Slf4j
public class AsyncTaskController
{
    @Autowired
    private AsyncService asyncService;


    @GetMapping(value = "/task")
    public RestResult taskExecute()
    {
        ArrayList<Integer> integerOnes = new ArrayList<>();
        for (int i = 1; i < 50; i++)
        {
            integerOnes.add(i);
        }
        ArrayList<Integer> integertwos = new ArrayList<>();
        for (int i = 50; i <= 100; i++)
        {
            integertwos.add(i);
        }


        long startTime = System.currentTimeMillis();
        Integer sum = Integer.valueOf("0");
        try
        {
            Future<Integer> r1 = asyncService.doTaskOne(integerOnes);
            Future<Integer> r2 = asyncService.doTaskTwo(integertwos);
            asyncService.doTaskThree();

            while (true)
            {
                if (r1.isDone() && r2.isDone())
                {
                    log.info("异步任务一、二已完成");
                    break;
                }
            }
            Integer baseResponse1 = r1.get();
            Integer baseResponse2 = r2.get();
            log.info("返回结果：{}，{}", baseResponse1, baseResponse2);
            sum = baseResponse1+baseResponse2;
        }
        catch (Exception e)
        {
            log.error("执行异步任务异常 {}", e.getMessage());
        }
        long endTime = System.currentTimeMillis();
        log.info("异步任务总耗时:{}", endTime - startTime);
        return ResultUtil.success(sum);
    }
}
