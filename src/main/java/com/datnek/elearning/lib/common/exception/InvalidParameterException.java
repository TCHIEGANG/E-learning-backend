package com.datnek.elearning.lib.common.exception;

/**
 * Exception pour la gestion des paramètres invalide
 */
public class InvalidParameterException extends Exception {

    private static final long serialVersionUID = 9156027333864466787L;

    /***/
    public InvalidParameterException() {
        super();
    }

    /**
     * @param msg message de l'exception
     */
    public InvalidParameterException(String msg) {
        super(msg);
    }

    /**
     * @param msg message de l'exception
     * @param th  l'exception
     */
    public InvalidParameterException(String msg, Throwable th) {
        super(msg, th);
    }

    /**
     * @param th exception
     */
    public InvalidParameterException(Throwable th) {
        super(th);
    }
}
