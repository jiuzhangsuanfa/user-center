package com.jiuzhang.usercenter.rpcdomain.common;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author by plain yuan
 * @since 2020/04/13 响应结果实体
 */
@JsonSerialize
public class RespResult<T> implements Serializable {

    private static final long serialVersionUID = 623922752035245707L;

    /**
     * 操作代码
     */
    int code;

    /**
     * 提示信息
     */
    String message;

    /**
     * 结果数据
     */
    T data;

    public RespResult() {
    }

    public RespResult(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    public RespResult(ResultCode resultCode, T data) {
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.data = data;
    }

    public RespResult(String message) {
        this.message = message;
    }

    public static RespResult<ResultCode> success() {
        return new RespResult<>(ResultCode.SUCCESS);
    }

    public static <T> RespResult<T> success(T data) {
        return new RespResult<>(ResultCode.SUCCESS, data);
    }

    public static RespResult<ResultCode> fail() {
        return new RespResult<>(ResultCode.FAIL);
    }

    public static RespResult<String> fail(String message) {
        return new RespResult<>(message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
