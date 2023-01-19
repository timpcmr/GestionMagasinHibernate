package org.example.modele;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class QuantiteCommandepk implements Serializable {

    private Integer idCommande;

    private Integer idMateriel;
}
