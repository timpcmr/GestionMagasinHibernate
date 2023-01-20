package org.example.modele;

import jakarta.persistence.*;
import java.util.List;
import java.util.Map;

import java.util.HashMap;
import java.util.Objects;

@Entity
public class Materiel {

    // Attributs
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idMateriel;

    private String nomMateriel;


    //Un matériel peut avoir plusieurs composants
    @ManyToMany(mappedBy = "materiels")
    private List<Composant> composants;

    //Un matériel a une catégorie
    @ManyToOne
    @JoinColumn(name="idCategorieMateriel")
    private CategorieMateriel categorie;

    //Chaque matériel a une quantité de composant
    @ElementCollection
    @CollectionTable(name="composer", joinColumns = @JoinColumn(name="idMateriel"), uniqueConstraints = @UniqueConstraint(columnNames = {"idMateriel", "idComposant"}))
    @MapKeyJoinColumn(name="idComposant")
    @Column(name="quantiteComposant")
    private Map<Composant, Integer> quantiteComposant;

    //Un matériel a un matériel de substitution
    @OneToOne
    @JoinColumn(name="materielSubstitution")
    private Materiel materielSubstitution;

    //Un matériel peut avoir plusieurs magasins
    @ManyToMany
    @JoinTable(name="materiel_magasin",
            joinColumns=@JoinColumn(name="idMateriel"),
            inverseJoinColumns=@JoinColumn(name="idMagasin"))
    private List<Magasin> magasins;


    // Constructeurs

    public Materiel() {
        this.idMateriel = -1;
        this.nomMateriel = "";
        this.materielSubstitution = null;
    }

    public Materiel(int idMateriel, String nomMateriel, String categorieMateriel) {
        this.idMateriel = idMateriel;
        this.nomMateriel = nomMateriel;
        this.materielSubstitution = null;

    }

    public Materiel(int idMateriel, String nomMateriel, String categorieMateriel, HashMap<Composant, Integer> quantiteComposant, Materiel materielSubstitution) {
        this.idMateriel = idMateriel;
        this.nomMateriel = nomMateriel;
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

    public Materiel getMaterielSubstitution() {

        return this.materielSubstitution;
    }

    public int getMaterielSubstitutionId() {
        return materielSubstitution.getIdMateriel();
    }

    public void setMaterielSubstitution(Materiel materielSubstitution) {
        this.materielSubstitution = materielSubstitution;
    }



    public void replaceComposantQuantity(int idComposant, int quantite){

    }

    public List<Composant> getComposants() {
        return composants;
    }

    public void setComposants(List<Composant> composants) {
        this.composants = composants;
    }

    public CategorieMateriel getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieMateriel categorie) {
        this.categorie = categorie;
    }

    public Map<Composant, Integer> getQuantiteComposant() {
        return quantiteComposant;
    }

    public void setQuantiteComposant(Map<Composant, Integer> quantiteComposant) {
        this.quantiteComposant = quantiteComposant;
    }

    public List<Magasin> getMagasins() {
        return magasins;
    }

    public void setMagasins(List<Magasin> magasins) {
        this.magasins = magasins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Materiel materiel = (Materiel) o;
        return idMateriel == materiel.idMateriel && nomMateriel.equals(materiel.nomMateriel) && categorie.equals(materiel.categorie) && Objects.equals(materielSubstitution, materiel.materielSubstitution);
    }
}
