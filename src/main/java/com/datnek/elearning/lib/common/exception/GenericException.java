package com.datnek.elearning.lib.common.exception;

/**
 * Exception générique
 */
public class GenericException extends Exception {

    private static final long serialVersionUID = 4331690856005947535L;

    /***/
    public GenericException() {
        super();
    }

    /**
     * @param msg Message de l'exception
     */
    public GenericException(String msg) {
        super(msg);
    }

    /**
     * @param msg messgaed l'exception
     * @param th  l'exception
     */
    public GenericException(String msg, Throwable th) {
        super(msg, th);
    }

    /**
     * @param th l'exception
     */
    public GenericException(Throwable th) {
        super(th);
    }

}
