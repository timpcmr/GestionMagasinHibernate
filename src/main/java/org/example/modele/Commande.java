package org.example.modele;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

@Entity
public class Commande implements Serializable {


    // Attributs

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idCommande;

    //Une commande n'a qu'un seul client
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="idClient")
    private Client client;

    //Une commande n'a qu'un seul magasin
    @ManyToOne(cascade = CascadeType.MERGE)
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

        this.quantiteMateriel = new HashMap<>();
        for (Materiel m : quantiteMateriel.keySet()) {
            this.quantiteMateriel.put(m, quantiteMateriel.get(m));
        }

        this.materiels = new ArrayList<>();
        this.materiels.addAll(quantiteMateriel.keySet());
    }

    public Commande(Commande commande) {
        this.idCommande = commande.idCommande;
        this.client = commande.client;
        this.magasin = commande.magasin;
        this.quantiteMateriel = commande.quantiteMateriel;
        this.materiels = commande.materiels;
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
