package org.example.controleur;

import jakarta.persistence.EntityManager;
import org.example.modele.Materiel;

public class MaterielDAO {
    private final EntityManager em;

    public MaterielDAO (EntityManager em) {
        this.em = em;
    }

    public Materiel findById (int id){
        return em.find(Materiel.class, id);
    }

    public List<Materiel> findCommandeByComposant(Composant){

        List<Materiel> materiaux = new ArrayList<>();

        String stringQuery = "from Commande where Commande.client = :client";
        Query query = em.createQuery(stringQuery).setParameter("client", idClient);

        List<Object[]> resultList = query.getResultList();

        for (Object[] result : resultList){
            Commande commande = (Commande) result[1];
            commandes.add(commande);
        }

        return commandes;
    }
}
