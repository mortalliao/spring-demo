package org.spring.learning.properties;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {

	@Test
	public void testResource() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"org/spring/learning/properties/spring-resource.xml");
		ResourceService resourceService = context.getBean(ResourceService.class);
		String user = (String) resourceService.getResource().get("user");
		System.out.println(user);
	}

}
