package cn.ccsu.exception;

import cn.ccsu.response.RestResult;
import cn.ccsu.response.ResultCode;
import cn.ccsu.response.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description:基础全局异常处理类
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-02-05
 * @Time: 13:45
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler
{
    /*
     * 处理所有不可知的异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResult handleException(Exception exception)
    {
        log.error(exception.toString());
        return ResultUtil.error(ResultCode.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    /**
     * NoHandlerFoundException 404 异常处理
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public RestResult handlerNoHandlerFoundException(NoHandlerFoundException exception)
    {
        log.error(exception.toString());
        return ResultUtil.error(ResultCode.NOT_FOUND, exception.getMessage());
    }

    /**
     * MethodArgumentNotValidException Validation 校验参数错误异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResult handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception)
    {
        log.error(exception.toString());
        //解析原错误信息，封装后返回，此处返回非法的字段名称，错误信息
        List<Map<String,String>> validationErrorEntities = new ArrayList<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors())
        {
            Map<String,String> validationErrorEntity = new HashMap<>();
            validationErrorEntity.put("param",error.getField());
            validationErrorEntity.put("message",error.getDefaultMessage());
            validationErrorEntities.add(validationErrorEntity);
        }
        if (CollectionUtils.isEmpty(validationErrorEntities))
        {

        }
        return ResultUtil.error(ResultCode.PARAM_IS_INVALID, validationErrorEntities, ResultCode.PARAM_IS_INVALID.getMessage());
    }

    /**
     * FormRepeatException 自定义from表单重复提交 异常处理
     */
    @ExceptionHandler(value = FormRepeatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResult handlerFormRepeatException(FormRepeatException exception)
    {
        log.error(exception.toString());
        return ResultUtil.error(ResultCode.FORM_SAME_SUBMIT, exception.getMessage());
    }

    /**
     * HttpRequestMethodNotSupportedException 请求方法错误的 异常处理
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResult httpRequestMethodNotSupportedExceptionException(HttpRequestMethodNotSupportedException exception)
    {
        log.error(exception.toString());
        return ResultUtil.error(ResultCode.METHOD_NOT_ALLOWED, exception.getMessage());
    }


}
