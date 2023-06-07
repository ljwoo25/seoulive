package org.zerock.seoulive.exception;


// 개발자가 만드는 컨트롤러의 핸들러 메소드에서 오류발생시, 
// 아래에 정의된 사용자정의 예외객체로, Wrapping해서 throw하게 
// 하기위해 정의된 예외클래스
public class DAOException extends Exception {
	private static final long serialVersionUID = 1L;
	//부모(Exception)가 Serializable 하기 때문에, 자식클래스에 serialVersionUID가 필요
	
	
	public DAOException(String message) {
		
		super(message);				//부모클래스의 예외메세지를 받음
		
	}// default Constructor
	
	
	public DAOException(Exception originalException) {
		
		super(originalException);
		
		
	}//default Constructor
	
	
}//end class