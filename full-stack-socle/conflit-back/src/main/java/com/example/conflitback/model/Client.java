package com.example.conflitback.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String civility;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    private String societyName;
    private String nameOfGeneralRepresentative;

    private String address;
    private String zipCode;
    private String city;
    private boolean isPerson;

    @OneToMany(mappedBy = "client",cascade = ALL)
    private List<Conflict> conflicts;

}
