package com.bezkoder.spring.security.jwt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

import javax.persistence.*;

@Entity(name = "refreshtoken")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RefreshToken {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;

   @OneToOne
   @JoinColumn(name = "user_id", referencedColumnName = "id")
   private User user;

   @Column(nullable = false, unique = true)
   private String token;

   @Column(nullable = false)
   private Instant expiryDate;

}
