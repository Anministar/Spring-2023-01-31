package com.test.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component
@Aspect
@Log4j
public class LoggingAdvice {

	//execution(반환타입 패키지.클래스.메소드(인자)) @ .. : 인자가 어떤 타입인지 상관하지 않겠다(모든 인자를 다 받을 수 있음)
	@Around("execution(* com.test.aop.MyMath.*(..))")
	public Object logging1(ProceedingJoinPoint pjp) throws Throwable { // public보다 왼쪽 옆에(17 숫자 옆) 보면 마커가 생기는데 이 줄부터 aop가 적용될 것이다라는 의미.
		long start = System.currentTimeMillis();
		log.info("[AOP] start : " + pjp.getSignature().getName());
		
		//위 코드까지 로직(실행되기 전)
		Object result = pjp.proceed();	//로직 실행
		//밑 코드부터 실행되기 후
		
		log.info("[AOP] result : " + result);
		long end = System.currentTimeMillis();
		log.info("[AOP] time : " + (end-start) + "ms");
		log.info("[AOP] end ");
		return result;
		
	}
}
