package org.spring.learning.componentscan.dao;

import org.springframework.stereotype.Repository;

@Repository("userDao2") // 数据访问层 <bean id="userDao"/>  默认bean的id是类名首字母小写 userDao
public class UserDao {

	public UserDao() {
		System.out.println("==UserDao==");
	}

	public void save() {
		System.out.println("=========save==========");
	}
}
