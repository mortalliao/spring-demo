package org.spring.learning.jms.rpc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("spring-jms.xml")
public class RpcTest {

	@Test
	public void test01() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		JmsServer service = (JmsServer) context.getBean("jmsServerProxy");
		service.doServer("Hello Message");
	}

}
