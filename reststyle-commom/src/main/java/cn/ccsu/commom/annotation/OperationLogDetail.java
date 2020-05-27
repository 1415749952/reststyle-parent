package cn.ccsu.commom.annotation;

import cn.ccsu.commom.enums.OperationType;
import cn.ccsu.commom.enums.OperationUnit;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * Description:自定义注解
 * <p>
 * 注解使用示例@OperationLogDetail(detail = "获取用户名",level = 3,operationUnit = OperationUnit.USER,operationType = OperationType.SELECT)
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-27
 * @Time: 17:22
 */
@Documented
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLogDetail
{
    /**
     * 方法描述
     */
    String detail() default "";

    /**
     * 日志等级:自己定，此处分为1-9
     */
    int level() default 0;

    /**
     * 操作类型(enum):主要是select,insert,update,delete
     */
    OperationType operationType() default OperationType.UNKNOWN;

    /**
     * 被操作的对象(此处使用enum):可以是任何对象，如表名(user)，或者是工具(redis)
     */
    OperationUnit operationUnit() default OperationUnit.UNKNOWN;
}