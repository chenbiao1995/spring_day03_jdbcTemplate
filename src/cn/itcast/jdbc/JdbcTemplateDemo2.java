package cn.itcast.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;



/**
 * title:
 * Description:
 * @author: 
 * @date:2018年1月30日
 * @time:下午1:24:18
 */
public class JdbcTemplateDemo2 {
	//1查询表中有多少条记录
	@Test
	public void testCount() {
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
		String sql = "SELECT COUNT(*) FROM user";
		//第一参数是sql 第二个是返回类型
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.println(count);
	}
	// 2 jadb实现代码
	@Test
	public void testJDBC() {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		//加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql:///spring_day03", "root", "admin");
		//编写sql语句
			String sql = "select * from user where username=?";
			//预编译sql
			psmt = conn.prepareStatement(sql);
			//设置参数值
			psmt.setString(1, "chen");
			//执行sql
			rs = psmt.executeQuery();
			//遍历结果集
			while (rs.next()) {
				//得到返回结果值
				String username = rs.getString("username");
				String password = rs.getString("password");
			//放到user对象里面
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				
				System.out.println(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				rs.close();
				conn.close();
				psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//3查询返回对象的形式
	@Test
	public void testObject() {
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
		String sql = "SELECT * from user where username=?";
		
		//第一参数是sql 第二个参数是接口，类似于dbutils里面接口，第三个参数是可变参数
		//第二个参数是接口rowMaper,需要自己写类实现接口，自己来做数据封装。
		User user = jdbcTemplate.queryForObject(sql, new MyRowMapper(), "dong");
		System.out.println(user);
	}
	//4查询返回对象的形式
		@Test
		public void testList() {
			
			//c3p0连接池
//			ComboPooledDataSource dataSource = new ComboPooledDataSource();
//			dataSource.setDriverClass("com.mysql.jdbc.Driver");
//			dataSource.setJdbcUrl("jdbc:mysql:///spring_day03");
//			dataSource.setUser("root");
//			dataSource.setPassword("admin");
			
			
			
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
			String sql = "SELECT * from user ";
			List<User> list = jdbcTemplate.query(sql,new MyRowMapper());
			System.out.println(list);
		}
}
class MyRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int num) throws SQLException {
		//1.从结果集中把数据得到
		String username = rs.getString("username");
		String password = rs.getString("password");
		//2.把数据封装到对象里面
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		return user;
	}
	
}