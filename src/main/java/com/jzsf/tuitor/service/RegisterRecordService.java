package com.jzsf.tuitor.service;

import com.jzsf.tuitor.pojo.RegisterRecord;
import org.springframework.stereotype.Service;

/**
 * @author by plain yuan
 * @since 2020/04/12
 */

@Service
public interface RegisterRecordService extends BaseService<RegisterRecord, String> {

    /**
     * 查看当前邮箱是否被注册，大于等于1条已被注册
     *
     * @param email
     * @return
     */
    boolean checkRegister(String email);

    /**
     * 根据用户名获取注册时的验证码
     *
     * @param username
     * @return
     */
    String getCaptchaByUsername(String username);
}
