package com.test.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.dto.MemberDto;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/member")
@Log4j
public class MemberJoinController {
	
	@InitBinder
	public void toDate(WebDataBinder binder) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd"); // dateformat을 하나 만들어두고
		SimpleDateFormat df2 = new SimpleDateFormat("yyyymmdd"); 
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false)); // 날짜 정보가 들어온다면 내가 정해준 양식에 맞게 들어온 데이터를 그렇게 맞춰줌.
		// true : null 허용 O , false : null 허용 X
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df2, false));
		binder.registerCustomEditor(String[].class, new StringArrayPropertyEditor("-")); 
		// 원래는 [010 1111 1234] 이런식으로 저 전체가 하나의 0번째 인덱스였는데,
		// 위 코드를 적용하면 [010, 1111, 1234] 이런식으로 0, 1, 2번째 인덱스가 생김. 
	}
	
	//남은 에러 처리를 여기서 해주면 됨. @ExceptionHandler로!!
	
	
	
//	@RequestMapping(value="/join", method=RequestMethod.GET)
	@GetMapping("/join") // 단순하게 GET으로만 처리할 꺼면 경로만 가지고 이렇게 Mapping을 해줘도 됨.
	public void doGet() { 	
		log.info("join form!!...");
	}
//	@RequestMapping(value="/save", method=RequestMethod.POST)
	@PostMapping("/save") // POST Mapping 방식
	public String doPost(MemberDto dto, BindingResult result, Model model) { 	// BindingResult가 Model보다 앞에 있어야함.
		log.info("request save!!...");
		log.info("result : " + result);
		//1 파라미터
		log.info("dto : " + dto);
		
		//2 유효성체크
		boolean flag = isValid(dto);
		if(!flag) {
			//메시지 전달
			model.addAttribute("msg", "유효성체크 결과 문제가 있음...");
			//특정 뷰 이동
			return "redirect:/member/join";
		}
		
		//3 서비스실행
		
		//4 뷰로 이동
		
//		return "login";
		return "joinresult";
		
	}
	private boolean isValid(MemberDto dto) {
		
		return false;
	}
	
	
	
}
