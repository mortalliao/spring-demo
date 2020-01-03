package org.spring.learning.bean;

import org.springframework.beans.factory.InitializingBean;

public class InitBean implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet invoke......");
	}

	public void init() {
		System.out.println("init invoke......");
	}

}
