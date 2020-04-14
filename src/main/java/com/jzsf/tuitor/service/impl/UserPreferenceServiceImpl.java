package com.jzsf.tuitor.service.impl;

import com.jzsf.tuitor.dao.UserPreferenceDao;
import com.jzsf.tuitor.pojo.User;
import com.jzsf.tuitor.pojo.UserPreference;
import com.jzsf.tuitor.rpcDomain.NoticeConfigReq;
import com.jzsf.tuitor.rpcDomain.RespResult;
import com.jzsf.tuitor.rpcDomain.ResultCode;
import com.jzsf.tuitor.service.UserPreferenceService;
import com.jzsf.tuitor.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author by plain yuan
 * @since 2020/04/13
 */
@Service
public class UserPreferenceServiceImpl
        extends BaseServiceImpl<UserPreference, String>
        implements UserPreferenceService {

    @Autowired
    UserPreferenceDao userPreferenceDao;

    @Autowired
    UserService userService;

    @Override
    protected JpaRepository getRepository() {
        return userPreferenceDao;
    }

    @Override
    public RespResult<UserPreference> getByUserId(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return new RespResult<>(ResultCode.PARAM_IS_BLANK);
        }
        Optional<User> user = userService.findById(userId);
        if (!user.isPresent()) {
            return new RespResult(ResultCode.FAIL, "没有查到该用户的设置信息");
        }
        Optional<UserPreference> entity = userPreferenceDao.findById(user.get().getId());
        return new RespResult<>(ResultCode.SUCCESS, entity.get());
    }

    @Override
    public RespResult updateSetting(NoticeConfigReq noticeConfigReq) {
        UserPreference userPreference = new UserPreference();
        userPreference.setOtherUserMessageNotice(noticeConfigReq.getOtherUserMessageNotice());
        userPreference.setTodoNotice(noticeConfigReq.getTodoNotice());
        userPreference.setSysMessageNotice(noticeConfigReq.getSysMessageNotice());
        userPreference.setUserId(noticeConfigReq.getUserId());
        userPreferenceDao.save(userPreference);
        return new RespResult(ResultCode.SUCCESS);
    }
}
