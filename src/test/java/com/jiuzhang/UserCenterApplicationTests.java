package com.jiuzhang;

import java.util.Date;

import com.jiuzhang.common.utils.JwtTokenUtil;
import com.jiuzhang.common.utils.UUIDUtil;
import com.jiuzhang.pojo.RegisterRecord;
import com.jiuzhang.service.RegisterRecordService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCenterApplicationTests {

    @Autowired
    RegisterRecordService registerRecordService;

    @Test
    public void quickTest() throws Exception {
        RegisterRecord record = new RegisterRecord();
        record.setCaptcha("1234");
        record.setEmail("17122366958@qq.com");
        record.setId(UUIDUtil.getUUID());
        Date date = new java.util.Date();
        record.setSendTime(date);
        registerRecordService.save(record);
        Assert.assertEquals("4", String.valueOf(registerRecordService.count()));
    }

    @Test
    public void contextLoads() {
        Assert.assertEquals(1, registerRecordService.checkRegister("somemail@mail.com", "程咬金"));
        Assert.assertEquals(2, registerRecordService.checkRegister("1229122464@qq.com", "sds"));
        Assert.assertEquals(0, registerRecordService.checkRegister("17122366958@163.com", "username"));
    }

    @Test
    public void jwtTest() {
        String token = JwtTokenUtil.createJWT("123", "456");
        Assert.assertEquals("123", JwtTokenUtil.getUserId(token));
    }

}
