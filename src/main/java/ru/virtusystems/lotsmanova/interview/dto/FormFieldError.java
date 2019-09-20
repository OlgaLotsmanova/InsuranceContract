package ru.virtusystems.lotsmanova.interview.dto;

import java.io.Serializable;

public class FormFieldError implements Serializable {

	private static final long serialVersionUID = 1L;

	private String objectName;

	private String field;

	private String message;

	public FormFieldError() {

	}

	public FormFieldError(String objectName, String field, String message) {
		this.objectName = objectName;
		this.field = field;
		this.message = message;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "FormFieldError [objectName=" + objectName + ", field=" + field
				+ ", message=" + message + "]";
	}
}
