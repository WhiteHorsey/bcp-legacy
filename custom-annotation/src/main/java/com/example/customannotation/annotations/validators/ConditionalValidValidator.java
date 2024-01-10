package com.example.customannotation.annotations.validators;

import com.example.customannotation.annotations.ConditionalValid;
import com.example.customannotation.model.Employee;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.util.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ConditionalValidValidator implements ConstraintValidator<ConditionalValid, Object> {

   private String field;
   private String[] dependentFields;

   @Override
   public void initialize(ConditionalValid constraintAnnotation) {
      field = constraintAnnotation.field();
      dependentFields = constraintAnnotation.dependentFields();
      ConstraintValidator.super.initialize(constraintAnnotation);
   }

   @SneakyThrows
   @Override
   public boolean isValid(Object value, ConstraintValidatorContext context) {
      String property = BeanUtils.getProperty(value, field);
      return Strings.isNotBlank(property) || areDependentFieldsNonBlank(value, dependentFields);
   }

   private boolean areDependentFieldsNonBlank(Object value, String[] dependentFields) {
      return Arrays.stream(dependentFields)
            .noneMatch(s -> {
               try {
                  return Strings.isBlank(BeanUtils.getProperty(value, s));
               } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                  e.printStackTrace();
               }
               return true;
            });
   }


}
