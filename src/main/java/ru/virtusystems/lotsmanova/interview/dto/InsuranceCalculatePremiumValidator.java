package ru.virtusystems.lotsmanova.interview.dto;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.virtusystems.lotsmanova.interview.model.InsurantDocument;

public class InsuranceCalculatePremiumValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return InsurantDocument.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		InsurantDocument document = (InsurantDocument) target;

		if (document.getInsurance() == null) {
			errors.rejectValue("insurance", "required insurance");
		}

		LocalDate startDate = document.getStartDate();
		LocalDate endDate = document.getEndDate();
		if (startDate != null && endDate != null) {
			LocalDate today = LocalDate.now();
			Period period = Period.between(startDate, endDate);
			if (startDate.isBefore(today)) {
				errors.rejectValue("startDate", "start date is incorrect");
			} else if (!startDate.isBefore(endDate)
					|| (period.getMonths() > 12)) {
				errors.rejectValue("endDate", "end date is incorrect");
			}
		}else {
			if (startDate == null) {
				errors.rejectValue("startDate", "required start date");
			}
			if (endDate == null) {
				errors.rejectValue("endDate", "required end date");
			}
		}

		if (document.getRealty() == null) {
			errors.rejectValue("realty", "required realty object");
		} else {
			if (document.getRealty().getRealtyType() == null) {
				errors.pushNestedPath("realty");
				errors.rejectValue("realtyType",
						"required correct realty type");
				errors.popNestedPath();
			}

			String constructionYear = document.getRealty()
					.getConstructionYear();
			if (!constructionYear.matches("^(19|20)[0-9][0-9]")) {
				errors.pushNestedPath("realty");
				errors.rejectValue("constructionYear",
						"construction year is incorrect");
				errors.popNestedPath();
			}
		}
		
	}

}
