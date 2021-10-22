package com.datnek.elearning.lib.common.wrapper;

import com.datnek.elearning.lib.common.logging.Loggable;
import com.datnek.elearning.lib.common.logging.LoggableList;
import com.datnek.elearning.lib.common.logging.LoggingUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Doit être le retour de tout endpoint !
 *
 * @param <T> un objet {@link Loggable} qui pourra être serialisé dans la réponse.
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericResultDto<T extends Loggable> implements Loggable {

    /**
     * Le statut HTTP de l'execution
     */
    private HttpStatus httpStatus;

    /**
     * Le message descriptif du resultat d'éxecution
     */
    private String message;

    /**
     * Liste des messages d'information détaillées
     */
    private LoggableList<GenericMessageDetails> genericMessageDetailsList;


    /**
     * Numéro de la page
     */
    private Integer page;

    /**
     * Nombre de la page size
     */
    private Integer perPage;

    /**
     * Nombre total de pages
     */
    private Integer nbPages;

    /**
     * Nombre total d'éléments
     */
    private Long nbElements;

    /**
     * Les entetes de réponses http
     */
    private HttpHeaders httpHeaders = new HttpHeaders();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T resultObject;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<T> resultObjects;

    public GenericResultDto(T resultObject, HttpStatus httpStatus, String message) {
        this.resultObject = resultObject;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public GenericResultDto(T resultObject, HttpStatusMessage httpStatusMessage) {
        this(resultObject, httpStatusMessage.getHttpStatus(), httpStatusMessage.getMessage());
    }

    /**
     * Permet d'ajouter directement une message detaillé dans l'objet Dto.
     *
     * @param genericMessageDetails le message detaillé à ajouter
     */
    public void addGenericMessageDetails(GenericMessageDetails genericMessageDetails) {
        if (genericMessageDetailsList == null) {
            genericMessageDetailsList = new LoggableList<>();
        }
        genericMessageDetailsList.add(genericMessageDetails);
    }

    /**
     * Obtient le statut HTTP de l'action prise sur l'annonce
     *
     * @return Le statut HTTP
     */
    @JsonProperty("status")
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * Met à jour le statut HTTP de l'action prise sur l'annonce
     *
     * @param httpStatus Le nouveau statut
     */
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @JsonProperty("headers")
    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    public void setHttpHeaders(String headerName, String headerValue) {
        this.httpHeaders.set(headerName,headerValue);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("log")
    public LoggableList<GenericMessageDetails> getGenericMessageDetailsList() {
        return genericMessageDetailsList;
    }

    public void setGenericMessageDetailsList(LoggableList<GenericMessageDetails> genericMessageDetailsList) {
        this.genericMessageDetailsList = genericMessageDetailsList;
    }

    @JsonProperty("resource")
    public T getResultObject() {
        return resultObject;
    }

    public void setResultObject(T resultObject) {
        this.resultObject = resultObject;
    }

    @JsonProperty("resources")
    public List<T> getResultObjects() {
        return resultObjects;
    }

    public void setResultObjects(List<T> resultObjects) {
        this.resultObjects = resultObjects;
    }

    /**
     * Getter of page
     *
     * @return page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * Setter of page
     *
     * @param page Value of page to set
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * Getter of perPage
     *
     * @return perPage
     */
    public Integer getPerPage() {
        return perPage;
    }

    /**
     * Setter of perPage
     *
     * @param perPage Value of perPage to set
     */
    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    /**
     * Getter of nbPages
     *
     * @return nbPages
     */
    public Integer getNbPages() {
        return nbPages;
    }

    /**
     * Setter of nbPages
     *
     * @param nbPages Value of nbPages to set
     */
    public void setNbPages(Integer nbPages) {
        this.nbPages = nbPages;
    }

    /**
     * Getter of nbElements
     *
     * @return nbElements
     */
    public Long getNbElements() {
        return nbElements;
    }

    /**
     * Setter of nbElements
     *
     * @param nbElements Value of nbElements to set
     */
    public void setNbElements(Long nbElements) {
        this.nbElements = nbElements;
    }

    @JsonIgnore
    @Override
    public String getLogDetail() {
        return "{httpStatus:" + httpStatus + ", genericMessageDetailsList:" + LoggingUtil.getLogDetail(genericMessageDetailsList) + ", resultObject:" + LoggingUtil.getLogDetail(resultObject) + "}";
    }

    @JsonIgnore
    @Override
    public String getLog() {
        return "{httpStatus:" + httpStatus + ", genericMessageDetailsList:" + LoggingUtil.getLog(genericMessageDetailsList) + ", resultObject:" + LoggingUtil.getLog(resultObject) + "}";
    }
}
