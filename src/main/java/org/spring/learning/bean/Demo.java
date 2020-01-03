package org.spring.learning.bean;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {

	@Test
	public void testCreate() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"org/spring/learning/bean/spring-bean-create.xml");
		TestService testService = context.getBean(TestService.class);
		testService.echo("Hello world");

		TestService testService1 = context.getBean("testService1", TestService.class);
		testService1.echo("testService1");

		TestService testService2 = (TestService) context.getBean("testService2");
		testService2.echo("testService2");
	}

	@Test
	public void testAutowire() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"org/spring/learning/bean/spring-bean-autowire.xml");
		TestService testService = context.getBean(TestService.class);
		testService.info("Hello World");
	}
}
