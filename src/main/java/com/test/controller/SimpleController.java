package com.test.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.dto.YoilDto;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class SimpleController {
	
	@RequestMapping("/Yoil1") // URL Mapping을 위한 에노테이션
	public void func1(HttpServletRequest req, HttpServletResponse resp ) throws IOException {
		log.info("/Yoil1 진입....");
		
		//파라미터 받기
		int year = Integer.parseInt(req.getParameter("year"));
		int month = Integer.parseInt(req.getParameter("month"));
		int day = Integer.parseInt(req.getParameter("day"));
		
		
		//입력값 검증
		
		//서비스실행(처리)
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);
		
		int dayOfweek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		char yoil = "일월화수목금토".charAt(dayOfweek);
		
		//뷰이동
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("</head>");
		out.print("<body>");
		out.print("<h1>YOIL PAGE!!</h1>");
		out.print(year + "-" + month + "-" + day + "은 " + yoil + " 요일 입니다.<br>");
		out.print("</body>");
		out.print("</html>");
		
	}
	
	
	@RequestMapping("/Yoil2") // URL Mapping을 위한 에노테이션
	public void func2(int year, int month, int day, HttpServletRequest req, HttpServletResponse resp ) throws IOException {
		log.info("/Yoil2 진입....");
		
		//파라미터 받기
		
		//입력값 검증
		
		//서비스실행(처리)
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);
		
		int dayOfweek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		char yoil = "일월화수목금토".charAt(dayOfweek);
		
		//뷰이동
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("</head>");
		out.print("<body>");
		out.print("<h1>YOIL2 PAGE!!</h1>");
		out.print(year + "-" + month + "-" + day + "은 " + yoil + " 요일 입니다.<br>");
		out.print("</body>");
		out.print("</html>");
		
	}
	
	
	// URL Mapping을 위한 에노테이션
	@RequestMapping("/Yoil3") 
	public void func3(int year, int month, int day) throws IOException {
		log.info("/Yoil3 진입....");
		
		//파라미터 받기
		log.info(year + " " + month + " " + day);
		//입력값 검증
		
		//서비스실행(처리)
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);
		
		int dayOfweek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		char yoil = "일월화수목금토".charAt(dayOfweek);
		
		//뷰이동
		
		
		
	}
	
	
	
	
	@RequestMapping("/Yoil4") // URL Mapping을 위한 에노테이션
	public String func4(int year, int month, int day, HttpServletRequest req, HttpServletResponse resp ) throws IOException {
		log.info("/Yoil4 진입....");
		
		//파라미터 받기
		log.info(year + " " + month + " " + day);
		//입력값 검증
		
		//서비스실행(처리)
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);
		
		int dayOfweek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		char yoil = "일월화수목금토".charAt(dayOfweek);
		
		//뷰이동
		return "yoil/Yoil4";
		
	}
	
	
	
	
	@RequestMapping("/Yoil5") // URL Mapping을 위한 에노테이션
	public String func5(int year, int month, int day, Model model ) throws IOException {
		log.info("/Yoil5 진입....");
		
		//파라미터 받기
		log.info(year + " " + month + " " + day);
		//입력값 검증
		
		//서비스실행(처리)
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);
		
		int dayOfweek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		char yoil = "일월화수목금토".charAt(dayOfweek);
		
		//뷰이동
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("yoil", yoil);
		
		return "yoil/Yoil5";
		
	}
	
	
	
	
	
	@RequestMapping("/Yoil6") // URL Mapping을 위한 에노테이션, 밑에 Model은 뷰로 이동시켜주는 요소이고, 우리는 그 요소에 속성들을 추가해준 것 뿐임.
	public String func6(YoilDto dto, Model model ) throws IOException {
		log.info("/Yoil6 진입....");
		
		//파라미터 받기
		log.info(dto.getYear() + " " + dto.getMonth() + " " + dto.getDay());
		//입력값 검증
		if(!isValid(dto)) {
			return "yoil/YoilError";
		}
		
		//서비스실행(처리)
		char yoil = getYoil(dto);
		
		//뷰이동
//		model.addAttribute("year", dto.getYear());
//		model.addAttribute("month", dto.getMonth());
//		model.addAttribute("day", dto.getDay()); // 속성을 추가해준 부분
		
		model.addAttribute("yoildto", dto);
		model.addAttribute("yoil", yoil);
		
		return "yoil/Yoil6";
		
	
	}
	private boolean isValid(YoilDto param) {
		return true;
	}
	
	private char getYoil(YoilDto dto) {
		Calendar cal = Calendar.getInstance();
		cal.set(dto.getYear(), dto.getMonth()-1, dto.getDay());
		int dayOfweek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		char yoil = "일월화수목금토".charAt(dayOfweek);
		return yoil;
	}
	
	
	
	
	
	
	
	
	
}
