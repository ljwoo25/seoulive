package org.zerock.seoulive.board.travel.exception;

// 개발자가 만드는 컨트롤러의 핸들러 메소드에서 오류 발생시 아래에 정의된
// 사용자 정의 예외 객체로 Wrapping 해서 throw 하게 하기 위해 정의된 예외 클래스
public class ServiceException extends Exception {
    private static final long serialVersionUID = 1L;

    public ServiceException(String message) {
        super(message);
    } // Default Constructor

    public ServiceException(Exception originalException) {
        super(originalException);
    } // Default Constructor

} // end class
