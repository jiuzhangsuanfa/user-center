package com.jiuzhang.usercenter.dao;

import com.jiuzhang.usercenter.pojo.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author by plain yuan
 * @since 2020/04/12
 */
@Repository
public interface AddressDao extends JpaRepository<Address, String> {

}
