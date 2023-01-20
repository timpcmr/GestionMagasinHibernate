package org.example.controleur;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.modele.Composant;

public class ComposantDAO {
    private final EntityManager em;

    public ComposantDAO (EntityManager em) {
        this.em = em;
    }

    public Composant findComposantById (int id) {
        return em.find(Composant.class, id);
    }

    public Composant findComposantByName (String nom) {

        Composant composant;
        String queryString = "SELECT c FROM Composant c WHERE c.nomComposant = :nom";
        Query query = em.createQuery(queryString).setParameter("nom", nom);

        try {
            return (Composant) query.getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }


}
