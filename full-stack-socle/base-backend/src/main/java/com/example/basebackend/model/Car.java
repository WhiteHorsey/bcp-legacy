package com.example.basebackend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Car {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String model;
   private String year;

      // A CAR BELONGS TO A STUDENT
      @ManyToOne()
      @JoinColumn(name = "student_id")
      private Student student;

   public Car(String model, String year) {
      this.model = model;
      this.year = year;
   }
}
