package org.example.modele;

import jakarta.persistence.*;
import org.example.controleur.ComposantDAO;
import org.example.controleur.MaterielDAO;

import java.util.HashMap;

@Entity
public class Materiel {

    // Attributs
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idMateriel;

    private String nomMateriel;

    private String categorieMateriel;


    @OneToOne
    private Materiel materielSubstitution;

    // Constructeurs

    public Materiel() {
        this.idMateriel = -1;
        this.nomMateriel = "";
        this.categorieMateriel = "";
        //this.quantiteComposant = new HashMap<>();
        this.materielSubstitution = null;
    }

    public Materiel(int idMateriel, String nomMateriel, String categorieMateriel) {
        this.idMateriel = idMateriel;
        this.nomMateriel = nomMateriel;
        this.categorieMateriel = categorieMateriel;
        //this.quantiteComposant = new HashMap<>();
        this.materielSubstitution = null;

    }

    public Materiel(int idMateriel, String nomMateriel, String categorieMateriel, HashMap<Composant, Integer> quantiteComposant, Materiel materielSubstitution) {
        this.idMateriel = idMateriel;
        this.nomMateriel = nomMateriel;
        this.categorieMateriel = categorieMateriel;
        //this.quantiteComposant = quantiteComposant;
        this.materielSubstitution = materielSubstitution;

    }

    // Getters et Setters
    public int getIdMateriel() {
        return idMateriel;
    }

    public void setIdMateriel(int idMateriel) {
        this.idMateriel = idMateriel;
    }

    public String getNomMateriel() {
        return nomMateriel;
    }

    public void setNomMateriel(String nomMateriel) {
        this.nomMateriel = nomMateriel;
    }

    public String getCategorieMateriel() {
        return categorieMateriel;
    }

    public void setCategorieMateriel(String categorieMateriel) {
        this.categorieMateriel = categorieMateriel;
    }

    /*public HashMap<Composant, Integer> getQuantiteComposant() {
        return quantiteComposant;
    }*/

    /*public void setQuantiteComposant(HashMap<Composant, Integer> quantiteComposant) {
        this.quantiteComposant = quantiteComposant;
    }*/

    public Materiel getMaterielSubstitution() {

        return this.materielSubstitution;
    }

    public int getMaterielSubstitutionId() {
        return materielSubstitution.getIdMateriel();
    }

    public void setMaterielSubstitution(Materiel materielSubstitution) {
        this.materielSubstitution = materielSubstitution;
    }

    /*public Integer getQuantityOfComposant(int idComposant) {

        Composant Key_to_use = null;
        for(Composant key : quantiteComposant.keySet()){
            if (key.getIdComposant() == idComposant){
                Key_to_use = key;
                break;
            }
        }

        if (Key_to_use != null){
            return quantiteComposant.get(Key_to_use);
        }

        return 0;
    }*/
    // Méthodes

    /*public void ajouterComposant(int idComposant, int quantite) {

        Composant Key_to_use = null;
        for(Composant key : quantiteComposant.keySet()){
            if (key.getIdComposant() == idComposant){
                Key_to_use = key;
                break;
            }
        }*/


        /*if (Key_to_use != null) {
            int newValue = this.quantiteComposant.get(Key_to_use) + quantite;
            if (newValue < 0){
                retirerComposant(idComposant);
            }

            this.quantiteComposant.replace(Key_to_use, newValue);
        } else {
            this.quantiteComposant.put(ComposantDAO.getComposant(idComposant), quantite);
        }
    }*/

    public void replaceComposantQuantity(int idComposant, int quantite){

    }

    /*public void retirerComposant(int idComposant) {
        Composant Key_to_delete = null;
        for(Composant key : quantiteComposant.keySet()){
            if (key.getIdComposant() == idComposant){
                Key_to_delete = key;
                break;
            }
        }
        if (Key_to_delete != null) {
            this.quantiteComposant.remove(Key_to_delete);
        }
    }*/


}
