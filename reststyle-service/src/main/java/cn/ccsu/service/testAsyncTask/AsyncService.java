package cn.ccsu.service.testAsyncTask;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-28
 * @Time: 13:45
 */
public interface AsyncService
{
    public Future<Integer> doTaskOne(List<Integer> one);

    public Future<Integer> doTaskTwo(List<Integer> two);

    public void doTaskThree();
}
