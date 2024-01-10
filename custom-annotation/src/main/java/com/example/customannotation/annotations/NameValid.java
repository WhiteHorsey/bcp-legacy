package com.example.customannotation.annotations;

import com.example.customannotation.annotations.validators.NameValidValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {NameValidValidator.class})
@Retention(RUNTIME)
@Target({TYPE})
public @interface NameValid {
   String message() default "Either name or (firstName and lastName) are required";

   Class<?>[] groups() default { };

   Class<? extends Payload>[] payload() default { };
}
