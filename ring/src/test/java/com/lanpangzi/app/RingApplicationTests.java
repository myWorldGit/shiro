package com.lanpangzi.app;

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
	RedisClientService redisService;
	Logger logger = LoggerFactory.getLogger(getClass());
	@Test
	public void contextLoads() {
	//	redisService.opsForList().leftPush("list",new User("3"));
		//redisService.putValue("shabi","laoge",300l);
		Map<Object,Object> map =new HashMap<>();
		map.put("1",1);
		map.put("2",2);
		map.put(3,4);
		map.put(5,7);
		map.put("15","21");
		map.put("61","111");
		List <Object> list =Arrays.asList(5,3,4);
		//List<Object> list =
		Object object=
		redisService.intersectSet("set","sett");
		logger.info(""+object.toString());
	}
}

