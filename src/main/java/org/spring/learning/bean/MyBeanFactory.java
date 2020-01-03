package org.spring.learning.bean;

/**
 * MyBeanFactory
 * 
 */
public class MyBeanFactory {

	/** 静态工厂方法 */
	public static TestService createUserService1() {
		System.out.println("=======createUserService1========");
		return new TestService();
	}

	/** 对象工厂方法 */
	public TestService createUserService2() {
		System.out.println("=======createUserService2========");
		return new TestService();
	}
}
