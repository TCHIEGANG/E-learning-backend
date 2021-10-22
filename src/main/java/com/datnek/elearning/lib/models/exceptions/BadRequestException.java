package com.datnek.elearning.lib.models.exceptions;
import com.datnek.elearning.lib.common.enumeration.Language;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Structure for all bad request exception
 *
 */
public class BadRequestException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 383792122994667924L;

	/**
	 * @param error
	 *            only error in the exception
	 */
	public BadRequestException(ServiceError error) {
		super(error);
	}

	/**
	 * Build an Exception having several Errors
	 * 
	 * @param errors
	 *            list of errors
	 */
	public BadRequestException(List<ServiceError> errors) {
		super(errors);
	}

	@Override
	protected void prepareException() {
		messages.put(Language.FR, "Mauvaise requete");
		messages.put(Language.EN, "Bad request");
		code = HttpStatus.BAD_REQUEST;
	}

}
