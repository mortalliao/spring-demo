package org.spring.learning.jdbc.dao;

import java.math.BigDecimal;

public interface UserDao {

	boolean change(Long userId, BigDecimal reduceMoney);
}
