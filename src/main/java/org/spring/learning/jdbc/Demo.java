package org.spring.learning.jdbc;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.learning.jdbc.dao.DeptDao;
import org.spring.learning.jdbc.entity.Dept;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // 指定运行的主类
@ContextConfiguration(locations = { "spring-jdbc.xml" }) // 加载Spring的配置文件
//@ContextConfiguration("classpath:applicationContext.xml")
public class Demo implements ApplicationContextAware {

	/** 部门数据访问接口 */
	private DeptDao deptDao;

	/** 查询所有的部门 */
	@Test
	public void queryAll() {
		List<Dept> depts = deptDao.getDepts();
		System.out.println(depts);
	}

	/** 查询所有的部门名称(一列) */
	@Test
	public void queryNames() {
		List<String> names = deptDao.getNames();
		System.out.println(names);
	}

	/** 查询id与name(多列) */
	@Test
	public void queryIdAndName() {
		List<Map<String, Object>> lists = deptDao.getIdAndName();
		System.out.println(lists);
	}

	/** 带查询条件(根据id做查询) */
	@Test
	public void queryWhere() {
		Dept dept = deptDao.getDept(1);
		System.out.println(dept);
	}

	/** 添加部门 */
	@Test
	public void saveDept() {
		Dept dept = new Dept();
		dept.setName("总经办");
		dept.setRemark("总经办");
		deptDao.save(dept);
	}

	/** 修改部门 */
	@Test
	public void updateDept() {
		Dept dept = new Dept();
		dept.setId(3);
		dept.setName("总经办2");
		dept.setRemark("总经办2");
		deptDao.update(dept);
	}

	/** 删除部门 */
	@Test
	public void deleteDept() {
		deptDao.delete(3);
	}

	/** 删除部门 */
	@Test
	public void count() {
		int count = deptDao.count();
		System.out.println("count: " + count);
	}

	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		this.deptDao = ac.getBean("deptDao", DeptDao.class);
	}
}
