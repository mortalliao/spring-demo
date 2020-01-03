package org.spring.learning.jdbc.service.impl;

import java.math.BigDecimal;

import org.spring.learning.jdbc.dao.UserDao;
import org.spring.learning.jdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public boolean change(Long userId, BigDecimal reduceMoney) {
		return userDao.change(userId, reduceMoney);
	}

}
