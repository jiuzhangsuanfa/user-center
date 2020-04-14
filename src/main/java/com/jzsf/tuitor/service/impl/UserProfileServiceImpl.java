package com.jzsf.tuitor.service.impl;

import com.jzsf.tuitor.dao.UserProfileDao;
import com.jzsf.tuitor.pojo.User;
import com.jzsf.tuitor.pojo.UserProfile;
import com.jzsf.tuitor.rpcDomain.RespResult;
import com.jzsf.tuitor.rpcDomain.ResultCode;
import com.jzsf.tuitor.service.UserProfileService;
import com.jzsf.tuitor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author by plain yuan
 * @since 2020/04/13
 */
@Service
public class UserProfileServiceImpl extends BaseServiceImpl<UserProfile, String>
        implements UserProfileService {

    @Autowired
    private UserProfileDao userProfileDao;

    @Autowired
    private UserService userService;

    @Override
    protected JpaRepository getRepository() {
        return userProfileDao;
    }

    @Override
    public RespResult getUserProfileInfo(String userId) {
        Optional<User> user = userService.findById(userId);
        if (!user.isPresent()) {
            return new RespResult(ResultCode.USER_NOT_EXIST);
        }
        UserProfile userProfile = userProfileDao.getOne(userId);

        RespResult respResult = new RespResult(ResultCode.SUCCESS);

        return respResult;
    }
}
