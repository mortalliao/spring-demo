package org.spring.learning.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import lombok.Data;

@Data
public class User {

	private String name;
	private int age;
	private float[] floats;
	private List<String> lists;
	private Set<String> sets;
	private Map<String, String> maps;
	private Properties props;
	private Date date;
}
