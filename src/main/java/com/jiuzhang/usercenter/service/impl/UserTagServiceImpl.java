package com.jiuzhang.usercenter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import com.jiuzhang.usercenter.dao.UserTagDao;
import com.jiuzhang.usercenter.pojo.UserTag;
import com.jiuzhang.usercenter.service.UserTagService;

/**
 * @author by plain yuan
 * @since 2020/04/13
 */
@Service
public class UserTagServiceImpl extends BaseServiceImpl<UserTag, String> implements UserTagService {

    @Autowired
    private UserTagDao userTagDao;

    @Override
    protected JpaRepository getRepository() {
        return userTagDao;
    }

    @Override
    public List<String> getUserTagList(String userId) {
        return userTagDao.getUserTagList(userId);
    }
}
