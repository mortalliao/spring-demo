package org.spring.learning.bean;

public class TestService {

	private TestDao testDao;

	public TestService() {
		System.out.println("==TestService==");
	}

	public TestService(TestDao testDao) {
		this.testDao = testDao;
	}

	public String echo(String str) {
		System.out.println(str);
		return str;
	}

	public String info(String info) {
		System.out.println("===testService===" + info);
		testDao.info();
		return info;
	}

	/**
	 * setter注入
	 */
	public void setTestDao(TestDao testDao) {
		System.out.println("setTestDao: " + testDao);
		this.testDao = testDao;
	}

}
