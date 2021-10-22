package com.datnek.elearning.lib.common.exception;

/**
 * Excpetion pour les traitements que l'on souhaite interrompre suite à erreur car on sait qu'il ne pourront pas terminer correctement ou que leur fin n'aura aucun sens.
 */
public class InterruptedActionException extends Exception {

    /***/
    public InterruptedActionException() {
        super();
    }

    /**
     * @param msg message de l'exception
     */
    public InterruptedActionException(String msg) {
        super(msg);
    }

    /**
     * @param msg message de l'exception
     * @param th  l'exception
     */
    public InterruptedActionException(String msg, Throwable th) {
        super(msg, th);
    }

    /**
     * @param th l'exception
     */
    public InterruptedActionException(Throwable th) {
        super(th);
    }
}
