package org.example.modele;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;

@Entity
public class QuantiteComposant {

    @EmbeddedId
    QuantiteComposantpk id = new QuantiteComposantpk();

    private int quantite;
}
