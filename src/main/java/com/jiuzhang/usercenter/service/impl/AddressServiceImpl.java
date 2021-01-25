package com.jiuzhang.usercenter.service.impl;

import com.jiuzhang.usercenter.dao.AddressDao;
import com.jiuzhang.usercenter.pojo.Address;
import com.jiuzhang.usercenter.service.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author by plain yuan
 * @since 2020/04/13
 */
@Service
public class AddressServiceImpl extends BaseServiceImpl<Address, String> implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Override
    protected JpaRepository getRepository() {
        return addressDao;
    }

}
