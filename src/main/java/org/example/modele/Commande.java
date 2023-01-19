package org.example.modele;

import jakarta.persistence.*;
import org.example.controleur.MaterielDAO;
import java.util.Map;
import java.security.Key;
import java.util.HashMap;

@Entity
public class Commande {


    // Attributs

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idCommande;

    private int idClient;

    private int idMagasin;

    // Constructeurs
    public Commande() {
        this.idCommande = -1;
        this.idClient = -1;
        this.idMagasin = -1;
        //MaterielCommande = new HashMap<>();
    }

    public Commande(int idClient, int idMagasin) {
        this.idCommande = -1;
        this.idClient = idClient;
        this.idMagasin = idMagasin;
        //MaterielCommande = new HashMap<>();
    }

    public Commande(int idCommande, int idClient, int idMagasin) {
        this.idCommande = idCommande;
        this.idClient = idClient;
        this.idMagasin = idMagasin;
        //MaterielCommande = new HashMap<>();
    }


    public Commande(int idCommande, int idClient, int idMagasin, HashMap<Materiel, Integer> MaterielCommande) {
        this.idCommande = idCommande;
        this.idClient = idClient;
        this.idMagasin = idMagasin;
        //this.MaterielCommande = MaterielCommande;
    }

    // Getters et Setters
    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
    }
    /*public HashMap<Materiel, Integer> getMaterielCommande() {
        return MaterielCommande;
    }*/

    /*public void setMaterielCommande(HashMap<Materiel, Integer> materielCommande) {
        MaterielCommande = materielCommande;
    }*/

    /*public void addMaterielCommande (int idMateriel, int quantite){

        Materiel Key_to_use = null;
        for(Materiel key : MaterielCommande.keySet()){
            if (key.getIdMateriel() == idMateriel){
                Key_to_use = key;
                break;
            }
        }

        if (Key_to_use != null) {
            int newValue = this.MaterielCommande.get(Key_to_use) + quantite;
            if (newValue < 0){
                suppMaterielCommande(idCommande);
            }
            this.MaterielCommande.replace(Key_to_use, this.MaterielCommande.get(Key_to_use) + quantite);
        } else {
            this.MaterielCommande.put(MaterielDAO.getMateriel(idMateriel), quantite);
        }

    }*/

    /*public void addMaterielCommande (String nomMateriel, int quantite){

        Materiel Key_to_use = null;
        int idMateriel = MaterielDAO.getIdFromName(nomMateriel);
        for(Materiel key : MaterielCommande.keySet()){
            if (key.getIdMateriel() == idMateriel){
                Key_to_use = key;
                break;
            }
        }

        if (Key_to_use != null) {
            int newValue = this.MaterielCommande.get(Key_to_use) + quantite;
            if (newValue < 0){
                suppMaterielCommande(idCommande);
            }
            this.MaterielCommande.replace(Key_to_use, this.MaterielCommande.get(Key_to_use) + quantite);
        } else {
            this.MaterielCommande.put(MaterielDAO.getMateriel(idMateriel), quantite);
        }

    }*/

    /*public void suppMaterielCommande (int idMateriel){
        Materiel Key_to_Delete = null;
        for(Materiel key : MaterielCommande.keySet()){
            if (key.getIdMateriel() == idMateriel){
                Key_to_Delete = key;
                break;
            }
        }
        if (Key_to_Delete != null){
           this.MaterielCommande.remove(Key_to_Delete);
        }

    }*/

}
