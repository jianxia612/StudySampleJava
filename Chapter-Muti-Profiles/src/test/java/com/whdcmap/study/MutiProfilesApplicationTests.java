package com.whdcmap.study;

import com.whdcmap.study.service.ReadProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 *
 * @author 蜀山剑侠
 * @version 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={MutiProfilesApplication.class})
public class MutiProfilesApplicationTests {

	private static final Log log = LogFactory.getLog(MutiProfilesApplicationTests.class);

	@Autowired
	private ReadProperties readProperties;


	@Test
	public void test1() throws Exception {
		System.out.println("readProperties:\n "+readProperties.toString());
		Assert.assertEquals("蜀山剑侠", readProperties.getName());
		Assert.assertEquals("Spring Boot教程", readProperties.getTitle());
		Assert.assertEquals("蜀山剑侠正在努力写《Spring Boot教程》", readProperties.getDesc());

		log.info("随机数测试输出：");
		log.info("随机字符串 : " + readProperties.getValue());
		log.info("随机int : " + readProperties.getNumber());
		log.info("随机long : " + readProperties.getBignumber());
		log.info("随机10以下 : " + readProperties.getTest1());
		log.info("随机10-20 : " + readProperties.getTest2());

	}

}
