package com.datnek.elearning.lib.models.exceptions;


import com.datnek.elearning.lib.common.enumeration.Language;

import java.util.HashMap;
import java.util.Map;

/**
 * Structure for all Sunlab exception
 *
 */
public abstract class ServiceError {

	/**
	 * error code
	 */
	private String code;

	/**
	 * field matching (can be null)
	 */
	private String field;

	/**
	 * context message
	 */
	private String[] context;

	/**
	 * pattern messages for the errors in all languages
	 */
	private Map<Language, String> messages;

	/**
	 * all messages for all errors in all languages
	 */
	protected static Map<String, Map<Language, String>> patterns = new HashMap<>();

	/**
	 * @param code
	 *            code erreur
	 */
	public ServiceError(String code) {
		this.code = code;
		messages = prepareMessages(code);
	}

	/**
	 * @param code
	 *            error code
	 * @param field
	 *            field concerned
	 * @param context
	 *            context for the massage building
	 */
	public ServiceError(String code, String field, String... context) {
		this.code = code;
		this.field = field;
		this.context = context;
		messages = prepareMessages(code);
	}

	/**
	 * prepare all messages pattern
	 */
	protected abstract void preparePattern();

	/**
	 * @param code
	 *            code erreur
	 * @return messages for the error
	 */
	private Map<Language, String> prepareMessages(String code) {
		preparePattern();
		return patterns.get(code);
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field
	 *            the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return the context
	 */
	public String[] getContext() {
		return context;
	}

	/**
	 * @param context
	 *            the context to set
	 */
	public void setContext(String[] context) {
		this.context = context;
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

}
