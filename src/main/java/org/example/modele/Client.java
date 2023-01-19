package org.example.modele;

import jakarta.persistence.*;
import java.util.List;
import java.util.Map;


@Entity
public class Client {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idClient;

    private String prenomClient;

    private String nomClient;

    private String adresseClient;

    private String telephoneClient;

    //Un client peut avoir un seul magasin référencé par idMagasin

    @ManyToOne
    @JoinColumn(name="idMagasin")
    private Magasin magasin;

    //Un client peut avoir plusieurs commandes
    @OneToMany(mappedBy = "client")
    private List<Commande> commandes;

    //Un client a un seuil par catégorie de matériel
    @ElementCollection
    @CollectionTable(name="seuil", joinColumns = @JoinColumn(name="idClient"), uniqueConstraints = @UniqueConstraint(columnNames = {"idClient", "idCategorieMateriel"}))
    @MapKeyJoinColumn(name="idCategorieMateriel")
    @Column(name="seuil")
    private Map<String, Integer> seuil;


    public Client() {
        this.idClient = -1;
        this.prenomClient = "";
        this.nomClient = "";
        this.adresseClient = "";
        this.telephoneClient = "";
        this.magasin = null;
    }

    public Client (int idClient) {

    }
    public Client(int idClient, String prenomClient, String nomClient, String adresseClient, String telephoneClient, Magasin magasin) {
        this.idClient = idClient;
        this.prenomClient = prenomClient;
        this.nomClient = nomClient;
        this.adresseClient = adresseClient;
        this.telephoneClient = telephoneClient;
        this.magasin = magasin;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getAdresseClient() {
        return adresseClient;
    }

    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }

    public String getTelephoneClient() {
        return telephoneClient;
    }

    public void setTelephoneClient(String telephoneClient) {
        this.telephoneClient = telephoneClient;
    }

    public Magasin getMagasin() {
        return this.magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public Map<String, Integer> getSeuil() {
        return seuil;
    }

    public void setSeuil(Map<String, Integer> seuil) {
        this.seuil = seuil;
    }

    // Méthodes

    public void MiseAJourDuSeuil(String categorieMateriel, int quantite){
        for (String key : seuil.keySet()){
            if (key.equals(categorieMateriel)){
                seuil.put(key, seuil.get(key) - quantite);
            }
        }
    }

    public boolean verificationSeuil(String categorieMateriel, int quantite){
        for (String key : seuil.keySet()){
            if (key.equals(categorieMateriel)){
                if (seuil.get(key) - quantite < 0){
                    return false;
                }
                else {
                    return true;
                }
            }
        }
        return false;
    }
}
