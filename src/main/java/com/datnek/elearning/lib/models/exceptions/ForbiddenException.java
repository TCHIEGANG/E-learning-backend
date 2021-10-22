package com.datnek.elearning.lib.models.exceptions;

import com.datnek.elearning.lib.common.enumeration.Language;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Structure for all Fobidden exception
 *
 */
public class ForbiddenException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -933728129775135908L;

	/**
	 * forbidden code
	 * 
	 * @param error
	 *            only error in the exception
	 */
	public ForbiddenException(ServiceError error) {
		super(error);
	}

	/**
	 * Build an Exception having several Errors
	 * 
	 * @param errors
	 *            list of errors
	 */
	public ForbiddenException(List<ServiceError> errors) {
		super(errors);
	}

	@Override
	protected void prepareException() {
		messages.put(Language.FR, "Action interdite");
		messages.put(Language.EN, "Forbidden action");
		code = HttpStatus.FORBIDDEN;
	}

}
