/**
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.jiuzhang.usercenter.common.stratege;

/**
 * @author senyang
 * @version $Id: OperatorStratege.java, v 0.1 2020年04月24日 8:50 PM senyang Exp $
 */
public enum OperatorStrategeEnum {
    /**
     * 成功
     */
    SUCCESS("SUCCESS", "成功"),

    /**
     * 失败
     */
    FAIL("FAIL", "失败"),

    /**
     * 未知
     */
    UNKNOWN("UNKNOWN", "未知"),

    /**
     * 验证码失败
     */
    EMAILFAIL("EMAILFAIL", "验证码发送失败");

    /**
     * code
     */
    private String code;

    /**
     * 描述
     */
    private String desc;

    OperatorStrategeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
