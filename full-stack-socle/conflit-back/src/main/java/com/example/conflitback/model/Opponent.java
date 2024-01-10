package com.example.conflitback.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Opponent {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String civility;
    private String firstName;
    private String lastName;
    private String email;
    private String daughterName;

    private String societyName;
    private String complement;

    private String address;
    private String zipCode;
    private String city;
    private String country;
    private boolean isPerson;

    @OneToMany(mappedBy = "opponent")
    private List<Conflict> conflicts;
}
