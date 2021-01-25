/**
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.jiuzhang.usercenter.component.valitor;

import com.jiuzhang.usercenter.base.ValidateException;
import com.jiuzhang.usercenter.rpcdomain.common.ResultCode;
import com.jiuzhang.usercenter.rpcdomain.req.RegisterReq;
import com.jiuzhang.usercenter.service.RegisterRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author senyang
 * @version $Id: RegisterStatusFormValidator.java, v 0.1 2020年04月24日 1:22 PM
 *          senyang Exp $
 */
@Service("RegisterStatusFormValidator")
public class RegisterStatusFormValidator implements FormValidator {

    @Autowired
    private RegisterRecordService registerRecordService;

    /**
     * @param inPkg
     * @return
     */
    @Override
    public boolean canAccept(Object inPkg) {
        if (inPkg instanceof RegisterReq) {
            return true;
        }
        return false;
    }

    /**
     * @param aInPkg
     */
    @Override
    public void validate(Object aInPkg) {
        // 检查是否注册
        RegisterReq reqInfo = (RegisterReq) aInPkg;
        int registerCode = registerRecordService.checkRegister(reqInfo.getEmail(), reqInfo.getUsername());
        if (registerCode == 1) {
            throw new ValidateException(ResultCode.USERNAME_HAS_USED, "用户名已被占用");
        } else if (registerCode == 2) {
            throw new ValidateException(ResultCode.MAIL_HAS_USED, "邮箱已被占用");

        }
    }
}
