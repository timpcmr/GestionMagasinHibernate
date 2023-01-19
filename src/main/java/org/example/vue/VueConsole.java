package org.example.vue;

import java.util.Scanner;

public class VueConsole {
    public static void afficherMenu() {
        System.out.println("/ ------------------------------Choisir une option--------------------------- \\");
        System.out.println("* 1. Afficher le contenu d'un magasin                                         *");
        System.out.println("* 2. Créer une commande cliente avec les détails du client et des matériaux   *");
        System.out.println("* 3. Connaitre la quantité disponible d'un matériel dans un magasin donné     *");
        System.out.println("* 4. Rechercher tous les matériaux dans lesquels on trouve un composant donné *");
        System.out.println("* 5. Afficher le profil d'un client                                           *");
        System.out.println("* 6. Afficher les commandes d'un client                                       *");
        System.out.println("\\ --------------------------------------------------------------------------- /");

    }

    // INTERACTIONS AVEC L'UTILISATEUR
    public static String choixMagasin(){
        System.out.println("Entrez le nom du magasin dont vous souhaitez voir le contenu : ");
        Scanner scanner1 = new Scanner(System.in);
        scanner1.useDelimiter(System.lineSeparator());
        return scanner1.nextLine();
    }

    public static void arretProgramme(String message){
        System.out.println("Arret du programme : " + message);
        System.exit(0);
    }

    public static String[] recupererQuantiteMateriel(){
        System.out.println("De quel matériel souhaitez-vous voir la quantité ? ");
        Scanner scanner3 = new Scanner(System. in);
        String nomMateriel2 = scanner3.nextLine();
        System.out.println("Quel magasin vouez-vous voir le stock de ce matériel ?");
        String nomMagasin2 = scanner3.nextLine();
        return new String[]{nomMateriel2, nomMagasin2};
    }

    public static void afficherQuantiteMateriel(String nomMateriel, String nomMagasin, int quantite){
        System.out.println("Le materiel " + nomMateriel + " est disponible en quantité " + quantite + " dans le magasin " + nomMagasin);
    }

    public static String recupererTexte(String message){
        System.out.println(message);
        Scanner scanner4 = new Scanner(System.in);
        return scanner4.nextLine();
    }

    public static int recupererEntier(String message){
        System.out.println(message);
        Scanner scanner5 = new Scanner(System.in);
        return scanner5.nextInt();
    }

    // AFFICHAGE DES ERREURS
    public static void afficherException(Exception e){
        System.out.println("Une exception a été levée : " + e.getMessage());
    }

    public static void afficherAlerte(String message){
        System.out.println("Une erreur a été levée : " + message);
    }

    public static void afficherMessage(String message){
        System.out.println(message);
    }

}
