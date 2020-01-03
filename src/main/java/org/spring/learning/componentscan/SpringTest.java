package org.spring.learning.componentscan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.learning.componentscan.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // 指定运行的主类
@ContextConfiguration(locations = { "/spring.xml" }) // 加载spring的核心配置文件
public class SpringTest implements ApplicationContextAware {

	/** 定义ApplicationContext容器 */
	private ApplicationContext ac;

	@Test
	public void test1() {
		/** 从Spring容器中根据bean的id获取指定的bean */
		UserService userService = ac.getBean("userService", UserService.class);
		userService.saveUser();
	}

	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		System.out.println("========Spring容器初始化完成=============");
		System.out.println(ac);
		this.ac = ac;
	}
}
