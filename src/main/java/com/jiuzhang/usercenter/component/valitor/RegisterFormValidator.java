/**
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.jiuzhang.usercenter.component.valitor;

import com.jiuzhang.usercenter.base.ValidateException;
import com.jiuzhang.usercenter.rpcdomain.common.RespResult;
import com.jiuzhang.usercenter.rpcdomain.common.ResultCode;
import com.jiuzhang.usercenter.rpcdomain.req.RegisterReq;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author senyang
 * @version $Id: RegisterFromValidator.java, v 0.1 2020年04月24日 1:01 AM senyang
 *          Exp $
 */
@Service("RegisterFormValidator")
public class RegisterFormValidator implements FormValidator {

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
        RegisterReq reqInfo = (RegisterReq) aInPkg;
        RespResult rspInfo = null;
        // 无效的注册
        if (StringUtils.isNotBlank(reqInfo.getCaptcha()) || StringUtils.isBlank(reqInfo.getEmail())
                || StringUtils.isBlank(reqInfo.getPassword()) || StringUtils.isBlank(reqInfo.getUsername())) {
            throw new ValidateException(ResultCode.REG_DATA_IS_WRONG, "表单数据错误");
        }
    }

}
