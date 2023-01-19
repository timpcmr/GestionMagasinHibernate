package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.controleur.ClientDAO;
import org.example.controleur.MagasinDAO;
import org.example.modele.Client;
import org.example.modele.Magasin;
import org.example.modele.Materiel;
import org.example.vue.VueConsole;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BDD");
        EntityManager em = emf.createEntityManager();

        VueConsole.afficherMenu();
        int choix = VueConsole.recupererEntier("Choisissez une option : ");

        switch(choix) {
            case 1 -> {
                int idUtilisateur = VueConsole.recupererEntier("Donnez votre identifiant utilisateur : ");
                afficherMagasinUtilisateur(idUtilisateur, em);
            }
            case 2 -> {
                int idUtilisateur = VueConsole.recupererEntier("Donnez votre identifiant utilisateur : ");
                creerCommandeUtilisateur(idUtilisateur, em);
            }
            case 3 -> {
                String nomMagasin = VueConsole.recupererTexte("Donnez le nom du magasin : ");
                afficherQuantiteMateriel(nomMagasin, em);
            }
            case 4 -> {
                String nomComposant = VueConsole.recupererTexte("Donnez le nom du composant : ");
                afficherMaterielComposant(nomComposant, em);
            }
            case 5 -> {
                int idUtilisateur = VueConsole.recupererEntier("Donnez votre identifiant utilisateur : ");
                afficherProfilUtilisateur(idUtilisateur, em);
            }
            case 6 -> {
                int idUtilisateur = VueConsole.recupererEntier("Donnez votre identifiant utilisateur : ");
                afficherCommandesUtilisateur(idUtilisateur, em);
            }
        }
    }

    // Option 1
    private static void afficherMagasinUtilisateur(int idUtilisateur, EntityManager em) {
        MagasinDAO magasinDAO = new MagasinDAO(em);
        Magasin magasinClient = magasinDAO.getMagasinByClient(idUtilisateur);

        if (magasinClient == null) {
            VueConsole.afficherAlerte("Le client n'a pas de magasin");
        }
        else{
            VueConsole.affichageMagasin(magasinClient);
        }

    }

    // Option 2
    private static void creerCommandeUtilisateur(int idUtilisateur, EntityManager em) {}

    // Option 3
    private static void afficherQuantiteMateriel(String nomMagasin, EntityManager em) {
    }

    // Option 4
    private static void afficherMaterielComposant(String nomComposant, EntityManager em) {
        List<Materiel> materiels
    }

    // Option 5
    private static void afficherProfilUtilisateur(int idUtilisateur, EntityManager em) {
        ClientDAO clientDAO = new ClientDAO(em);
        Client client = clientDAO.findClientById(idUtilisateur);

        if (client == null){
            VueConsole.afficherAlerte("Le client n'existe pas");
        }
        else {
            VueConsole.affichageClient(client);
        }
    }

    // Option 6
    private static void afficherCommandesUtilisateur(int idUtilisateur, EntityManager em) {
    }
}