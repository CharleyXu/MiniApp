package com.xu.miniapp.exception;

import com.xu.miniapp.common.ResponseMessage;
import javax.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author CharleyXu Created on 2019/1/10.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseMessage<String> handler(Exception e) {

        if (e instanceof ConnectionException) {
            log.error("[连接超时] {}", e.getMessage(), e);
            return ResponseMessage.error(ErrorCode.CONNECTION_ERROR);
        }

        if (e instanceof WxErrorException) {
            log.error("", e.getMessage(), e);
            return ResponseMessage.error(ErrorCode.WX_API_ERROR);
        }

        if (e instanceof ValidationException || e instanceof BindException
            || e instanceof ServletRequestBindingException) {
            log.error("[参数不合法] {}", e.getMessage());
            return ResponseMessage.error(ErrorCode.PARAM_ILLEGAL);
        }

        log.error("[系统异常] {}", e);
        return ResponseMessage.error(ErrorCode.SYSTEM_ERROR);
    }

}
