package com.datnek.elearning.lib.common.exception;

/**
 * Exception pour la gestion des erreurs lié à la manipulation de fichier
 */
public class FileSystemException extends Exception {

    private static final long serialVersionUID = -4801504922727167860L;

    /***/
    public FileSystemException() {
        super();
    }

    /**
     * @param msg message de l'exception
     */
    public FileSystemException(String msg) {
        super(msg);
    }

    /**
     * @param msg message de l'exception
     * @param th  l'exception
     */
    public FileSystemException(String msg, Throwable th) {
        super(msg, th);
    }

    /**
     * @param th l'exception
     */
    public FileSystemException(Throwable th) {
        super(th);
    }
}
