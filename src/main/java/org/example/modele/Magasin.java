package org.example.modele;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Magasin implements Serializable {

    // Attributs

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idMagasin;

    private String nomMagasin;

    private String adresseMagasin;

    //Un magasin peut avoir plusieurs clients
    @OneToMany(mappedBy = "magasin", cascade = CascadeType.ALL)
    private List<Client> clients;

    //Un magasin peut avoir plusieurs matériels
    @ManyToMany(mappedBy = "magasins", cascade = CascadeType.ALL)
    private List<Materiel> materiels;

    //Un magasin peut avoir plusieurs materiel avec une quantite
    @ElementCollection
    @CollectionTable(name="posseder", joinColumns=@JoinColumn(name="idMagasin"), uniqueConstraints = @UniqueConstraint(columnNames = {"idMagasin", "idMateriel"}))
    @MapKeyJoinColumn(name="idMateriel")
    @Column(name="quantite")
    private Map<Materiel, Integer> quantiteMateriel;

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Materiel> getMateriels() {
        return materiels;
    }

    public void setMateriels(List<Materiel> materiels) {
        this.materiels = materiels;
    }

    public Map<Materiel, Integer> getQuantiteMateriel() {
        return quantiteMateriel;
    }

    public void setQuantiteMateriel(Map<Materiel, Integer> quantiteMateriel) {
        this.quantiteMateriel = quantiteMateriel;
    }



    // Constructeurs
    public Magasin() {
        this.idMagasin = -1;
        this.nomMagasin = "";
        this.adresseMagasin = "";
        //this.stockMateriel = new HashMap<>();
    }

    public Magasin(int idMagasin, String nomMagasin, String adresseMagasin) {
        this.idMagasin = idMagasin;
        this.nomMagasin = nomMagasin;
        this.adresseMagasin = adresseMagasin;
        //this.stockMateriel = new HashMap<>();
    }

    public Magasin(int idMagasin, String nomMagasin, String adresseMagasin, HashMap<Materiel, Integer> stockMateriel) {
        this.idMagasin = idMagasin;
        this.nomMagasin = nomMagasin;
        this.adresseMagasin = adresseMagasin;
        //this.stockMateriel = stockMateriel;
    }

    // Getters et Setters
    public int getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
    }

    public String getNomMagasin() {
        return nomMagasin;
    }

    public void setNomMagasin(String nomMagasin) {
        this.nomMagasin = nomMagasin;
    }

    public String getAdresseMagasin() {
        return adresseMagasin;
    }

    public void setAdresseMagasin(String adresseMagasin) {
        this.adresseMagasin = adresseMagasin;
    }

    // Méthodes

    public int MiseAJourDuStock(Materiel materiel, int quantite) {
        int quantiteDisponible = this.quantiteMateriel.get(materiel);
        if (quantite - quantiteDisponible > 0) {
            this.quantiteMateriel.put(materiel, quantiteDisponible - quantite);
        }
        else if (quantite - quantiteDisponible == 0) {
            this.quantiteMateriel.remove(materiel);
        }
        else{
            return -1;
        }
        return 0;
    }

    public Materiel verifyDisponibility(Materiel materiel, int quantite){
        if (this.getQuantiteMateriel().containsKey(materiel)){
            if (this.getQuantiteMateriel().get(materiel) >= quantite){
                return materiel;
            }
            else{
                if (materiel.getMaterielSubstitution() != null){
                    return verifyDisponibility(materiel.getMaterielSubstitution(), quantite);
                }
            }
        }
        return null;
    }

    public boolean isInStock (Materiel materiel){
        for (Materiel m : this.getQuantiteMateriel().keySet()){
            if (materiel.equals(m)){
                return true;
            }
        }
        return false;
    }



}
