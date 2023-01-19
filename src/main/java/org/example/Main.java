package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.vue.VueConsole;

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

    private static void afficherCommandesUtilisateur(int idUtilisateur, EntityManager em) {
    }

    private static void afficherProfilUtilisateur(int idUtilisateur, EntityManager em) {
    }

    private static void afficherMaterielComposant(String nomComposant, EntityManager em) {
    }

    private static void afficherQuantiteMateriel(String nomMagasin, EntityManager em) {
    }

    private static void creerCommandeUtilisateur(int idUtilisateur, EntityManager em) {
        
    }

    private static void afficherMagasinUtilisateur(int idUtilisateur, EntityManager em) {
    }
}