package com.jiuzhang.usercenter.service;

import com.jiuzhang.usercenter.pojo.UserPreference;
import com.jiuzhang.usercenter.rpcdomain.common.RespResult;
import com.jiuzhang.usercenter.rpcdomain.req.UserPreferenceReq;
import com.jiuzhang.usercenter.rpcdomain.resp.UserPreferenceResp;

import org.springframework.stereotype.Service;

/**
 * @author by plain yuan
 * @since 2020/04/13
 */
@Service
public interface UserPreferenceService extends BaseService<UserPreference, String> {

    /**
     * 根据用户名，获取偏好设置
     *
     * @param userId
     * @return
     */
    RespResult<UserPreferenceResp> getByUserId(String userId);

    /**
     * 根据userId更新个人偏好设置
     *
     * @param req
     * @param userId
     * @return
     */
    RespResult updateSetting(UserPreferenceReq req, String userId);

}
