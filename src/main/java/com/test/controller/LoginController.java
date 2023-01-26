package com.test.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.test.dto.AuthDto;
import com.test.dto.LoginDto;
import com.test.service.AuthService;
import com.test.validation.LoginValidator;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class LoginController {
	
	@Autowired
	AuthService service;
	
	@InitBinder
	public void IDPWInit(WebDataBinder binder) {
		
		// 유효성 체크
		binder.addValidators(new LoginValidator());
		List<Validator> vlist = binder.getValidators();
		log.info("validatorList : " + vlist);
	}
	
	
	
	
	
	@GetMapping("/login")
	void loginform() {
		log.info("login Get...");
//		throw new NullPointerException("login get 에서 발생");
	}
	@PostMapping("/login")
	String loginproc(
			@Valid @ModelAttribute LoginDto loginDto,
			BindingResult result,
			Model model,
			HttpServletRequest request,
			HttpServletResponse response) { // form이 전달하는 이름과 파라미터 이름이 같아야함.(email, pwd)
		log.info("login Post...");
//		log.info("id : " + email + " pwd : " + pwd + " idchk : " + rememberId);
		
		//1 파라미터(생략)
		
		//2 유효성
//		boolean flag = isValid(email, pwd);
//		if(!flag) {
//			model.addAttribute("msg", "ID/PW가 일치하지 않습니다.");
//			
//			return "redirect:/login";
//		}
		
		if(result.hasErrors()) {
			return "/login";
		}
		
		
		
		//3 서비스
		//1) DB id/pw 와 파라미터 id/pw 일치여부 확인
		//2) 일치하다면 - 세션객체생성 최소한의 정보를 저장
		//3) 기타(쿠키)
		boolean flag = service.LoginCheck(loginDto, request);
		
		
		if(loginDto.isRememberId()) {
			Cookie cookie = new Cookie("email", loginDto.getEmail());
			response.addCookie(cookie);
		} else {
			Cookie cookie = new Cookie("email", loginDto.getEmail());
			cookie.setMaxAge(0); // 만료를 0으로 잡으면 없애겠다는 의미
			response.addCookie(cookie); // 쿠키전달
		}
		
		
		//4 뷰
//		return "main"; //기본 forwarding방식으로 보내지기 때문에
		return "redirect:/";
		
	}
	private boolean isValid(String email, String pwd) {
		//여기서 유효성 검사를 하는건데 DB대용으로 쓸거임 여기서만. 원래는 ID&PW 일치여부를 서비스에서 하는데 일단 여기서만.
		return email.equals("abcd@naver.com")&&pwd.equals("1234");
	}
	
	@GetMapping("/loggout")
	public String loggout(HttpSession session, Model model) {
		//1 세션종료
		session.invalidate();
		
		//2 메세지
		model.addAttribute("msg", "로그아웃 성공!");
		
		//3 login이동
		return "redirect:/login";
	}
	
}
