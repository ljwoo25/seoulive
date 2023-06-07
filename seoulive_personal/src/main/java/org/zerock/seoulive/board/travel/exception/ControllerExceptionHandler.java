package org.zerock.seoulive.board.travel.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
@ControllerAdvice(basePackages= {"org.zerock.seoulive.controller"})	// For one package
public class ControllerExceptionHandler {

    // NullPointerException을 처리하는 예외 처리 메소드
    @ExceptionHandler({NullPointerException.class,
            IllegalStateException.class,
            NumberFormatException.class})
    String handleRuntimeException(Exception e, Model model) {

        log.trace("handleRuntimeException({}) invoked.", e);
        log.error("1. Exception Type : {}", e.getClass().getName());
        log.error("2. Exception Mesg : {}", e.getMessage());

        model.addAttribute("__EXCEPTION__", e);

        // 컨트롤러의 핸들러 메소드와 동일한 리턴타입(즉 뷰의 이름)
        return "errorPage";
    } // handleRuntimeException

} // end class
