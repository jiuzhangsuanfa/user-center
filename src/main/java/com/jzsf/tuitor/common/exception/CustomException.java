package com.jzsf.tuitor.common.exception;

import java.text.MessageFormat;

/**
 * @author by plain yuan
 * @since 2020/04/13
 */

import com.jzsf.tuitor.rpcdomain.common.ResultCode;

/**
 * 自定义异常类型
 *
 * @author pyy
 **/
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误代码
     */
    final ResultCode resultCode;

    public CustomException(ResultCode resultCode) {
        super(resultCode.message());
        this.resultCode = resultCode;
    }

    public CustomException(ResultCode resultCode, Object... args) {
        super(resultCode.message());
        String message = MessageFormat.format(resultCode.message(), args);
        resultCode.setMessage(message);
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

}
