package AOP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DataSourceTests {

	
	@Autowired //의존성 주입???? 찾아보기!!
	DataSource ds;
	
	@Test
	public void Conn() {
		log.info("conn : " + ds);
	}
	
	@Test
	public void insert() throws SQLException {
		Connection conn = ds.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("INSERT INTO tbl_test VALUES(1, '홍길동')");
		int result = pstmt.executeUpdate();
		log.info("result : " + result); 
	}
	@Test
	public void select() throws SQLException {
		Connection conn = ds.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tbl_test");
		ResultSet rs = pstmt.executeQuery();
		if(rs != null) {
			while(rs.next()) {
				log.info("id : " + rs.getInt(1));
				log.info("id : " + rs.getString(2));
			}
		}
	}
	
}
