package org.spring.learning.aop.service;

public class AdminService {

	public String login(String userId, String pwd){
		System.out.println(userId + "==" + pwd);
		
		//int i = 10 / 0;
		
		return userId + ",登录成功！";
	}
	
	public String login(String userId){
		System.out.println(userId);
		return userId;
	}
}
