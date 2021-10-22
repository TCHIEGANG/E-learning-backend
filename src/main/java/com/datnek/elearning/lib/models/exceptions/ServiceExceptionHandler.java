package com.datnek.elearning.lib.models.exceptions;

import com.datnek.elearning.lib.common.enumeration.Language;
import org.hornetq.utils.json.JSONArray;
import org.hornetq.utils.json.JSONException;
import org.hornetq.utils.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

/**
 * Class utilitaire pour les exceptions
 */
public class ServiceExceptionHandler {

	/** Logger de la classe */
	public static final Logger LOGGER = LoggerFactory.getLogger(ServiceExceptionHandler.class);

	/**
	 * Build JSON response for Exception
	 * 
	 * @param e
	 *            Exception
	 * @param locale
	 *            language of the request
	 * @return error json
	 * @throws JSONException
	 *             format failed
	 */
	// TODO :-O=
	// Plutôt que de générer nous-même la chaîne JSON, définir une class avec les attributs désirés puis retourner cet objet
	// des méthodes @ExceptionHandler. Spring et Jackson se chargeront de convertir l'objet en JSON
	// Voir fr.minnot.exception.BindingExceptionDto
	public static String buildExceptionBody(ServiceException e, Locale locale) throws JSONException {
		Language language;
		if (locale.equals(Locale.FRENCH) || locale.equals(Locale.FRANCE)) {
			language = Language.FR;
		} else {
			language = Language.EN;
		}

		JSONArray errors = new JSONArray();
		// building error list in JSON
		for (ServiceError minError : e.getErrors()) {
			JSONObject jError = new JSONObject();
			// handle message building
			if (minError.getContext() != null && minError.getContext().length != 0) {
				// Handle if there is some data missing for the context
				try {
					jError.put("message", String.format(minError.getMessages().get(language), (Object[]) minError.getContext()));
				} catch (Exception e2) {
					LOGGER.error("Wrong context for exception message building");
					jError.put("message", minError.getMessages().get(language));
				}
			} else {
				jError.put("message", minError.getMessages().get(language));
			}
			// field
			if (minError.getField() != null) {
				jError.put("field", minError.getField());
			}
			// error code
			if (minError.getCode() != null) {
				jError.put("code", minError.getCode());
			}
			// add error to the exception list
			errors.put(jError);
		}
		// finalize exception
		JSONObject exception = new JSONObject();
		exception.put("message", e.getMessages().get(language));
		exception.put("errors", errors);

		return exception.toString();
	}
}
