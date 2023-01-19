package org.example.modele;

import javax.persistence.*;


@Entity
public class Client {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idClient;

    private String prenomClient;

    private String nomClient;

    private String adresseClient;

    private String telephoneClient;

    @ManyToOne(cascade = CascadeType.ALL)
    private Magasin magasin;

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

}
