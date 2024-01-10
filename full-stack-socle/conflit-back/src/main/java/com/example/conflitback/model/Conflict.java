package com.example.conflitback.model;

import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Conflict extends DateAudit {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "opponent_id")
    private Opponent opponent;

    private String service;

    @OneToMany(mappedBy = "conflict", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> documents = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Conflict conflict = (Conflict) o;
        return Objects.equals(id, conflict.id) && Objects.equals(client, conflict.client) && Objects.equals(opponent, conflict.opponent) && Objects.equals(service, conflict.service) && Objects.equals(documents, conflict.documents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, client, opponent, service, documents);
    }
}
