package com.example.basebackend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Passport {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String number;

   // PASSPORT HAVE ONE STUDENT
   @OneToOne(mappedBy = "passport")
   private Student student;

   public void removeStudent(){
      this.getStudent().setPassport(null);
      this.setStudent(null);
   }

   public Passport(String number) {
      this.number = number;
   }
}
