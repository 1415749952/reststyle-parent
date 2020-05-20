package cn.ccsu.response;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-02-05
 * @Time: 2:10
 */
public class ResultUtil
{
    /**
     * 成功查询，返回一个对象使用这个方法
     *
     * @param data
     * @return
     */
    public static RestResult success(Object data)
    {
        return new RestResult(true, ResultCode.OK.getCode(), data, ResultCode.OK.getMessage());
    }

    /**
     * 成功操作，就用这个方法
     *
     * @return
     */
    public static RestResult success()
    {
        return new RestResult(true, ResultCode.OK.getCode(), null, ResultCode.OK.getMessage());
    }

    /**
     * 成功查询，返回分页数据就用这个方法
     *
     * @param queryList 从数据库中查询出来的数据
     * @param pageable  分页对象 这里使用的是PageHelper自带的com.github.pagehelper.Page类
     * @return
     */
/*    public static RestResult success(List queryList, Page pageable)
    {
        PageBaseInfo pageBaseInfo = new PageBaseInfo(queryList, pageable.getPageNum(), pageable.getPageSize(), pageable.getTotal());
        return new RestResult(true, ResultCode.OK.getCode(), pageBaseInfo, ResultCode.OK.getMessage());
    }*/

    /**
     * 失败后，返回没有错误数据
     *
     * @param resultCode
     * @return
     */
    public static RestResult error(ResultCode resultCode, String message)
    {
        return new RestResult(false, resultCode.getCode(), null, message);
    }

    /**
     * 失败后，返回有错误数据
     *
     * @param resultCode
     * @return
     */
    public static RestResult error(ResultCode resultCode, Object data, String message)
    {
        return new RestResult(false, resultCode.getCode(), data, message);
    }

}
