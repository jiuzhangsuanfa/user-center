package com.jzsf.tuitor.dao;

import com.jzsf.tuitor.pojo.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author by plain yuan
 * @since 2020/04/13
 */
@Repository
public interface UserTagDao extends JpaRepository<UserTag, String> {
}
