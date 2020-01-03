package org.spring.learning.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class InitDestroyService implements InitializingBean, DisposableBean {

	// 调用构造器 1
	public InitDestroyService() {
		System.out.println("=====调用构造器=======");
	}

	private String name;
	private int age;

	// setter设值注入 2
	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	// 接口初始化方法 3
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("===接口方法初始化===afterPropertiesSet=======");
	}

	// 配置初始化方法 4 (init-method)
	public void init() {
		System.out.println("====配置方法初始化====init=======");
		if (this.name.equals("张明")) {
			this.age = 30;
		}
	}

	// 普通方法
	public void info() {
		System.out.println(name + "==" + age);
	}

	// 接口销毁方法 5
	@Override
	public void destroy() throws Exception {
		System.out.println("====销毁前调用接口方法====destroy========");
	}

	// 配置销毁方法 6 (destory-method)
	public void close() {
		System.out.println("====销毁前调用配置方法====close=======");
	}
}
