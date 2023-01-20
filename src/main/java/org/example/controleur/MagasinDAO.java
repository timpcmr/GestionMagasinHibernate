package org.example.controleur;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.modele.Magasin;

import java.util.List;

public class MagasinDAO {
    private final EntityManager em;

    public MagasinDAO (EntityManager em){
        this.em = em;
    }

    public Magasin getMagasinById(int id){
        return em.find(Magasin.class, id);
    }

    public Magasin getMagasinByClient(int id){
        String stringQuery = "SELECT m FROM Magasin m JOIN m.clients c WHERE c.idClient = :id";
        Query query = em.createQuery(stringQuery).setParameter("id", id);

        try {
            return (Magasin) query.getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }

    public Magasin findMagasinByName(String nom){
        String stringQuery = "SELECT m FROM Magasin m WHERE m.nomMagasin = :nom";
        Query query = em.createQuery(stringQuery).setParameter("nom", nom);

        try {
            return (Magasin) query.getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }
}
