/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.jzsf.tuitor.common.stratege;

import com.jzsf.tuitor.common.utils.MD5Utils;
import com.jzsf.tuitor.common.utils.UUIDUtil;
import com.jzsf.tuitor.dao.UserDao;
import com.jzsf.tuitor.pojo.RegisterRecord;
import com.jzsf.tuitor.pojo.User;
import com.jzsf.tuitor.rpcdomain.common.RespResult;
import com.jzsf.tuitor.rpcdomain.common.ResultCode;
import com.jzsf.tuitor.rpcdomain.req.RegisterReq;
import com.jzsf.tuitor.service.RegisterRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author senyang
 * @version $Id: UserStrategeImpl.java, v 0.1 2020年04月24日 8:54 PM senyang Exp $
 */
@Service
public class RegestProcessingStrategeImpl implements UserStratege {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RegisterRecordService registerRecordService;

    @Override
    public RespResult doProcessor(RegisterReq req, OperatorStrategeEnum context) {
        if (context == OperatorStrategeEnum.SUCCESS) {
            // 保存注册记录
            RegisterRecord registerRecord = new RegisterRecord();
            registerRecord.setId(UUIDUtil.getUUID());
            registerRecord.setUsername(req.getUsername());
            registerRecord.setEmail(req.getEmail());
            registerRecord.setCaptcha(req.getCaptcha());
            registerRecord.setSendTime(new Date());
            registerRecordService.save(registerRecord);

            // 保存用户
            User user = new User();
            user.setId(UUIDUtil.getUUID());
            user.setUsername(req.getUsername());
            user.setPassword(MD5Utils.getMD5(req.getPassword()));
            user.setEmail(req.getEmail());
            userDao.save(user);
            return new RespResult(ResultCode.REGISTER_CAPTCHA_SEND);
        } else {
            return new RespResult(ResultCode.MAIL_SEND_FAIL);
        }
    }
}