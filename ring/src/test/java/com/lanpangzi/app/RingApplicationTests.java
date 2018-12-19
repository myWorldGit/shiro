package com.lanpangzi.app;

import com.lanpangzi.conf.service.OssService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RingApplicationTests {
	@Autowired
	ValueOperations ossService;
	Logger logger = LoggerFactory.getLogger(getClass());
	@Test
	public void contextLoads() {
		ossService.set("d","d");
	}
}

