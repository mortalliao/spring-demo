package org.spring.learning.componentscan.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.spring.learning.componentscan.dao.UserDao;
import org.spring.learning.componentscan.util.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service // 业务层组件，默认bean的id是类名首字母小写 userService
@Scope("singleton") // <bean scope=""/> 指定作用域
@Lazy // <bean lazy-init=""/>  延迟初始化
public class UserService {
	
	//@Resource(name="userDao") // 字段注入
	@Autowired // 类型优先
	@Qualifier("userDao2") // 指定bean的id
	private UserDao userDao;
	
	@Resource
	private EmailUtils senderEmail;
	
	@Value("${user}") // 获取属性占位符的值
	private String username;
	
	public UserService(){
		System.out.println("====UserService====");
	}
	
	// @PostConstruct: 初始化方法 <bean init-method=""/>
	@PostConstruct
	public void init(){
		System.out.println("=========初始化方法===========");
	}
	
	// @PreDestroy: 消毁方法 <bean destroy-method=""/>
	@PreDestroy
	public void destroy(){
		System.out.println("========消毁方法=========");
	}
	
	public void saveUser(){
		System.out.println("======saveUser=======");
		System.out.println("username:" + username);
		senderEmail.send();
		userDao.save();
	}
}

