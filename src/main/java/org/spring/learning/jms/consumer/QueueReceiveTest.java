package org.spring.learning.jms.consumer;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("spring-jms-receive.xml")
public class QueueReceiveTest {

	@Autowired
	private JmsOperations jmsOperations;

	/**
	 * jmsOperations 的 receiveAndConvert() 方法
	 */
	@Test
	public void receiveAndConvert() {
		List<String> queueName = (List) jmsOperations.receiveAndConvert("queueList");
		for (String s : queueName) {
			System.out.println("-----------" + s + "-------------");
		}
	}

	/**
	 * jmsOperations 的 receive() 方法
	 */
	@Test
	public void receive() {
		MapMessage message = (MapMessage) jmsOperations.receive("queueMap");
		try {
			String java = message.getString("java");
			System.out.println("----------" + java + "----------");
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
