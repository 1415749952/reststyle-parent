package cn.ccsu.domain.table;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-27
 * @Time: 17:30
 */
@Data
public class ExceptionLog
{
    private String id;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 请求方法名
     */
    private String methodName;

    /**
     * 异常名称
     */
    private String exceptionName;

    /**
     * 异常信息
     */
    private String exceptionMessage;

    /**
     * 操作人id
     */
    private String userId;

    /**
     * 操作URI
     */
    private String url;

    /**
     * 操作员IP
     */
    private String ip;

    /**
     * 异常发生时间
     */
    private Date occurTime;

    /**
     * 系统版本
     */
    private String version;
}
