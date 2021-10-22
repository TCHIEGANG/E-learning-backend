package com.datnek.elearning.lib.common.error;

import com.datnek.elearning.lib.common.exception.ExceptionUtil;
import com.datnek.elearning.lib.common.logging.Loggable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Dto servant à fusionner les données contenues dans les objets de type {@link Exception} et {@link HttpServletRequest},
 * en vue d'être serialisé pour les log ou les retour HTTP.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericResultErrorDto implements Loggable {

    private String httpStatusName;
    private Integer httpStatusCode;
    private String errorMessage;
    private String errorClass;
    private String errorLocalizedMessage;
    private List<String> stackTrace;
    private List<String> causes;
    private String authType;
    private String contextPath;
    private String method;
    private String pathInfo;
    private String pathTranslated;
    private String queryString;
    private String remoteUser;
    private String requestURI;
    private String servletPath;
    private String characterEncoding;
    private String contentType;
    private Integer contentLength;

    /**
     * Constructeur. Recupère les informations dans {@link Exception} et {@link HttpServletRequest}
     *
     * @param exception l'objet exception
     * @param request   l'objet request
     */
    public GenericResultErrorDto(Exception exception, HttpServletRequest request) {
        HttpStatus httpStatus = ExceptionUtil.buildHttpStatus(exception);
        this.httpStatusName = httpStatus.name();
        this.httpStatusCode = httpStatus.value();

        this.errorMessage = exception.getMessage();
        this.errorClass = exception.getClass().getName();
        this.errorLocalizedMessage = exception.getLocalizedMessage();

        this.stackTrace = new ArrayList<>();
        for (int i = 0; i < Math.min(exception.getStackTrace().length, 5); i++) {
            this.stackTrace.add(exception.getStackTrace()[i].toString());
        }

        this.causes = new ArrayList<>();
        Throwable cause = exception;
        while ((cause = cause.getCause()) != null) {
            this.causes.add(cause.toString());
        }

        this.authType = request.getAuthType();
        this.contextPath = request.getContextPath();
        this.method = request.getMethod();
        this.pathInfo = request.getPathInfo();
        this.pathTranslated = request.getPathTranslated();
        this.queryString = request.getQueryString();
        this.remoteUser = request.getRemoteUser();
        this.requestURI = request.getRequestURI();
        this.servletPath = request.getServletPath();
        this.characterEncoding = request.getCharacterEncoding();
        this.contentType = request.getContentType();
        this.contentLength = request.getContentLength();
    }

    /**
     * Constructeur par défaut
     */
    public GenericResultErrorDto() {
        //RAF
    }

    public String getHttpStatusName() {
        return httpStatusName;
    }

    public void setHttpStatusName(String httpStatusName) {
        this.httpStatusName = httpStatusName;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorClass() {
        return errorClass;
    }

    public void setErrorClass(String errorClass) {
        this.errorClass = errorClass;
    }

    public String getErrorLocalizedMessage() {
        return errorLocalizedMessage;
    }

    public void setErrorLocalizedMessage(String errorLocalizedMessage) {
        this.errorLocalizedMessage = errorLocalizedMessage;
    }

    public List<String> getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(List<String> stackTrace) {
        this.stackTrace = stackTrace;
    }

    public List<String> getCauses() {
        return causes;
    }

    public void setCauses(List<String> causes) {
        this.causes = causes;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPathInfo() {
        return pathInfo;
    }

    public void setPathInfo(String pathInfo) {
        this.pathInfo = pathInfo;
    }

    public String getPathTranslated() {
        return pathTranslated;
    }

    public void setPathTranslated(String pathTranslated) {
        this.pathTranslated = pathTranslated;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getRemoteUser() {
        return remoteUser;
    }

    public void setRemoteUser(String remoteUser) {
        this.remoteUser = remoteUser;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public String getServletPath() {
        return servletPath;
    }

    public void setServletPath(String servletPath) {
        this.servletPath = servletPath;
    }

    public String getCharacterEncoding() {
        return characterEncoding;
    }

    public void setCharacterEncoding(String characterEncoding) {
        this.characterEncoding = characterEncoding;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Integer getContentLength() {
        return contentLength;
    }

    public void setContentLength(Integer contentLength) {
        this.contentLength = contentLength;
    }

    @JsonIgnore
    @Override
    public String getLog() {
        return "{httpStatusName:" + httpStatusName + ",  httpStatusCode:" + httpStatusCode +
                ", errorMessage:" + errorMessage + "}";
    }

    @JsonIgnore
    @Override
    public String getLogDetail() {
        return "{httpStatusName:'" + httpStatusName + '\'' +
                ", httpStatusCode:" + httpStatusCode + '\'' +
                ", errorMessage:'" + errorMessage + '\'' +
                ", errorClass:'" + errorClass + '\'' +
                ", errorLocalizedMessage:'" + errorLocalizedMessage + '\'' +
                ", stackTrace:" + stackTrace +
                ", authType:'" + authType + '\'' +
                ", contextPath:'" + contextPath + '\'' +
                ", method:'" + method + '\'' +
                ", pathInfo:'" + pathInfo + '\'' +
                ", pathTranslated:'" + pathTranslated + '\'' +
                ", queryString:'" + queryString + '\'' +
                ", remoteUser:'" + remoteUser + '\'' +
                ", requestURI:'" + requestURI + '\'' +
                ", servletPath:'" + servletPath + '\'' +
                ", characterEncoding:'" + characterEncoding + '\'' +
                ", contentType:'" + contentType + '\'' +
                ", contentLength:" + contentLength +
                '}';
    }
}
