package org.spring.learning.jdbc.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class User {

	private Long id;
	private String name;
	private String password;
	private BigDecimal money;
}
