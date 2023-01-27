package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.test.dto.TestDto;

import lombok.extern.log4j.Log4j;

@Repository
@Log4j
public class TestDaoImpl implements TestDao {
	
	@Autowired
	DataSource ds;
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	@Override
	public int insert(TestDto dto) throws Exception {
		int result = 0;
		
		try {
			//conn = ds.getConnection();
			conn = DataSourceUtils.getConnection(ds); // conn을 트랜잭션 단위로 관리하기 위해 DataSourceUtils애 주입하는 과정임?? 한번 더 찾아보기!!
			log.info("CONN : " + conn);
			pstmt = conn.prepareStatement("insert into tbl_test values(?, ?)");
			pstmt.setInt(1, dto.getId());
			pstmt.setString(2, dto.getName());
			result = pstmt.executeUpdate();
			
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {pstmt.close();}catch(Exception e) {}
		
			DataSourceUtils.releaseConnection(conn, ds);
			
		}
		
		return result;
	}
	
	
	
}
