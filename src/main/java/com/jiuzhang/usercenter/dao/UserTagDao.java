package com.jiuzhang.usercenter.dao;

import java.util.List;

import com.jiuzhang.usercenter.pojo.UserTag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author by plain yuan
 * @since 2020/04/13
 */
@Repository
public interface UserTagDao extends JpaRepository<UserTag, String> {
    /**
     * 根据用户id，获取用户标签
     *
     * @param userId
     * @return
     */
    @Query("select tagName from UserTag where userId=?1")
    List<String> getUserTagList(String userId);
}
