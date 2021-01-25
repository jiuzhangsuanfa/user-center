package com.jiuzhang.usercenter.service;

import org.springframework.stereotype.Service;

import java.util.List;

import com.jiuzhang.usercenter.pojo.UserTag;

/**
 * @author by plain yuan
 * @since 2020/04/13
 */
@Service
public interface UserTagService extends BaseService<UserTag, String> {

    /**
     * 获取用户标签
     *
     * @param userId
     * @return
     */
    List<String> getUserTagList(String userId);

}
