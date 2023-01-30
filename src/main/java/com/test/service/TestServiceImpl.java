package com.test.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dao.TestDao;
import com.test.dto.TestDto;

import lombok.extern.log4j.Log4j;

@Service
@Log4j	
public class TestServiceImpl implements TestService {
//	@Autowired
//	TestDao dao;
//	
//	@Override
//	public void func1() throws Exception {
//		dao.insert(new TestDto(6, "ff"));
//		dao.insert(new TestDto(6, "ff"));
//	}
//	@Override
//	@Transactional(rollbackFor=Exception.class) //@ 모든 Exception에 적용, 트랜잭션 처리가 될꺼다라고 알려주는 어노테이션
//	public void func2() throws Exception {
//		dao.insert(new TestDto(6, "ff"));
//		dao.insert(new TestDto(6, "ff"));
//	}
//	
//	
//	@Override
//	@Transactional //RuntimeException발생시에만 적용...
//	public void func3() throws Exception {
//		dao.insert(new TestDto(6, "ff"));
//		throw new RuntimeException();
////		dao.insert(new TestDto(6, "ff"));
//	}
	
	// 위에까지가 원래 했던거, 밑에서부터 Mapper 연결
	
	@Autowired
	TestDao dao;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public TestDto GetTestObject(int id) {
		return dao.select(id);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public TestDto InsertTestObject(TestDto dto) {
		return dao.insert(dto);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int UpdateTestObject(TestDto dto) {
		return dao.update(dto);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int DeleteTestObject(int id) {
		return dao.delete(id);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<Map<String, Object>> SelectAllTestObject() {
		return dao.SelectAll();
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<Map<String, Object>> SelectAllTestObject(Map<String, Object> map) {
		return dao.SelectAll(map);
	}
	
}
