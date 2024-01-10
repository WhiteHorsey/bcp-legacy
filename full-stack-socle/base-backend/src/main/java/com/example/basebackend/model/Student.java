package com.example.basebackend.model;

import com.example.basebackend.payload.response.StudentDetailsResponse;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Student {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String firstName;
   private String lastName;
   private String email;

   // OWNING SIDE : STUDENT HAS ONE PASSPORT
   @OneToOne
   @JoinColumn(name = "passport_id", referencedColumnName = "id")
   private Passport passport;

   // STUDENT HAVE MANY CARS
   @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Car> cars = new ArrayList<>();

   // STUDENT CAN HAVE MANY TAGS
   @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
   @JoinTable(name = "student_tags", joinColumns = {@JoinColumn(name = "student_id")}, inverseJoinColumns = {@JoinColumn(name = "tag_id")})
   private List<Tag> tags = new ArrayList<>();

   // Passport
   public void addPassport(Passport passport) {
      this.setPassport(passport);
   }

   // CARS
   public void addCar(Car car) {
      this.getCars().add(car);
      car.setStudent(this);
   }

   public void removeCar(Car car) {
      this.getCars().remove(car);
      car.setStudent(null);
   }

   // TAGS
   public void addTag(Tag tag) {
      this.tags.add(tag);
      tag.getStudents().add(this);
   }

   public void removeTag(long tagId) {
      // FIND TAG IN THE LIST OF TAGS OF THE STUDENT
      Tag tag = this.tags.stream().filter(t -> t.getId() == tagId).findFirst().orElse(null);
      // IF EXISTS REMOVE IT FORM BOTH LISTS
      if (tag != null) {
         this.tags.remove(tag);
         tag.getStudents().remove(this);
      }
   }



}
