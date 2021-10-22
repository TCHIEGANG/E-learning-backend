package com.datnek.elearning.lib.common.exception;

/**
 * Excpetion pour les actions non permises
 */
public class ActionNotAllowedException extends Exception {

    private static final long serialVersionUID = -7365195115543316146L;

    /***/
    public ActionNotAllowedException() {
        super();
    }

    /**
     * @param msg message de l'exception
     */
    public ActionNotAllowedException(String msg) {
        super(msg);
    }

    /**
     * @param msg message de l'exception
     * @param th  l'exception
     */
    public ActionNotAllowedException(String msg, Throwable th) {
        super(msg, th);
    }

    /**
     * @param th l'exception
     */
    public ActionNotAllowedException(Throwable th) {
        super(th);
    }
}
