package com.felype.creditcard.contract.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = CreditCardNumberValidator.class)
@Documented
public @interface CreditCardNumber {

    String message() default "Not a valid credit card number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}