package org.example.controleur;

import org.example.modele.Client;

import jakarta.persistence.EntityManager;

public class ClientDAO {

    private final EntityManager em;

    public ClientDAO (EntityManager em) {
        this.em = em;
    }

    public Client findClientById (int idClient) {
        return em.find(Client.class, idClient);
    }

}
