package com.jzsf.tuitor.service;

import com.jzsf.tuitor.pojo.UserPreference;
import com.jzsf.tuitor.rpcDomain.NoticeConfigReq;
import com.jzsf.tuitor.rpcDomain.RespResult;
import org.springframework.stereotype.Service;

/**
 * @author by plain yuan
 * @since 2020/04/13
 */
@Service
public interface UserPreferenceService extends BaseService<UserPreference, String> {

    /**
     * 根据用户名，获取实体
     *
     * @param username
     * @return
     */
    RespResult<UserPreference> getByUserId(String username);

    /**
     * 更新实体
     *
     * @param noticeConfigReq
     * @return
     */
    RespResult updateSetting(NoticeConfigReq noticeConfigReq);

}
