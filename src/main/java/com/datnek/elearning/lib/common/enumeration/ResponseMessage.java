package com.datnek.elearning.lib.common.enumeration;

import lombok.Getter;

/**
 * Enum contenant les messages des reponses aux requestes.
 */
@Getter
public enum ResponseMessage {

    LANGUAGE_NOT_ALLOWED("Votre compte n'est pas autorisé dans cette rubrique"),
    LANGUAGE_UNIDENTIFIED("Cette langue n'a pas pu être identifiée"),
    LANGUAGE_NOT_FOUND("Cette langue n'existe pas"),
    LANGUAGE_ALREADY_EXISTS("Votre compte existe déjà, Veuillez vous connecté"),
    LANGUAGE_ALREADY_ACTIVE("Votre compte a déjà été activé"),
    ERROR_SAVING_LANGUAGE("Une erreur est survenue lors de la création de votre compte"),
    CREDENTIALS_INCORRECT("Login ou mot de passe incorrect"),
    INPUT_DATA_INVALID("Certaines données sont invalides"),
    POST_NOT_FOUND("Votre publication est introuvable"),
    DOCUMENT_TYPE_NOT_ALLOWED("ce type de document n'est pas autorisé"),
    POST_DOES_NOT_BELONG_TO_LANGUAGE("Cette action n'est pas possible car vous n'êtes pas l'auteur de cette publication"),
    ERROR_SAVING_POST("Une erreur est survenue lors de la sauvegarde de la publication");

    private final String message;

    ResponseMessage(String message) {
        this.message = message;
    }
}
