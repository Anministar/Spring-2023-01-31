package com.test.dao;

import java.util.List;
import java.util.Map;

import com.test.dto.TestDto;

public interface TestDao {

//	int insert(TestDto dto) throws Exception;

	
	// 위에까지가 원래 했던거, 밑에서부터 Mapper 연결
	
	public TestDto select(int id);
	
	public TestDto insert(TestDto dto);
	
	public int update(TestDto dto);
	
	public int delete(int id);
	
	public List<Map<String, Object>> SelectAll();
	
	public List<Map<String, Object>> SelectAll(Map<String, Object> map);
	
}