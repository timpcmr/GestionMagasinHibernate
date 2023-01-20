package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.controleur.*;
import org.example.modele.*;
import org.example.vue.Vue;
import org.example.vue.VueConsole;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BDD");
        EntityManager em = emf.createEntityManager();
        VueConsole vue = new VueConsole();

        vue.afficherMenu();
        int choix = vue.recupererEntier("Choisissez une option : ");

        switch(choix) {
            case 1 -> {
                String nomMagasin = vue.choixMagasin();
                afficherMagasinUtilisateur(nomMagasin, em, vue);
            }
            case 2 -> {
                int idUtilisateur = vue.recupererEntier("Donnez votre identifiant utilisateur : ");
                creerCommandeUtilisateur(idUtilisateur, em, vue);
            }
            case 3 -> {
                String nomMateriel = vue.recupererTexte("Donnez le nom du matériel : ");
                String nomMagasin = vue.recupererTexte("Donnez le nom du magasin : ");

                afficherQuantiteMateriel(nomMagasin, nomMateriel, em, vue);
            }
            case 4 -> {
                String nomComposant = vue.recupererTexte("Donnez le nom du composant : ");
                afficherMaterielComposant(nomComposant, em, vue);
            }
            case 5 -> {
                int idUtilisateur = vue.recupererEntier("Donnez votre identifiant utilisateur : ");
                afficherProfilUtilisateur(idUtilisateur, em, vue);
            }
            case 6 -> {
                int idUtilisateur = vue.recupererEntier("Donnez votre identifiant utilisateur : ");
                afficherCommandesUtilisateur(idUtilisateur, em, vue);
            }
        }
    }

    // Option 1
    private static void afficherMagasinUtilisateur(String nomMagasin, EntityManager em, Vue vue) {
        MagasinDAO magasinDAO = new MagasinDAO(em);
        Magasin magasinClient = magasinDAO.findMagasinByName(nomMagasin);

        if (magasinClient == null) {
            vue.afficherAlerte("Le magasin n'existe pas");
        }
        else{
            vue.affichageMagasin(magasinClient);
        }

    }

    // Option 2
    private static void creerCommandeUtilisateur(int idUtilisateur, EntityManager em, Vue vue) {
        MagasinDAO magasinDAO = new MagasinDAO(em);
        Magasin magasinClient = magasinDAO.getMagasinByClient(idUtilisateur);

        ClientDAO clientDAO = new ClientDAO(em);
        Client client = clientDAO.findClientById(idUtilisateur);

        CommandeDAO commandeDAO = new CommandeDAO(em);

        Commande commande = vue.saisieCommande(client, magasinClient, em);
        commandeDAO.uploadCommande(commande);
    }

    // Option 3
    private static void afficherQuantiteMateriel(String nomMagasin, String nomMateriel, EntityManager em, Vue vue) {
        MagasinDAO magasinDAO = new MagasinDAO(em);
        Magasin magasin = magasinDAO.findMagasinByName(nomMagasin);

        if (magasin == null) {
            vue.afficherAlerte("Le magasin n'existe pas");
        }
        else {
            for (Materiel materiel : magasin.getQuantiteMateriel().keySet()) {
                if (materiel.getNomMateriel().equals(nomMateriel)) {
                    vue.afficherMessage("Il y a " + magasin.getQuantiteMateriel().get(materiel) + " " + nomMateriel + " dans le magasin " + nomMagasin);
                    return;
                }
            }
            vue.afficherAlerte("Le magasin ne contient pas de " + nomMateriel);
        }

    }

    // Option 4
    private static void afficherMaterielComposant(String nomComposant, EntityManager em, Vue vue) {
        ComposantDAO composantDAO = new ComposantDAO(em);
        Composant composant = composantDAO.findComposantByName(nomComposant);

        List<Materiel> materiels = composant.getMateriels();

        vue.afficherListe(materiels, Materiel.class);
    }

    // Option 5
    private static void afficherProfilUtilisateur(int idUtilisateur, EntityManager em, Vue vue) {
        ClientDAO clientDAO = new ClientDAO(em);
        Client client = clientDAO.findClientById(idUtilisateur);

        if (client == null){
            vue.afficherAlerte("Le client n'existe pas");
        }
        else {
            vue.affichageClient(client);
        }
    }

    // Option 6
    private static void afficherCommandesUtilisateur(int idUtilisateur, EntityManager em, Vue vue) {
        ClientDAO clientDAO = new ClientDAO(em);
        Client client = clientDAO.findClientById(idUtilisateur);

        if (client == null){
            vue.afficherAlerte("Le client n'existe pas");
        }
        else {
            vue.afficherListe(client.getCommandes(), Commande.class);
        }
    }
}