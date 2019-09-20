package ru.virtusystems.lotsmanova.interview.dto;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.virtusystems.lotsmanova.interview.model.Customer;

@Component
public class CustomerValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return Customer.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Customer customer = (Customer) target;

		if (customer.getPassportSeries() == null
				|| customer.getPassportSeries() < 1000
				|| customer.getPassportSeries() > 9999) {
			errors.rejectValue("passportSeries",
					"required correct passport series");
		}

		if (customer.getPassportId() == null
				|| customer.getPassportId() < 100000
				|| customer.getPassportSeries() > 999999) {
			errors.rejectValue("passportid", "required correct passport id");
		}

	}

}
