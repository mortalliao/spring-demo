package org.spring.learning.jdbc.dao;

import java.util.List;
import java.util.Map;

import org.spring.learning.jdbc.entity.Dept;

/**
 * 部门数据访问接口
 * 
 */
public interface DeptDao {
	/** 查询所有部门 */
	List<Dept> getDepts();

	/** 查询所有的部门名称 */
	public List<String> getNames();

	/** 查询id与name(多列) */
	List<Map<String, Object>> getIdAndName();

	/** 根据主键id做查询 */
	Dept getDept(long id);

	/** 添加部门 */
	void save(Dept dept);

	/** 修改部门 */
	void update(Dept dept);

	/** 删除部门 */
	void delete(int id);

	/** 统计查询 */
	int count();

}
