package com.wanniwa.king.common.handler;

import com.wanniwa.king.common.enums.ResultEnum;
import com.wanniwa.king.common.exception.ResultException;
import com.wanniwa.king.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 *
 * @author wanniwa
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(ResultException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result ResultException(ResultException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exception(Exception e) {
        log.error("异常信息:{}", e.getMessage(), e);
        return Result.error(ResultEnum.FAILED.getCode(),e.getMessage());
    }
}