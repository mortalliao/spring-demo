package org.spring.learning.aop;

import org.junit.Test;
import org.spring.learning.aop.service.AdminService;
import org.spring.learning.aop.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {

	@Test
	public void testAop() {
		/** 获取Spring容器 */
		ApplicationContext context = new ClassPathXmlApplicationContext("org/spring/learning/aop/spring-aop.xml");
		System.out.println("=======spring容器初始化完成=======");

		/** 从Spring容器中获取Bean */
		AdminService adminService = context.getBean("adminService", AdminService.class);
		String res = adminService.login("admin", "123456");
		System.out.println(res);

		UserService userService = context.getBean("userService", UserService.class);
		userService.say();
	}
}
