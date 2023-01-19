package org.example.modele;

import jakarta.persistence.*;
import org.example.bdd.Bdd;
import org.example.controleur.MaterielDAO;
import org.example.vue.VueConsole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Entity
public class Magasin {

    // Attributs

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idMagasin;

    private String nomMagasin;

    private String adresseMagasin;

    @OneToMany(mappedBy = "idClient")
    private List<Client> Client;

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

    /*public HashMap<Materiel, Integer> getStockMateriel() {
        return stockMateriel;
    }*/

    /*public void setStockMateriel(HashMap<Materiel, Integer> stockMateriel) {
        this.stockMateriel = stockMateriel;
    }*/

    // Méthodes

    /*public void ajouterMateriel(int idMateriel, int quantite) {

        Materiel Key_to_use = null;
        for(Materiel key : stockMateriel.keySet()){
            if (key.getIdMateriel() == idMateriel){
                Key_to_use = key;
                break;
            }
        }
        if (Key_to_use != null) {
            int newValue = this.stockMateriel.get(Key_to_use) + quantite;
            if (newValue < 0){
                retirerMateriel(idMateriel);
            }
            this.stockMateriel.replace(Key_to_use, this.stockMateriel.get(Key_to_use) + quantite);
        } else {
            this.stockMateriel.put(MaterielDAO.getMateriel(idMateriel), quantite);
        }
    }*/

    /*public void retirerMateriel(int idMateriel) {

        Materiel Key_to_Delete = null;
        for(Materiel key : stockMateriel.keySet()){
            if (key.getIdMateriel() == idMateriel){
                Key_to_Delete = key;
                break;
            }
        }

        if (Key_to_Delete != null){
            this.stockMateriel.remove(Key_to_Delete);
        }
    }*/

    /*public void replaceMateriel(int idMateriel, int quantite){

        if (quantite == 0){
            retirerMateriel(idMateriel);
        } else if (!stockMateriel.containsKey(idMateriel)) {
            stockMateriel.put(MaterielDAO.getMateriel(idMateriel), quantite);
        } else{
            for(Materiel key : stockMateriel.keySet()) {
                if (key.getIdMateriel() == idMateriel) {
                    stockMateriel.replace(key, stockMateriel.get(key), quantite);
                }
            }
        }
    }*/


    /*public int verifyDisponibility(int idMateriel, int quantite){

        for (Materiel m : this.getStockMateriel().keySet()){
            if ( m.getIdMateriel() == idMateriel){
                if(this.getStockMateriel().get(m) >= quantite){
                    return idMateriel;
                }else{
                    int newMateriel = MaterielDAO.getMateriel(idMateriel).getMaterielSubstitutionId();
                    if (newMateriel != -1){
                        return verifyDisponibility(newMateriel, quantite);
                    }
                    break;
                }
            }
        }
        return -1;
    }*/

    /*public void miseAJourDuStock(HashMap<Integer, Integer> listeMateriel) {
            for (int idMateriel : listeMateriel.keySet()) {
                if (this.getStockMateriel().get(idMateriel) - listeMateriel.get(idMateriel) >= 0) {

                }
                int quantite = listeMateriel.get(idMateriel);

            }
    }*/
}
