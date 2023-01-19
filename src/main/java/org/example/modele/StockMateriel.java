package org.example.modele;

import jakarta.persistence.*;

@Entity
public class StockMateriel {

    @EmbeddedId
    StockMaterielpk id = new StockMaterielpk();

    private int Quantite;
}
