package com.test.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.dto.TestDto;

import lombok.extern.log4j.Log4j;

@Repository
@Log4j
public class TestDaoImpl implements TestDao {
//	
//	@Autowired
//	DataSource ds;
//	
//	Connection conn;
//	PreparedStatement pstmt;
//	ResultSet rs;
//	
//	@Override
//	public int insert(TestDto dto) throws Exception {
//		int result = 0;
//		
//		try {
//			//conn = ds.getConnection();
//			conn = DataSourceUtils.getConnection(ds); // conn을 트랜잭션 단위로 관리하기 위해 DataSourceUtils애 주입하는 과정임?? 한번 더 찾아보기!!
//			log.info("CONN : " + conn);
//			pstmt = conn.prepareStatement("insert into tbl_test values(?, ?)");
//			pstmt.setInt(1, dto.getId());
//			pstmt.setString(2, dto.getName());
//			result = pstmt.executeUpdate();
//			
//			
//		} catch(Exception e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			try {pstmt.close();}catch(Exception e) {}
//		
//			DataSourceUtils.releaseConnection(conn, ds);
//			
//		}
//		
//		return result;
//	}

	
	// 위에까지가 원래 했던거, 밑에서부터 Mapper 연결
	@Autowired
	private SqlSession session;
	
	private static String namespace="com.test.mapper.TestMapper.";
	
	//SELECTone
	@Override
	public TestDto select(int id) {
		return session.selectOne(namespace + "selectXML",  id);
	}
	
	//INSERT(SelectKey)
	@Override
	public TestDto insert(TestDto dto) {
		session.insert(namespace + "insertKeyAfterXML", dto);
		return dto;
	}
	
	//UPDATE
	@Override
	public int update(TestDto dto) {
		return session.update(namespace + "updateXML", dto);
	}
	
	//DELETE
	@Override
	public int delete(int id) {
		return session.delete(namespace + "deleteXML", id);
	}
	
	//SELECTALL
	@Override
	public List<Map<String, Object>> SelectAll() {
		return session.selectList(namespace + "selectXMLHashMap");
		
	}
	
	//SELECTALL + map
	@Override
	public List<Map<String, Object>> SelectAll(Map<String, Object> map) {
		return session.selectList(namespace + "selectXMLHashMapIf", map);
	}
	
}
