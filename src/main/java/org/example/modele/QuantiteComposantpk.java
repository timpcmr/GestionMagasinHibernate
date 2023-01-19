package org.example.modele;

import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class QuantiteComposantpk implements Serializable {

    private Integer idComposant;

    private Integer idMateriel;
}
