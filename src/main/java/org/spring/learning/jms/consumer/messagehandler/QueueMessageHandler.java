package org.spring.learning.jms.consumer.messagehandler;

import java.util.List;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("queueMessageHandler")
public class QueueMessageHandler {

	public void handle(String str) {
		log.info("---------------queueMessageHandler:" + str);
	}

	@JmsListener(destination = "queueList")
	public void handle2(List list) {
		for (Object o : list) {
			System.out.println(o);
		}
	}
}
