package com.jzsf.tuitor.common.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.jzsf.tuitor.rpcDomain.common.RespResult;
import com.jzsf.tuitor.rpcDomain.common.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
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
    RespResult handleException(Exception e) {
        e.printStackTrace();
        logger.error(e.getMessage());
        if (e instanceof JsonParseException) {
            return new RespResult(ResultCode.JSON_FORMAT_ERROR);
        } else if (e instanceof CustomException) {
            return new RespResult(((CustomException) e).getResultCode());
        } else if (e instanceof MissingServletRequestParameterException) {
            return new RespResult(ResultCode.REQ_PARAM_IS_BLANK);
        } else {
            return new RespResult(ResultCode.GENERAL_ERROR);
        }
    }

}