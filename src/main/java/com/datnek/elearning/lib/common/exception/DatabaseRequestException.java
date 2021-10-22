package com.datnek.elearning.lib.common.exception;

/**
 * Exception pour les erreurs lié à la base de données
 */
public class DatabaseRequestException extends Exception {

    private static final long serialVersionUID = 8398864312035655321L;

    /***/
    public DatabaseRequestException() {
        super();
    }

    /**
     * @param msg message de l'exception
     */
    public DatabaseRequestException(String msg) {
        super(msg);
    }

    /**
     * @param msg message de l'exception
     * @param th  l'exception
     */
    public DatabaseRequestException(String msg, Throwable th) {
        super(msg, th);
    }

    /**
     * @param th l'exception
     */
    public DatabaseRequestException(Throwable th) {
        super(th);
    }
}
