package org.zerock.seoulive.board.course.exception;


public class ServiceException extends Exception {
    private static final long serialVersionUID = 1L;

    public ServiceException(String message) {
        super(message);
    } // Default Constructor

    public ServiceException(Exception originalException) {
        super(originalException);
    } // Default Constructor

} // end class
