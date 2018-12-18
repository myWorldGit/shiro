package com.lanpangzi.app;

import com.lanpangzi.conf.shiro.OssService;
import com.lanpangzi.pojo.User;
import com.lanpangzi.service.redis.RedisClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RingApplicationTests {
	@Autowired
	OssService ossService;
	Logger logger = LoggerFactory.getLogger(getClass());
	@Test
	public void contextLoads() {

	}
}

