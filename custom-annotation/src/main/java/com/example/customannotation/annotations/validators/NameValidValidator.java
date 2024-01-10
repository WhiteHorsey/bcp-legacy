package com.example.customannotation.annotations.validators;

import com.example.customannotation.annotations.NameValid;
import com.example.customannotation.model.Employee;
import org.apache.logging.log4j.util.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidValidator implements ConstraintValidator<NameValid, Employee> {
   @Override
   public boolean isValid(Employee employee, ConstraintValidatorContext context) {
      return
            Strings.isNotBlank(employee.getName()) ||
                  (
                        Strings.isNotBlank(employee.getFirstName())
                              && Strings.isNotBlank(employee.getLastName())
                  );
   }
}
