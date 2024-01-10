package com.example.conflitback.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // MANY DOCUMENT BELONGS TO A CONFLICT
    @ManyToOne()
    @JoinColumn(name = "conflict_id")
    private Conflict conflict;
}
