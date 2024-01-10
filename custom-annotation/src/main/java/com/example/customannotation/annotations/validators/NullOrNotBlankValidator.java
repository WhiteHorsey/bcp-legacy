package com.example.customannotation.annotations.validators;

import com.example.customannotation.annotations.NullOrNotBlank;
import org.apache.logging.log4j.util.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, String> {
   @Override
   public boolean isValid(String value, ConstraintValidatorContext context) {
      return Objects.isNull(value) || Strings.isNotBlank(value);
   }
}
