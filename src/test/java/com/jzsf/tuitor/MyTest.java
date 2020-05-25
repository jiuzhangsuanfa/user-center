package com.jzsf.tuitor;

import com.jzsf.tuitor.common.utils.JwtTokenUtil;
import com.jzsf.tuitor.common.utils.MD5Utils;
import com.jzsf.tuitor.dao.UserDao;
import com.jzsf.tuitor.pojo.Address;
import com.jzsf.tuitor.rpcDomain.req.UserProfileReq;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author by plain yuan
 * @since 2020/04/12
 */

public class MyTest {


    @Autowired
    private UserDao userDao;

    @Test
    public void testmd5() throws Exception {
        String pas1 = "admin123";
        String pas2 = "admin";
        Assert.assertEquals(32, MD5Utils.getMD5(pas1).length());
        Assert.assertNotEquals(MD5Utils.getMD5(pas2), MD5Utils.getMD5(pas1));

        Address address = new Address();
        address.setStreetAddress("西湖区西溪谷崇仁街");
        address.setCity("杭州市");
        address.setProvince("浙江省");
        address.setCountry("China");
        UserProfileReq req = new UserProfileReq();
        BeanUtils.copyProperties(address, req);
        System.out.println(req);
    }

    @Test
    public void jwtTest() {
        String jwt = JwtTokenUtil.createJWT("123", "456");
        System.out.println(jwt);
    }

}
