package org.spring.learning.jms.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:org/spring/learning/jms/producer/spring-jms.xml")
public class TopicSendTest {

	@Autowired
	private JmsOperations jmsOperations;

	@Test
	public void convertAndSend() {
		jmsOperations.convertAndSend("topicString", "Hello topic");
	}
}
