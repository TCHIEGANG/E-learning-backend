package com.datnek.elearning.lib.common.exception;

import org.springframework.http.HttpStatus;

/**
 * Class utilitaire pour les exceptions
 */
public class ExceptionUtil {

    /**
     * Http Status par défaut
     */
    public static final HttpStatus DEFAULT_END_POINT_HTTP_STATUS = HttpStatus.ACCEPTED;

    private ExceptionUtil() {
        // empty constructor
    }

    /**
     * Construit le code retour Http en fonction du type d'erreur recue.
     *
     * @param exception l'exception
     * @return httpStatus le code http de retour
     */
    public static HttpStatus buildHttpStatus(Exception exception) {
        // Par défaut, tout doit sortir en 500 internal serveur error.
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        // Certaines exceptions auront des retours spécifiques
        if (exception instanceof ActionNotAllowedException) httpStatus = HttpStatus.FORBIDDEN;
        if (exception instanceof InvalidParameterException) httpStatus = HttpStatus.BAD_REQUEST;

        return httpStatus;
    }
}
