package com.jzsf.tuitor.service.impl;

import com.jzsf.tuitor.dao.RegisterRecordDao;
import com.jzsf.tuitor.pojo.RegisterRecord;
import com.jzsf.tuitor.service.RegisterRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author by plain yuan
 * @since 2020/04/12
 */
@Service
public class RegisterRecordServiceImpl
        extends BaseServiceImpl<RegisterRecord, String>
        implements RegisterRecordService {

    @Autowired
    private RegisterRecordDao registerRecordDao;

    @Override
    protected JpaRepository<RegisterRecord, String> getRepository() {
        return registerRecordDao;
    }

    @Override
    public boolean checkRegister(String email) {
        return registerRecordDao.findByEmail(email).size() >= 1;
    }

    @Override
    public String getCaptchaByUsername(String username) {
        return registerRecordDao.findByUsername(username).getCaptcha();
    }
}
