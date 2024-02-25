package com.project.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GmailValidator implements ConstraintValidator<Gmail, String>{

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return email != null && email.endsWith("@gmail.com");
	}

}
