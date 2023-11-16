package com.lin.linsux.service.handler;

import com.lin.linsux.model.exception.CommonException;
import com.lin.linsux.model.vo.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>TODO</p>
 *
 * @author linsz
 * @version v1.0
 * @date 2023/10/30 19:02
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<String> exceptionHandler(Exception e) {
        return Result.build(null,201,"出现异常:"+e.getMessage());
    }

    @ExceptionHandler(CommonException.class)
    public Result commonExceptionHandler(CommonException e) {
        return Result.build(null,e.getResultCodeEnum());
    }
}
