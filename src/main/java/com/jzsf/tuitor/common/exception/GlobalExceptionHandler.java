package com.jzsf.tuitor.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author by plain yuan
 * @since 2020/04/13
 * Controller全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    @ResponseBody
    String handleException(Exception e) {
        logger.error(e.getMessage());
        return "服务器开小差了，过会儿再试试吧ლ(′◉❥◉｀ლ)";
    }

}