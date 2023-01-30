package com.test.service;

import java.util.List;
import java.util.Map;

import com.test.dto.TestDto;

public interface TestService {

//	void func1() throws Exception;
//
//	void func2() throws Exception;
//
//	void func3() throws Exception;
	
	//위에까지가 원래 했던거, 밑에서부터 Mapper 연결
	
	public TestDto GetTestObject(int id);

	public TestDto InsertTestObject(TestDto dto);
	
	public int UpdateTestObject(TestDto dto);
	
	public int DeleteTestObject(int id);
	
	public List<Map<String, Object>> SelectAllTestObject();
	
	public List<Map<String, Object>> SelectAllTestObject(Map<String, Object> map);
	
}