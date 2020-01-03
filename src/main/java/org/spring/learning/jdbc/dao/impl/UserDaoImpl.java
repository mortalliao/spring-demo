package org.spring.learning.jdbc.dao.impl;

import java.math.BigDecimal;

import org.spring.learning.jdbc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean change(Long userId, BigDecimal reduceMoney) {
		return jdbcTemplate.update("update user set money=money-? where id=? and money > ?",
				new Object[] { reduceMoney, userId, reduceMoney }) > 0;
	}

}
