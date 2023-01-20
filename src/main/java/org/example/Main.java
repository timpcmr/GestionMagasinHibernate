package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.controleur.*;
import org.example.modele.*;
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
                String nomMagasin = VueConsole.choixMagasin();
                afficherMagasinUtilisateur(nomMagasin, em);
            }
            case 2 -> {
                int idUtilisateur = VueConsole.recupererEntier("Donnez votre identifiant utilisateur : ");
                creerCommandeUtilisateur(idUtilisateur, em);
            }
            case 3 -> {
                String nomMateriel = VueConsole.recupererTexte("Donnez le nom du matériel : ");
                String nomMagasin = VueConsole.recupererTexte("Donnez le nom du magasin : ");

                afficherQuantiteMateriel(nomMagasin, nomMateriel, em);
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
    private static void afficherMagasinUtilisateur(String nomMagasin, EntityManager em) {
        MagasinDAO magasinDAO = new MagasinDAO(em);
        Magasin magasinClient = magasinDAO.findMagasinByName(nomMagasin);

        if (magasinClient == null) {
            VueConsole.afficherAlerte("Le magasin n'existe pas");
        }
        else{
            VueConsole.affichageMagasin(magasinClient);
        }

    }

    // Option 2
    private static void creerCommandeUtilisateur(int idUtilisateur, EntityManager em) {
        MagasinDAO magasinDAO = new MagasinDAO(em);
        Magasin magasinClient = magasinDAO.getMagasinByClient(idUtilisateur);

        ClientDAO clientDAO = new ClientDAO(em);
        Client client = clientDAO.findClientById(idUtilisateur);

        CommandeDAO commandeDAO = new CommandeDAO(em);

        Commande commande = VueConsole.saisieCommande(client, magasinClient, em);
        commandeDAO.uploadCommande(commande);
    }

    // Option 3
    private static void afficherQuantiteMateriel(String nomMagasin, String nomMateriel, EntityManager em) {
        MagasinDAO magasinDAO = new MagasinDAO(em);
        Magasin magasin = magasinDAO.findMagasinByName(nomMagasin);

        if (magasin == null) {
            VueConsole.afficherAlerte("Le magasin n'existe pas");
        }
        else {
            for (Materiel materiel : magasin.getQuantiteMateriel().keySet()) {
                if (materiel.getNomMateriel().equals(nomMateriel)) {
                    VueConsole.afficherMessage("Il y a " + magasin.getQuantiteMateriel().get(materiel) + " " + nomMateriel + " dans le magasin " + nomMagasin);
                    return;
                }
            }
            VueConsole.afficherAlerte("Le magasin ne contient pas de " + nomMateriel);
        }

    }

    // Option 4
    private static void afficherMaterielComposant(String nomComposant, EntityManager em) {
        ComposantDAO composantDAO = new ComposantDAO(em);
        Composant composant = composantDAO.findComposantByName(nomComposant);

        List<Materiel> materiels = composant.getMateriels();

        VueConsole.afficherListe(materiels, Materiel.class);
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
        ClientDAO clientDAO = new ClientDAO(em);
        Client client = clientDAO.findClientById(idUtilisateur);

        if (client == null){
            VueConsole.afficherAlerte("Le client n'existe pas");
        }
        else {
            VueConsole.afficherListe(client.getCommandes(), Commande.class);
        }
    }
}