package com.example.customannotation.model;

import com.example.customannotation.annotations.ConditionalValid;
import com.example.customannotation.annotations.NameValid;
import com.example.customannotation.annotations.NullOrNotBlank;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;


@Data
//@NameValid
@ToString
@ConditionalValid(field = "name" , dependentFields = {"firstName", "lastName"}, message = "Either name or (firstName and lastName) are required")
public class Employee {

   // CAN BE NULL, BUT IF PRESENT SHOULD NOT BE BLANK
   @NullOrNotBlank(message = "Employee Code cannot be null if present")
   private String employeeCode;

   @NotEmpty
   // EITHER NAME OR FIRSTNAME AND LASTNAME ARE REQUIRED
   private String name;
   private String firstName;
   private String lastName;

   // EITHER EMAIL OR PHONE IS REQUIRED
   private String email;
   private String phone;

   // IF verificationId NOT BLANK verifiedBy CAN NOT BE BLANK
   private String verificationId;
   private String verifiedBy;


}
