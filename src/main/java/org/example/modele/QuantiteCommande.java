package org.example.modele;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;

@Entity
public class QuantiteCommande {

    @EmbeddedId
    QuantiteCommandepk id = new QuantiteCommandepk();

    private int Quantite;
}
