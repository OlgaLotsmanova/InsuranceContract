package ru.virtusystems.lotsmanova.interview.dto;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ValidationErrorHandlerAdvice {

	private static final String UNCOLLECT_ERROR = "typeMismatch";

	@ExceptionHandler({ BindException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public List<FormFieldError> handleMethodArgumentNotValid(BindException ex) {

		BindingResult result = ex.getBindingResult();

		List<FormFieldError> formFieldErrors = new LinkedList<FormFieldError>();

		List<FieldError> fieldErrors = result.getFieldErrors();

		for (FieldError fieldError : fieldErrors) {
			if (!fieldError.getCode().equals(UNCOLLECT_ERROR)) {
				String defaultMessage = fieldError.getDefaultMessage();
				String message = (defaultMessage != null
						&& defaultMessage.length() > 0) ? defaultMessage
								: fieldError.getCode();
				formFieldErrors
						.add(new FormFieldError(fieldError.getObjectName(),
								fieldError.getField(), message));
			}
		}

		return formFieldErrors;
	}

}
