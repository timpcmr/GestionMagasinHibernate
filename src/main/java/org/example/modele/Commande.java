package org.example.modele;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

@Entity
public class Commande {


    // Attributs

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idCommande;

    //Une commande n'a qu'un seul client
    @ManyToOne
    @JoinColumn(name="idClient")
    private Client client;

    //Une commande n'a qu'un seul magasin
    @ManyToOne
    @JoinColumn(name="idMagasin")
    private Magasin magasin;

    //Une commande a plusieurs materiels
    @ManyToMany
    private List<Materiel> materiels;

    //Chaque matériel a une quantité
    @ElementCollection
    @CollectionTable(name="quantifier", joinColumns=@JoinColumn(name="idCommande"), uniqueConstraints = @UniqueConstraint(columnNames = {"idCommande", "idMateriel"}))
    @MapKeyJoinColumn(name="idMateriel")
    @Column(name="quantiteCommande")
    private Map<Materiel, Integer> quantiteMateriel;

    // Constructeurs
    public Commande() {
        this.idCommande = -1;
        this.client = null;
        this.magasin = null;
        this.quantiteMateriel = new HashMap<>();
        this.materiels = new ArrayList<>();
    }

    public Commande(Client client, Magasin magasin, Map<Materiel, Integer> quantiteMateriel) {
        this.client = client;
        this.magasin = magasin;
        this.quantiteMateriel = quantiteMateriel;
        this.materiels = new ArrayList<>();
        this.materiels.addAll(quantiteMateriel.keySet());
    }

    // Getters et Setters
    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Magasin getMagasin() {
        return this.magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public Map<Materiel, Integer> getMateriels (){
        return this.quantiteMateriel;
    }

    public void setQuantiteMateriel (Map<Materiel, Integer> quantiteMateriel){
        this.quantiteMateriel = quantiteMateriel;
    }
}
