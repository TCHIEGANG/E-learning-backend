package com.datnek.elearning.lib.models.exceptions;

import com.datnek.elearning.lib.common.enumeration.Language;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Structure for all Sunlab exception
 *
 */
public abstract class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5705624471834322083L;

	/**
	 * 
	 */
	protected HttpStatus code;

	/**
	 * 
	 */
	protected Map<Language, String> messages = new HashMap<>();

	/**
	 * 
	 */
	private List<ServiceError> errors = new ArrayList<>();

	/**
	 * Element for build an Exception having one Error
	 * 
	 * @param exception
	 *            only error in the exception
	 */
	public ServiceException(Exception exception) {
		super(exception);
	}
	
	/**
	 * Element for build an Exception having one Error
	 * 
	 * @param error
	 *            only error in the exception
	 */
	public ServiceException(ServiceError error) {
		super();
		this.errors.add(error);
		prepareException();
	}

	/**
	 * Build an Exception having several Errors
	 * 
	 * @param errors
	 *            list of errors
	 */
	public ServiceException(List<ServiceError> errors) {
		super();
		this.errors = errors;
		prepareException();
	}

	/**
	 * define the httpstatus code and the messages in all language for the exception
	 */
	protected abstract void prepareException();

	/**
	 * @return the code
	 */
	public HttpStatus getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(HttpStatus code) {
		this.code = code;
	}

	/**
	 * @return the messages
	 */
	public Map<Language, String> getMessages() {
		return messages;
	}

	/**
	 * @param messages
	 *            the messages to set
	 */
	public void setMessages(Map<Language, String> messages) {
		this.messages = messages;
	}

	/**
	 * @return the errors
	 */
	public List<ServiceError> getErrors() {
		return errors;
	}

	/**
	 * @param errors
	 *            the errors to set
	 */
	public void setErrors(List<ServiceError> errors) {
		this.errors = errors;
	}
	
	/**
	 * @param error
	 *            the error to set
	 */
	public void addError(ServiceError error) {
		errors.add(error);
	}
	
	@Override
	public String getMessage() {
		StringBuilder messageBuilder = new StringBuilder();

		if (!getMessages().isEmpty()) {
			messageBuilder.append(getMessages().get(Language.FR));
		}

		if (!getErrors().isEmpty()) {
			messageBuilder.append(" - ");
			messageBuilder.append(getErrors().stream()
					.map(e -> e.getMessages().get(Language.FR))
					.collect(Collectors.toList()));
		}

		if (messageBuilder.length() > 0) {
			return messageBuilder.toString();
		}
		
		return super.getMessage();
	}

}
