package com.fernandocanabarro.desafio_ze_delivery.services.validations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = PartnerRequestDTOValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PartnerRequestDTOValid {

    String message() default "Validation Error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
