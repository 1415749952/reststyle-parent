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
 * @Time: 17:32
 */
@Data
public class OperationLog
{
    private String id;

    /**
     * 请求方法名
     */
    private String methodName;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 返回结果
     */
    private String responseParam;

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
     * 请求发生时间
     */
    private Date requestTime;

    /**
     * 系统版本
     */
    private String version;

    /**
     * 方法描述(这个方法是干什么的)
     */
    private String detail;

    /**
     * 日志等级:自己定，此处分为1-9
     */
    private Integer level;

    /**
     * 操作类型(enum):主要是select,insert,update,delete
     */
    private String operationType;

    /**
     * 被操作的对象被操作的对象(此处使用enum):可以是任何对象，如表名(user)，或者是工具(redis)
     */
    private String operationUnit;
}
