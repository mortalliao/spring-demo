package org.spring.learning.jms.consumer.messagehandler;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("topicMessageHandler")
public class TopicMessageHandler {

	public void handle(String str) {
		log.info("-----------------topicMessageHandler:" + str);
	}
}
