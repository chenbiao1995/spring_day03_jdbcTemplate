package cn.itcast.c3p0;

import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {
	
	//得到JdbcTemplate对象
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public void add() {
		//创建jdbcTemplate对象
		//JdbcTemplate jabcTemplate = new JdbcTemplate(dataSource);
		String sql = "insert into user value(?,?)";
		jdbcTemplate.update(sql, "lily","520");
	
	}
}
