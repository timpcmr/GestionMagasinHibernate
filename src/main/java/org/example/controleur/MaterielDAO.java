package org.example.controleur;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.modele.Composant;
import org.example.modele.Materiel;

import java.util.ArrayList;
import java.util.List;

public class MaterielDAO {
    private final EntityManager em;

    public MaterielDAO (EntityManager em) {
        this.em = em;
    }

    public Materiel findById (int id){
        return em.find(Materiel.class, id);
    }

    public Materiel findByName (String nom){
        Materiel materiel;
        String queryString = "SELECT m FROM Materiel m WHERE m.nomMateriel = :nom";
        Query query = em.createQuery(queryString).setParameter("nom", nom);

        try {
            return (Materiel) query.getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }

}
