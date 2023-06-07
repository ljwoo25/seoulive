package org.zerock.seoulive.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

//컨트롤러에서 발생하는 예외를 핸들링하는 클래스임을 알려주는 것
//@ControllerAdvice	//For All packages

@Log4j2
@NoArgsConstructor

//기본값->모든 패키지    / basePackages로 Controller를 지정해주면, Controller패키지만 처리가능
@ControllerAdvice(basePackages= {"org.zerock.seoulive.controller"})		//For one package
public class ControllerExceptionHandler {

	
	//Exception을 처리하는 예외처리 메소드
	@ExceptionHandler(Exception.class)
	String handleException (Exception e, Model model) {
		log.trace("handleException ({}) invoked.",e);	
		
		log.error("\t+ 1. Exception Type : {}",e.getClass().getName());
		log.error("\t+ 2, Exception Mesg : {}",e.getMessage());
		
		//공유영역중, Request Scope의 공유속성으로 바인딩
		model.addAttribute("__EXCEPTION__", e);
		
		//컨트롤러의 핸들러 메소드와 동일한 리턴타입(즉, 뷰의 이름)
		return "errorpage";
		
	}//handleException 
	

	@ExceptionHandler(ControllerException.class)
	String handleControllerException (Exception e, Model model) {
		log.trace("handleControllerException ({}) invoked.",e);	
		
		log.error("\t+ 1. Exception Type : {}",e.getClass().getName());
		log.error("\t+ 2, Exception Mesg : {}",e.getMessage());
		
		//공유영역중, Request Scope의 공유속성으로 바인딩
		model.addAttribute("__EXCEPTION__", e);
		
		//컨트롤러의 핸들러 메소드와 동일한 리턴타입(즉, 뷰의 이름)
		return "errorpage";
		
	}//handleControllerException 
	
	
	
	
//	NullPointerException을 처리하는 예외처리 메소드
//	@ExceptionHandler(NullPointerException.class) //하나의 예외만 처리할경우
	
	//배열이므로, 여러가지 예외처리를 처리할 수 있음.
	@ExceptionHandler({ 
		NullPointerException.class,
		IllegalStateException.class,
		NumberFormatException.class
	})
	String handleRuntimeException(Exception e, Model model) {
		log.trace("handleRuntimeException({}) invoked.",e);	
		
		log.error("\t+ 1. Exception Type : {}",e.getClass().getName());
		log.error("\t+ 2, Exception Mesg : {}",e.getMessage());
		
		//공유영역중, Request Scope의 공유속성으로 바인딩
		model.addAttribute("__EXCEPTION__", e);
		
		//컨트롤러의 핸들러 메소드와 동일한 리턴타입(즉, 뷰의 이름)
		return "errorpage";
		
	}//handleRuntimeException
	

}//end class
