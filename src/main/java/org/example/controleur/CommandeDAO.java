package org.example.controleur;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.modele.Commande;
import org.example.modele.Magasin;
import org.example.modele.Materiel;
import org.example.modele.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandeDAO {
    private final EntityManager em;

    public CommandeDAO (EntityManager em){
        this.em = em;
    }

    public Commande findCommandeById (int id){
        return em.find(Commande.class, id);
    }

    public List<Commande> findCommandeByClient(int idClient){

        List<Commande> commandes = new ArrayList<>();

        String stringQuery = "SELECT c FROM Commande c JOIN c.client cl WHERE cl.idClient = :idClient";
        Query query = em.createQuery(stringQuery).setParameter("client", idClient);
        List<Object[]> resultList;
        try{
            resultList = query.getResultList();
        }
        catch (jakarta.persistence.NoResultException e){
            return null;
        }


        for (Object[] result : resultList){
            Commande commande = (Commande) result[1];
            commandes.add(commande);
        }

        return commandes;
    }

    public void uploadCommande (Commande commande) {

        // Récupération d'un id de commande disponible
        int idCommande = 0;
        String stringQuery = "SELECT max(c.idCommande) FROM Commande c";
        Query query = em.createQuery(stringQuery);
        try{
            idCommande = (int) query.getSingleResult();
            idCommande++;
        }
        catch (jakarta.persistence.NoResultException ignored){}

        commande.setIdCommande(idCommande);
        //Afficher l'id de la commande
        System.out.println("ID de la commande : " + commande.getIdCommande());

        // Insertion de la commande
        em.getTransaction().begin();
        em.merge(commande);

        //em.persist(commande);
        em.getTransaction().commit();

        // Actualisation du stock
        Magasin magasin = commande.getMagasin();

        Map<Materiel, Integer> materiels = commande.getMateriels();
        for (Materiel materiel : materiels.keySet()){
            magasin.MiseAJourDuStock(materiel, materiels.get(materiel));
        }
        em.getTransaction().begin();
        Magasin attachedMagasin = em.merge(magasin);
        em.getTransaction().commit();

        // Actualisation du seuil de commandes

        Client client = commande.getClient();

        for (Materiel materiel : commande.getMateriels().keySet()){
            client.MiseAJourSeuil(materiel.getCategorie(), commande.getMateriels().get(materiel));
        }
        client.getCommandes().add(commande);

        em.getTransaction().begin();
        Client attachedClient = em.merge(client);
        em.getTransaction().commit();

    }
}
