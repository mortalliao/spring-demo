package org.spring.learning.jdbc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.spring.learning.jdbc.dao.DeptDao;
import org.spring.learning.jdbc.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 部门数据访问接口实现类
 */
@Repository
public class DeptDaoImpl implements DeptDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/** 查询所有部门 */
	public List<Dept> getDepts() {
		return jdbcTemplate.query("select  * from dept", new RowMapper<Dept>() {
			/** 映射一行数据 */
			@Override
			public Dept mapRow(ResultSet rs, int rowIndex) throws SQLException {
				System.out.println("rowIndex: " + rowIndex);
				Dept dept = new Dept();
				dept.setId(rs.getLong("id"));
				dept.setName(rs.getString("name"));
				dept.setRemark(rs.getString("remark"));
				return dept;
			}
		});
	}

	/** 查询所有的部门名称 */
	public List<String> getNames() {
		return jdbcTemplate.queryForList("select name from dept", String.class);
	}

	/** 查询id与name(多列) */
	public List<Map<String, Object>> getIdAndName() {
		return jdbcTemplate.queryForList("select id, name from dept");
	}

	/** 根据主键id做查询 */
	public Dept getDept(long id) {
		String sql = "select * from dept where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new RowMapper<Dept>() {
			@Override
			public Dept mapRow(ResultSet rs, int rowIndex) throws SQLException {
				Dept dept = new Dept();
				dept.setId(rs.getLong("id"));
				dept.setName(rs.getString("name"));
				dept.setRemark(rs.getString("remark"));
				return dept;
			}
		});
	}

	/** 统计查询 */
	public int count() {
		return jdbcTemplate.queryForObject("select count(*) from dept", Integer.class);
	}

	/** 添加部门 */
	public void save(Dept dept) {
		jdbcTemplate.update("insert into dept(name ,remark) values(?,?)",
				new Object[] { dept.getName(), dept.getRemark() });
	}

	/** 修改部门 */
	public void update(Dept dept) {
		jdbcTemplate.update("update dept set name = ?,remark = ? where id = ?",
				new Object[] { dept.getName(), dept.getRemark(), dept.getId() });
	}

	/** 删除部门 */
	public void delete(int id) {
		jdbcTemplate.update("delete from dept where id = ?", new Object[] { id });
	}

	/** Spring的setter注入 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
