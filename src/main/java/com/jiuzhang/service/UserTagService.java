package com.jiuzhang.service;

import org.springframework.stereotype.Service;

import java.util.List;

import com.jiuzhang.pojo.UserTag;

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
