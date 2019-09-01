package com.wanniwa.king.common.security.handler;


import com.wanniwa.king.common.core.constant.enums.ResultEnum;
import com.wanniwa.king.common.core.utils.R;
import com.wanniwa.king.common.exception.ResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

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
    public R resultException(ResultException e) {
        log.error(e.getMessage(), e);
        return R.error(e.getCode(), e.getMessage());
    }
    /**
     * 通用异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R exception(Exception e) {
        log.error("异常信息:{}", e.getMessage(), e);
        return R.error(ResultEnum.FAILED.getCode(),e.getMessage());
    }

    /**
     * validation Exception
     *
     * @param exception
     * @return R
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R handleBodyValidException(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        log.error("参数绑定异常,ex = {}", fieldErrors.get(0).getDefaultMessage());
        return R.error(fieldErrors.get(0).getDefaultMessage());
    }
}