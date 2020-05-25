/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.jzsf.tuitor.base;

import com.jzsf.tuitor.rpcDomain.common.ResultCode;

/**
 * @author senyang
 * @version $Id: ValidateException.java, v 0.1 2020年04月24日 4:53 PM senyang Exp $
 */
public class ValidateException extends RuntimeException {

    public ValidateException(ResultCode code, String msg) {
        resultCode = code;
        errMsg = msg;
    }

    public String getErrLogStr() {
        return "{" + resultCode + "}:" + errMsg;
    }

    /**
     * Getter method for property <tt>resultCode</tt>.
     *
     * @return property value of resultCode
     */
    public ResultCode getResultCode() {
        return resultCode;
    }

    /**
     * Getter method for property <tt>errMsg</tt>.
     *
     * @return property value of errMsg
     */
    public String getErrMsg() {
        return errMsg;
    }

    private ResultCode resultCode;

    private String errMsg;


}