package com.example.customannotation.annotations;

import com.example.customannotation.annotations.validators.NullOrNotBlankValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

// VALIDATOR
@Constraint(validatedBy = {NullOrNotBlankValidator.class})
// WHERE THIS ANNOTATION ILL BE APPLIED AT RUNTIME
@Retention(RUNTIME)
// WHERE THIS ANNOTATION CAN BE USED ON FIELD OR CLASS CALLED TYPE
@Target({FIELD})
public @interface NullOrNotBlank {

   String message() default "{javax.validation.constraints.NullOrNotBlank.message}";

   Class<?>[] groups() default { };

   Class<? extends Payload>[] payload() default { };
}
