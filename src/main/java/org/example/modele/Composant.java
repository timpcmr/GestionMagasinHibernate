package org.example.modele;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Composant {

    // Attributs

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idComposant;

    private String nomComposant;

    // Constructeurs
    public Composant() {
        this.idComposant = -1;
        this.nomComposant = "";
    }

    public Composant(int idComposant, String nomComposant) {
        this.idComposant = idComposant;
        this.nomComposant = nomComposant;
    }

    // Getters et Setters
    public int getIdComposant() {
        return idComposant;
    }

    public void setIdComposant(int idComposant) {
        this.idComposant = idComposant;
    }

    public String getNomComposant() {
        return nomComposant;
    }

    public void setNomComposant(String nomComposant) {
        this.nomComposant = nomComposant;
    }


}
