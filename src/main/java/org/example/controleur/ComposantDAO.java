package org.example.controleur;

import jakarta.persistence.EntityManager;
import org.example.modele.Composant;

public class ComposantDAO {
    private final EntityManager em;

    public ComposantDAO (EntityManager em) {
        this.em = em;
    }

    public Composant findCommandeById (int id) {
        return em.find(Composant.class, id);
    }


}
