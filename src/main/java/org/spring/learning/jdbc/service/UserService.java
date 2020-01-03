package org.spring.learning.jdbc.service;

import java.math.BigDecimal;

public interface UserService {

	boolean change(Long userId, BigDecimal reduceMoney);
}
