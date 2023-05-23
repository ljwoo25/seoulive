package org.zerock.seoulive.board.course.exception;


public class ControllerException extends Exception {
    private static final long serialVersionUID = 1L;

    public ControllerException(String message) {
        super(message);
    } // Default Constructor

    public ControllerException(Exception originalException) {
        super(originalException);
    } // Default Constructor

} // end class
