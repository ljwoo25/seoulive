package org.zerock.seoulive.board.course.exception;


public class DAOException extends Exception {
    private static final long serialVersionUID = 1L;

    public DAOException(String message) {
        super(message);
    } // Default Constructor

    public DAOException(Exception originalException) {
        super(originalException);
    } // Default Constructor

} // end class
