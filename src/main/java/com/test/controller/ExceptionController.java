package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ExceptionController {
	
	@ExceptionHandler(Exception.class)
	public String error(Exception ex, Model model) {
		log.info("Exception func...");
		model.addAttribute("ex", ex); 
		// 예외가 발생하면 예외 메세지 처리를 한 후 100번지(예를 들면) 객체가 생기고 그걸 ex랑 연결시켜주는 작업.. Model은 view에 보여주게 되는데 그게 ex라는 속성에 ex를 속성값으로 담아 처리하는 과정임.
		return "error";
	}
	
	@GetMapping("/test1")
	public void func1() {
		log.info("tes1 Page...");
//		String str = null;
//		str.toString();
		throw new NullPointerException("메시지 : Null 예외발생!!!"); // 예외 객체 생성
	}
	@GetMapping("/test2")
	public void func2() {
		log.info("test2 Page...");
		int n1 = 10;
		int n2 = 0;
		log.info(n1/n2); //산술 오류
	}
	

}
