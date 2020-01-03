package org.spring.learning.componentscan;

import java.util.Arrays;

import org.junit.Test;
import org.spring.learning.componentscan.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {

	@Test
	public void test1() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"org/spring/learning/componentscan/spring.xml");
		UserService userService = context.getBean(UserService.class);
		userService.saveUser();

		context.close();
	}

	@Test
	public void test() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScanConfig.class);
		String[] beanDefinitionNames = context.getBeanDefinitionNames();
		Arrays.asList(beanDefinitionNames).stream().forEach(x -> System.out.println("扫描到的Bean---" + x));
		
		UserService userService = context.getBean(UserService.class);
		userService.saveUser();

		context.close();
	}
}
