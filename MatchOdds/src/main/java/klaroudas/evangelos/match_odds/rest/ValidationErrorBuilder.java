package klaroudas.evangelos.match_odds.rest;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * Simple Builder class for @see ValidationError
 * @author baggelis
 *
 */
public class ValidationErrorBuilder {

	public static ValidationError fromBindingErrors(Errors errors) {
		ValidationError error = new ValidationError(
				"Validation failed. " + errors.getErrorCount() + " error(s)");
		for (ObjectError objectError : errors.getAllErrors()) {
			error.addValidationError(objectError.getDefaultMessage());
		}
		return error;
	}

}
