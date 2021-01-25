package com.jiuzhang.service.impl;

import com.jiuzhang.dao.RegisterRecordDao;
import com.jiuzhang.pojo.RegisterRecord;
import com.jiuzhang.service.RegisterRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author by plain yuan
 * @since 2020/04/12
 */
@Service
public class RegisterRecordServiceImpl extends BaseServiceImpl<RegisterRecord, String>
        implements RegisterRecordService {

    @Autowired
    private RegisterRecordDao registerRecordDao;

    @Override
    protected JpaRepository<RegisterRecord, String> getRepository() {
        return registerRecordDao;
    }

    @Override
    public int checkRegister(String email, String username) {
        // 增强返回逻辑，指明被注册的详细信息
        return registerRecordDao.findByEmail(email).size() >= 1 ? 2
                : registerRecordDao.findByUsername(username) != null ? 1 : 0;
    }

    @Override
    public String getCaptchaByUsername(String username) {
        return registerRecordDao.findByUsername(username).getCaptcha();
    }
}
