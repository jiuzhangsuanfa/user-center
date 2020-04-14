package com.jzsf.tuitor;

import com.jzsf.tuitor.common.utils.MD5Utils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author by plain yuan
 * @since 2020/04/12
 */

public class a {


    @Test
    public void testmd5() throws Exception {
        String pas1 = "admin123";
        String pas2 = "admin";
        Assert.assertEquals(32, MD5Utils.getMD5(pas1));
        Assert.assertNotEquals(MD5Utils.getMD5(pas2), MD5Utils.getMD5(pas1));
    }


}
