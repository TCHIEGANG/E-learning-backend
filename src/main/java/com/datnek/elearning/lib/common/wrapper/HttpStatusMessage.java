package com.datnek.elearning.lib.common.wrapper;

import org.springframework.http.HttpStatus;

import java.util.Objects;

public class HttpStatusMessage {

    /**
     * HttpStatus of the execution
     */
    private HttpStatus httpStatus;

    /**
     * Message about this HttpStatus
     */
    private String message;


    public HttpStatusMessage(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatusMessage() {

    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HttpStatusMessage that = (HttpStatusMessage) o;
        return httpStatus == that.httpStatus &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {

        return Objects.hash(httpStatus, message);
    }
}
