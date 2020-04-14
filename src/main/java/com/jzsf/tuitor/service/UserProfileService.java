package com.jzsf.tuitor.service;

import com.jzsf.tuitor.pojo.UserProfile;
import com.jzsf.tuitor.rpcDomain.RespResult;
import org.springframework.stereotype.Service;

/**
 * @author by plain yuan
 * @since 2020/04/13
 */
@Service
public interface UserProfileService extends BaseService<UserProfile, String> {
    /**
     * 根据用户id，获取用户基本数据
     *
     * @param userId
     * @return
     */
    RespResult getUserProfileInfo(String userId);
}
