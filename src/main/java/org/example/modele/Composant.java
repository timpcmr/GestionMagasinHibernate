package org.example.modele;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Composant implements Serializable {

    // Attributs

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idComposant;

    private String nomComposant;

    //Un composant peut avoir plusieurs matériaux
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="composer",
            joinColumns=@JoinColumn(name="idComposant"),
            inverseJoinColumns=@JoinColumn(name="idMateriel"))
    private List<Materiel> materiels;


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

    public List<Materiel> getMateriels() {
        return materiels;
    }

    public void setMateriels(List<Materiel> materiels) {
        this.materiels = materiels;
    }

}
