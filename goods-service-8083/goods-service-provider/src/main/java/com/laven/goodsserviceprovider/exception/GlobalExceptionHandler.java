package com.laven.goodsserviceprovider.exception;

import com.laven.api.R;
import com.laven.exception.ValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    public R handleException(Exception e, HttpServletRequest request) {
        log.info("GlobalExceptionHandler.handleException:{}, {}", request.getRequestURI(), e);
        String msg = "系统繁忙: " + e.getMessage();
        if (e instanceof ValidException) {
            msg = e.getMessage();
        }
        return new R.Builder().buildFailed(msg);
    }
}
