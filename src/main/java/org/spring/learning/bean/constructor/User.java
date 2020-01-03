package org.spring.learning.bean.constructor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import lombok.Data;

@Data
public class User {

	private String name;
	private Integer age;

	public User(String name) {
		this.name = name;
	}

	public User(String name, int age) {
		this.age = age;
	}

	/** 数组类型String[] */
	private String[] cities;

	/** List集合 */
	private List<String> strings;

	public User(String[] cities, int[] ints) {
		this.cities = cities;
		System.out.println(Arrays.toString(cities));
		System.out.println(Arrays.toString(ints));
	}

	public User(List<String> strings, List<Integer> ints) {
		this.strings = strings;
		System.out.println(strings);
		System.out.println("=========List<String>=========");
		System.out.println(ints);
	}

	/** List<String[]> */
	private List<String[]> lists;

	public User(List<String[]> lists) {
		System.out.println("==============List<String[]>================");
		this.lists = lists;
		for (String[] arr : lists) {
			System.out.println(Arrays.toString(arr));
		}
	}

	/** Set集合 */
	private Set<String> set;

	/** List<Set<String>> */
	private List<Set<String>> listSets;

	public User(Set<String> set, List<Set<Integer>> listSets) {
		this.set = set;
		System.out.println(set);
		System.out.println("=========Set<String>  List<Set<String>>=========");
		System.out.println(listSets);
	}

	/** Properties集合 */
	private Properties props;

	public User(Properties props) {
		System.out.println(props);
		System.out.println("=========Properties=========");
	}

	/** Date */
	private Date date;

	public User(Date date, String beanId) {
		this.date = date;
		System.out.println(date);
		System.out.println("=========Date=========");
		System.out.println(beanId);
	}
	
	public User(String name, int age, Date date){
		this.name = name;
		System.out.println("===String name, int age, Date date==");
		System.out.println(name + "==" + age + "==" + date);
	}
}
