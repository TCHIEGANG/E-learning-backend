package com.datnek.elearning.lib.common.wrapper;


import com.datnek.elearning.lib.common.enumeration.ServiceResponseCode;
import com.datnek.elearning.lib.common.logging.Loggable;
import com.datnek.elearning.lib.common.logging.LoggingUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * Classe servant à renseigner un message avec son code.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericMessageDetails implements Loggable {

    /**
     * Code du message
     */
    ServiceResponseCode code;

    /**
     * Descriptif du message
     */
    private String message;

    /**
     * Champs ciblé par le message / code
     */
    private String field;

    /**
     * Objet loggable pour enrichir les logs
     */
    private Loggable object;

    public GenericMessageDetails(ServiceResponseCode code, String message, String field, Loggable object) {
        this(code, message, field);
        this.object = object;

    }

    public GenericMessageDetails(ServiceResponseCode code, String message, String field) {
        this.code = code;
        this.message = message;
        this.field = field;
    }

    public GenericMessageDetails() {
        // default
    }

    /**
     * Getter de code.
     *
     * @return le code à afficher sous forme de String.
     */
    public String getCode() {
        if (code != null) {
            return code.getServiceResponseCode();
        }
        return null;
    }

    public void setCode(ServiceResponseCode code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Loggable getObject() {
        return object;
    }

    public void setObject(Loggable object) {
        this.object = object;
    }

    @JsonIgnore
    @Override
    public String getLog() {
        return "{code:" + code + ",  message:" + message +
                ", field:" + field + "}";
    }

    @JsonIgnore
    @Override
    public String getLogDetail() {
        return "{" +
                "code:" + code +
                ", message:'" + message + '\'' +
                ", field:'" + field + '\'' +
                ", object:" + LoggingUtil.getLog(object) +
                "}";
    }
}
