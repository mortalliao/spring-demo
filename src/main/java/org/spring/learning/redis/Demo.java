package org.spring.learning.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("spring-redis.xml")
public class Demo {

	@Autowired
	RedisService redisSerive;

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Test
	public void testRedisStr() {
		redisSerive.set("welcome:hello", "world");
		Object object = redisTemplate.opsForValue().get("welcome:hello");
		System.out.println(object);
	}

}
