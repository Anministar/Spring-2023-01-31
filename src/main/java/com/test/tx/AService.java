package com.test.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.test.dto.TestDto;

import lombok.extern.log4j.Log4j;

@Service
@Log4j	
public class AService  {
	@Autowired
	ADao adao;
	
	@Autowired
	BService bservice;
	
	@Transactional(rollbackFor=Exception.class)  
	public void func1() throws Exception {
		adao.insert(new TestDto(1, "aa"));
		adao.insert(new TestDto(2, "bb"));
	}
	
	//Propagation.REQUIRED : 기본값
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class) 
	public void func2() throws Exception { // A트랜젝션 안에 B트랜젝션이 있을꺼고 tbl_a insert 후 tbl_b insert 후 b에 대한 트랜젝션 처리, a에 대한 트랜잭션 처리 이후 commit 과정
		adao.insert(new TestDto(1, "aa"));
		bservice.func2();
		adao.insert(new TestDto(2, "bb"));
	}
	
	
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class) 
	public void func3() throws Exception {
		adao.insert(new TestDto(1, "aa"));
		bservice.func3();
		adao.insert(new TestDto(2, "bb"));
		
	}
	
	
}
