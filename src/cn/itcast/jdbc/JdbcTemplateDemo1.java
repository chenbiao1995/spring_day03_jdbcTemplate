package cn.itcast.jdbc;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JdbcTemplateDemo1 {
	//1添加操作
	@Test
	public void add() {
		//1设置数据库的信息
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql:///spring_day03");
		dataSource.setUsername("root");
		dataSource.setPassword("admin");
		//2创建jdbcTemplate 对象，设置数据源
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//3调用jdbcTemplate中的方法操作
		//创建sql语句
		String sql = "insert into user value(?,?)";
		int rows = jdbcTemplate.update(sql, "chen","000");
		System.out.println(rows);
	}
	//2修改操作
	@Test
	public void update() {
		//1设置数据库的信息
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql:///spring_day03");
		dataSource.setUsername("root");
		dataSource.setPassword("admin");
		//2创建jdbcTemplate 对象，设置数据源
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//3调用jdbcTemplate中的方法操作
		//创建sql语句
		String sql = "update user set password=? where username=?";
		int rows = jdbcTemplate.update(sql, "111","chen");

		System.out.println(rows);
	}
	//3 删除操作
		@Test
		public void delete() {
			//1设置数据库的信息
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql:///spring_day03");
			dataSource.setUsername("root");
			dataSource.setPassword("admin");
			//2创建jdbcTemplate 对象，设置数据源
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			//3调用jdbcTemplate中的方法操作
			//创建sql语句
			String sql = "delete from user where username=?";
			int rows = jdbcTemplate.update(sql, "chen");

			System.out.println(rows);
		}
		
/*		jdbcTemplate实现查询，有接口RowMapper
 * 		jdbcTmeplate针对这个接口没有提供实现类，得到不同的类型的数据需要自己进行数据封装
 * 		
*/		
}
