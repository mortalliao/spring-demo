package org.spring.learning.jms.producer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <pre>
 * convertAndSend() 和 receiveAndConvert() 方法 如果不指定 消息通道名称，
 *	则采用JmsTemplate 默认设置的，即 defaultDestination 关联的消息目的地中的消息通道。
 *  
 *  convertAndSend() 和 receiveAndConvert() 方法 能便捷的实现 发送和接收消息功能，原因是 消息转换器 
 * 	发送时，JmsTemplate 先把消息内容转换成对应Message；
 *	接收时，JmsTemplate 再把对应Message 转换回消息内容。
 *	
 *	JmsTemplate 定义了多个消息转换器
 *	SimpleMessageConverter 转换器，也就是 JmsTemplate 中默认使用的转换器(不设置用的就是这个转换器)
 *	如果需要，可以自定义转换器
 * 
 *	除了 convertAndSend() 和 receiveAndConvert() 方法，
 *	JmsTempalte 还支持 send() 和 receive() 方法来发送和接收消息，
 *	就是写起来麻烦点，还要自己处理 JMSException。
 * 
 *	使用JmsTemplate接收消息的最大缺点在于receive()和receiveAndConvert()方法都是同步的
 * </pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("spring-jms.xml")
public class QueueSendTest {

	@Autowired
	private JmsOperations jmsOperations;

	/**
	 * jmsOperations.send() 方法，"queue" 不填写，用默认的 Destination
	 */
	@Test
	public void send() {
		jmsOperations.send("queueList", new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				List<String> list = new ArrayList<>();
				list.add("java");
				list.add("python");
				list.add("c++");
				return session.createObjectMessage((Serializable) list);
			}
		});
	}

	/**
	 * jmsOperations.convertAndSend() 方法，"queue" 不填写，用默认的 Destination
	 */
	@Test
	public void convertAndSend() {
		Map<String, Object> map = new HashMap<>(16);
		map.put("java", "java");
		map.put("python", "python");
		map.put("c++", "c++");
		jmsOperations.convertAndSend("queueMap", map);
	}

}
