package com.jiuzhang.usercenter.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.jiuzhang.usercenter.dao.UserProfileDao;
import com.jiuzhang.usercenter.pojo.Address;
import com.jiuzhang.usercenter.pojo.User;
import com.jiuzhang.usercenter.pojo.UserPreference;
import com.jiuzhang.usercenter.pojo.UserProfile;
import com.jiuzhang.usercenter.rpcdomain.common.RespResult;
import com.jiuzhang.usercenter.rpcdomain.common.ResultCode;
import com.jiuzhang.usercenter.rpcdomain.req.UserProfileReq;
import com.jiuzhang.usercenter.rpcdomain.resp.UserProfileResp;
import com.jiuzhang.usercenter.service.AddressService;
import com.jiuzhang.usercenter.service.UserPreferenceService;
import com.jiuzhang.usercenter.service.UserProfileService;
import com.jiuzhang.usercenter.service.UserService;

/**
 * @author by plain yuan
 * @since 2020/04/13
 */
@Service
public class UserProfileServiceImpl extends BaseServiceImpl<UserProfile, String> implements UserProfileService {

    @Autowired
    private UserProfileDao userProfileDao;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserPreferenceService userPreferenceService;

    @Override
    protected JpaRepository getRepository() {
        return userProfileDao;
    }

    @Override
    public RespResult getUserProfileInfo(String userId) {
        Optional<User> userOptional = userService.findById(userId);
        if (!userOptional.isPresent()) {
            return new RespResult(ResultCode.USER_NOT_EXIST);
        }
        User user = userOptional.get();
        UserProfile userProfile = userProfileDao.findById(userId).get();
        Address address = addressService.findById(userId).get();
        UserPreference userPreference = userPreferenceService.findById(userId).get();

        UserProfileResp respInfo = new UserProfileResp();

        BeanUtils.copyProperties(user, respInfo);
        BeanUtils.copyProperties(address, respInfo);
        BeanUtils.copyProperties(userProfile, respInfo);
        BeanUtils.copyProperties(userPreference, respInfo);

        return new RespResult(ResultCode.SUCCESS, respInfo);
    }

    @Override
    public RespResult updateUserProfile(String userId, UserProfileReq userProfileReq) {
        Optional<User> userOptional = userService.findById(userId);
        if (!userOptional.isPresent()) {
            return new RespResult(ResultCode.USER_NOT_EXIST);
        }

        User user = userOptional.get();
        Address address = addressService.findById(userId).get();
        UserProfile userProfile = userProfileDao.findById(userId).get();
        UserPreference userPreference = userPreferenceService.findById(userId).get();

        BeanUtils.copyProperties(userProfileReq, user);
        BeanUtils.copyProperties(userProfileReq, address);
        BeanUtils.copyProperties(userProfileReq, userProfile);
        BeanUtils.copyProperties(userProfileReq, userPreference);

        userService.save(user);
        addressService.save(address);
        userProfileDao.save(userProfile);
        userPreferenceService.save(userPreference);

        return new RespResult(ResultCode.SUCCESS);
    }

}
