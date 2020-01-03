package org.spring.learning.componentscan.util;

import org.springframework.stereotype.Component;

@Component
public class EmailUtils {

	public void send(){
		System.out.println("=======send email========");
	}
}
