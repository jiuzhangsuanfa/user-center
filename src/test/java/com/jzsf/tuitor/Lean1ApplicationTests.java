package com.jzsf.tuitor;

import com.jzsf.tuitor.pojo.RegisterRecord;
import com.jzsf.tuitor.service.RegisterRecordService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Lean1ApplicationTests {

	@Autowired
	RegisterRecordService registerRecordService;

	@Test
	public void quickTest() throws Exception {
		RegisterRecord record = new RegisterRecord();
		record.setCaptcha("1234");
		record.setEmail("17122366958@qq.com");
		record.setId(registerRecordService.getUUID());
		Date date = new java.util.Date();
		record.setSendTime(date);
		registerRecordService.save(record);
		Assert.assertEquals("4", registerRecordService.count());
	}

	@Test
	public void contextLoads() {
		boolean b = registerRecordService.checkRegister("1229122464@qq.com");
	}

}
