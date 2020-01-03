package org.spring.learning.jms.consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("spring-jms-receive.xml")
public class TopicReceiveTest {

	@Autowired
	private JmsOperations jmsOperations;

	@Test
	public void receiveAndConvert() {
		String topicName = (String) jmsOperations.receiveAndConvert("topicString");
		System.out.println("--------------------------" + topicName + "-------------------------");
	}

}
