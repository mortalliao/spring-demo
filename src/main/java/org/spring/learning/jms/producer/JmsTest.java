package org.spring.learning.jms.producer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;

public class JmsTest {

	@Test
	public void producer() {
		ConnectionFactory cf = new ActiveMQConnectionFactory("admin", "admin", "tcp://192.168.99.100:61617");
		Connection conn = null;
		Session session = null;
		try {
			conn = cf.createConnection();
			conn.start();
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = new ActiveMQQueue("queueTest");
			MessageProducer producer = session.createProducer(destination);
			TextMessage message = session.createTextMessage("Hello Message");
			producer.send(message);
			conn.stop();
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			try {
				if (session != null) {
					session.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void consumer() {
		ConnectionFactory cf = new ActiveMQConnectionFactory("admin", "admin", "tcp://192.168.99.100:61617");
		Connection conn = null;
		Session session = null;
		try {
			conn = cf.createConnection();
			conn.start();
			session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
			Destination destination = new ActiveMQQueue("queueTest");
			MessageConsumer consumer = session.createConsumer(destination);
			Message receive = consumer.receive(5000L);
			TextMessage message = (TextMessage) receive;
			if (message != null) {
				System.out.println("---------------" + message.getText() + "------------------");
			}
			session.commit();
			conn.stop();
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			try {
				if (session != null) {
					session.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
