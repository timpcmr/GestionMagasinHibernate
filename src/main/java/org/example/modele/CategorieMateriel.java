package org.example.modele;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CategorieMateriel {

    @Id
    private int idCategorieMateriel;

    private String nomCategorieMateriel;

    public int getIdCategorieMateriel() {
        return idCategorieMateriel;
    }

    public void setIdCategorieMateriel(int idCategorieMateriel) {
        this.idCategorieMateriel = idCategorieMateriel;
    }

    public String getNomCategorieMateriel() {
        return nomCategorieMateriel;
    }

    public void setNomCategorieMateriel(String nomCategorieMateriel) {
        this.nomCategorieMateriel = nomCategorieMateriel;
    }
}
