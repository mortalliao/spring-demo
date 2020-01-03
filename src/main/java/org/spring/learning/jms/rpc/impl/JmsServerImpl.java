package org.spring.learning.jms.rpc.impl;

import org.spring.learning.jms.rpc.JmsServer;
import org.springframework.stereotype.Component;

@Component("jmsServerImpl")
public class JmsServerImpl implements JmsServer {

	@Override
	public void doServer(String str) {
		System.out.println("your message::".concat(str).concat(":::length:") + str.length());
	}
}
