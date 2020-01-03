package org.spring.learning.jdbc;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.learning.jdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class) // 指定运行的主类
@ContextConfiguration(locations = { "spring-jdbc.xml" }) // 加载Spring的配置文件
public class TransactionTest {

	@Autowired
	private UserService userService;

	@Test
	public void testUser() {
		if (userService.change(1L, new BigDecimal(20))) {
			System.out.println("success");
		} else {
			System.out.println("failed");
		}
	}

	@Test
	@Transactional
	public void testUserTransaction() {
		userService.change(1L, new BigDecimal(20));
		similateError();
	}

	public void similateError() {
		throw new RuntimeException();
	}
}
