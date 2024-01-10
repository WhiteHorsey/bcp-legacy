package com.example.customannotation.annotations;

import com.example.customannotation.annotations.validators.ConditionalValidValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target(TYPE)
@Retention(RUNTIME)
@Repeatable(ConditionalValid.List.class)
@Constraint(validatedBy = {ConditionalValidValidator.class})
public @interface ConditionalValid {

   String message() default "Invalid";

   Class<?>[] groups() default { };

   Class<? extends Payload>[] payload() default { };

   String field();

   String[] dependentFields();

   @Target({ TYPE })
   @Retention(RUNTIME)
   @Documented
   public @interface List {
      ConditionalValid[] value();
   }
}
