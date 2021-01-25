package com.jiuzhang.usercenter.dao;

import com.jiuzhang.usercenter.pojo.UserPreference;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author by plain yuan
 * @since 2020/04/13
 */
@Repository
public interface UserPreferenceDao extends JpaRepository<UserPreference, String> {
}
