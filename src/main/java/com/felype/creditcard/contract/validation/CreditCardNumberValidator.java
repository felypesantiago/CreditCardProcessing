package com.felype.creditcard.contract.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CreditCardNumberValidator implements ConstraintValidator<CreditCardNumber, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return LuhnTenValidator.isValid(value);
    }

}