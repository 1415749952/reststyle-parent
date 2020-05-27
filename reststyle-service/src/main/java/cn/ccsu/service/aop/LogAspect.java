package cn.ccsu.service.aop;

import cn.ccsu.commom.annotation.OperationLogDetail;
import cn.ccsu.domain.table.ExceptionLog;
import cn.ccsu.domain.table.OperationLog;
import cn.ccsu.service.security.AccountSecurityUtil;
import cn.ccsu.utils.JsonUtils.JacksonUtil;
import cn.ccsu.utils.ipUtils.IPUtil;
import cn.ccsu.utils.uniqueId.SnowFlakeUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * Description:这个是最主要的类,可以使用自定义注解或针对包名实现AOP增强。
 * 1)这里实现了对自定义注解的环绕增强切点，对使用了自定义注解的方法进行AOP切面处理；
 * 2)对方法运行时间进行监控；
 * 3)对方法名，参数名，参数值，对日志描述的优化处理；
 * 在方法上增加@Aspect 注解声明切面,使用@Pointcut 注解定义切点，标记方法。
 * 使用切点增强的时机注解:@Before,@Around,@AfterReturning,@AfterThrowing,@After
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-27
 * @Time: 17:27
 */
@Slf4j
@Aspect
@Component
public class LogAspect
{
    /**
     * 操作版本号
     * 项目启动时从命令行传入，例如：java -jar xxx.war --version=201902
     */
    @Value("${version}")
    private String operVer;
    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(cn.ccsu.commom.annotation.OperationLogDetail)")
    public void operLogPoinCut()
    {
    }

    /**
     * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
     *
     * @param joinPoint 切入点
     * @param keys      返回结果
     */
    @AfterReturning(value = "operLogPoinCut()", returning = "keys")
    public void saveoperLog(JoinPoint joinPoint, Object keys)
    {
        OperationLog operLog = new OperationLog();
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        try
        {
            operLog.setId(SnowFlakeUtil.getId());
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取操作
            OperationLogDetail opLog = method.getAnnotation(OperationLogDetail.class);
            if (opLog != null)
            {
                operLog.setDetail(opLog.detail());
                operLog.setLevel(opLog.level());
                operLog.setOperationType(opLog.operationType().toString());
                operLog.setOperationUnit(opLog.operationUnit().toString());
            }
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;
            operLog.setMethodName(methodName);
            // 请求的参数
            operLog.setRequestParam(getRequestParamer(request, joinPoint));
            operLog.setResponseParam(JacksonUtil.object2Json(keys));
            operLog.setIp(IPUtil.getIP(request));
            operLog.setUrl(request.getRequestURI());
            operLog.setRequestTime(new Date());
            operLog.setVersion(operVer);
            operLog.setUserId(AccountSecurityUtil.getLoginUserId());
            System.out.println(operLog);
            //operationLogService.addOperationLog(operLog);
        }
        catch (Exception e)
        {
            log.error("记录日志错误"+e.getMessage()+ "{} ", JacksonUtil.object2Json(operLog));
        }
    }

    /**
     * 异常返回通知，用于拦截异常日志信息 连接点抛出异常后执行
     *
     * @param joinPoint 切入点
     * @param e         异常信息
     */
    @AfterThrowing(pointcut = "operLogPoinCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e)
    {
        ExceptionLog excepLog = new ExceptionLog();
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        try
        {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            excepLog.setId(SnowFlakeUtil.getId());
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;
            // 请求的参数
            excepLog.setRequestParam(getRequestParamer(request, joinPoint));
            excepLog.setMethodName(methodName);
            excepLog.setExceptionName(e.getClass().getName());
            excepLog.setExceptionMessage(e.toString());
            excepLog.setUrl(request.getRequestURI());
            excepLog.setIp(IPUtil.getIP(request));
            excepLog.setVersion(operVer);
            excepLog.setOccurTime(new Date());
            excepLog.setUserId(AccountSecurityUtil.getLoginUserId());
            log.error(JacksonUtil.object2Json(excepLog));
            //exceptionLogService.addExceptionLog(excepLog);
        }
        catch (Exception e1)
        {
            log.error("记录日志错误 {} ", JacksonUtil.object2Json(excepLog));
        }

    }

    /**
     * 转换request 请求参数
     *
     * @param request
     */
    public String getRequestParamer(HttpServletRequest request, JoinPoint joinPoint)
    {
        Map<String, String[]> paramMap = request.getParameterMap();
        String requestParamer = "";
        //如果请求数据不在body里面
        if (paramMap.size() != 0)
        {
            Map<String, String> rtnMap = new HashMap<String, String>();
            for (String key : paramMap.keySet())
            {
                rtnMap.put(key, paramMap.get(key)[0]);
            }
            requestParamer = JacksonUtil.object2Json(rtnMap);
        }
        else
        {
            Object[] args = joinPoint.getArgs();
            if (args.length > 0 && args[0] instanceof MultipartFile)
            {
                MultipartFile file = (MultipartFile) joinPoint.getArgs()[0];
                HashMap<String, String> data = new HashMap<>();
                data.put("fileName", file.getName());
                return JacksonUtil.object2Json(data);
            }
            requestParamer = JacksonUtil.object2Json(joinPoint.getArgs());
        }
        return requestParamer;
    }

    /**
     * 转换异常信息为字符串
     *
     * @param exceptionName    异常名称
     * @param exceptionMessage 异常信息
     * @param elements         堆栈信息
     */
    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements)
    {
        StringBuffer strbuff = new StringBuffer();
        for (StackTraceElement stet : elements)
        {
            strbuff.append(stet + "\n");
        }
        String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
        return message;
    }
}
