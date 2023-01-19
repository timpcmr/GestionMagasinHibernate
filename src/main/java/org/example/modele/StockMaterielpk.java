package org.example.modele;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class StockMaterielpk implements Serializable {

    private Integer idMagasin;

    private Integer idMateriel;

}
