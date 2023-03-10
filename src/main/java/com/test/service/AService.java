package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dao.TestDao;
import com.test.dto.TestDto;
import com.test.service.TestService;

import lombok.extern.log4j.Log4j;

@Service
@Log4j	
public class AService implements TestService {
	@Autowired
	TestDao dao;
	
	@Override
	public void func1() throws Exception {
		dao.insert(new TestDto(6, "ff"));
		dao.insert(new TestDto(6, "ff"));
	}
	@Override
	@Transactional(rollbackFor=Exception.class) //@ 모든 Exception에 적용, 트랜잭션 처리가 될꺼다라고 알려주는 어노테이션
	public void func2() throws Exception {
		dao.insert(new TestDto(6, "ff"));
		dao.insert(new TestDto(6, "ff"));
	}
	
	
	@Override
	@Transactional //RuntimeException발생시에만 적용...
	public void func3() throws Exception {
		dao.insert(new TestDto(6, "ff"));
		throw new RuntimeException();
//		dao.insert(new TestDto(6, "ff"));
	}
	
	
}
