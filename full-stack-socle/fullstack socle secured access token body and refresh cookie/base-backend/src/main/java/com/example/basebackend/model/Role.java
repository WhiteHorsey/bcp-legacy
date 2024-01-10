package com.example.basebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
   @Id
   @GeneratedValue(strategy = IDENTITY)
   private Integer id;

   @Enumerated(EnumType.STRING)
   @Column(length = 20)
   private ERole name;

   public Role(ERole name) {
      this.name = name;
   }
}
