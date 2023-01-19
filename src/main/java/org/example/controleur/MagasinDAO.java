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
        String stringQuery = "from Magasin where Magasin.idMagasin = :id";
        Query query = em.createQuery(stringQuery).setParameter("id", id);

        return (Magasin)query.getSingleResult();
    }
}
