package org.example.vue;

import jakarta.persistence.EntityManager;
import org.example.controleur.MaterielDAO;
import org.example.modele.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public static Commande saisieCommande (Client client, Magasin magasin, EntityManager em) {
        Map<Materiel, Integer> materiauxCommande = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        MaterielDAO materielDAO = new MaterielDAO(em);

        System.out.println("Début de la commande. Entrez le nom du materiel désiré. Pour terminer votre commande, tapez fin");
        String nomMateriel = scanner.next();
        while (!nomMateriel.equals("fin")) {
            Materiel materielSelectionne = materielDAO.findByName(nomMateriel);

            if (materielSelectionne == null) {
                System.out.println("Ce matériel n'est pas disponible dans ce magasin. Entrez un nouveau nom de matériel ou tapez fin pour terminer votre commande : ");
            }

            // Vérification de l'existence du matériel
            else if(magasin.isInStock(materielSelectionne)) {
                System.out.println("Combien en souhaitez-vous ? ");
                int quantite = scanner.nextInt();

                // Vérification de la quantité disponible
                Materiel newMateriel = magasin.verifyDisponibility(materielSelectionne, quantite);
                boolean respectSeuil = client.verifierSeuil(newMateriel.getCategorie(), quantite);

                if (!newMateriel.equals(materielSelectionne) && newMateriel != null) {
                    System.out.println("Le materiel sélectionné n'était pas disponible en quantité suffisante, il a été substitué");
                }
                // Ajout du matériel à la commande
                if (newMateriel == null) {
                    System.out.println("Le matériel n'est pas disponible en quantité suffisante dans ce magasin");
                }else{
                    if (respectSeuil) {
                        materiauxCommande.put(newMateriel, quantite);
                    } else {
                        System.out.println("Le matériel n'a pas été ajouté à la commande car le seuil de commande a été atteint");
                    }
                }
                System.out.println("Entrez un nouveau nom de matériel ou tapez fin pour terminer votre commande : ");
            }
            else{
                System.out.println("Ce matériel n'est pas disponible dans ce magasin. Entrez un nouveau nom de matériel ou tapez fin pour terminer votre commande : ");
            }
            nomMateriel = scanner.next();
        }

        return new Commande(client, magasin, materiauxCommande);
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

    // AFFICHAGE DE LISTES

    public static String afficherListe(List<?> liste, Class<?> classe){
        String message = "Voici la liste des objets de la classe" + classe.getSimpleName() + " : ";
        for (var o : liste){
            switch (classe.getSimpleName()){
                case "Magasin" -> {VueConsole.affichageMagasin((Magasin) o);}
                case "Client" -> {VueConsole.affichageClient((Client) o);}
                case "Materiel" -> {VueConsole.affichageMateriel((Materiel) o);}
                case "Commande" -> {VueConsole.affichageCommande((Commande) o);}
                case "Composant" -> {VueConsole.affichageComposant((Composant) o);}
            }
        }
        return message;
    }

    // AFFICHAGE DES OBJETS

    public static String affichageClient(Client client){
        String objet = "\n\n/-------------"+ client.getPrenomClient() + " " + client.getNomClient() + "------------\\ \n" +
                "* Client n°" + client.getIdClient() + "\n" +
                "* Adresse : " + client.getAdresseClient() + "\n" +
                "* Téléphone : " + client.getTelephoneClient() + "\n" +
                "* Magasin : " + client.getMagasin().getNomMagasin();

        System.out.println(objet);
        return objet;
    }

    public static String affichageMagasin(Magasin magasin){
        String result = "\n\n/ Magasin : " + magasin.getNomMagasin() + " - (Identifiant : "+ magasin.getIdMagasin() + ") - " + magasin.getAdresseMagasin() + " \\ \n"
                + "Stock : \n";
        if(magasin.getQuantiteMateriel().isEmpty()){
            result += "Le magasin a un stock vide !";
        }
        else {
            for (Materiel key : magasin.getQuantiteMateriel().keySet()) {
                result += key.getNomMateriel() + " : " + magasin.getQuantiteMateriel().get(key) + "\n";
            }
        }
        System.out.println(result);
        return result;
    }

    public static String affichageMateriel(Materiel materiel){
        String valeur = "Materiel : \n" +
                "Identifiant = " + materiel.getIdMateriel() +
                "\nNom = " + materiel.getNomMateriel() +
                "\nCatégorie = " + materiel.getCategorie() + '\n';
        for(Composant key : materiel.getQuantiteComposant().keySet()){
            valeur += affichageComposant(key) + " | Quantité : " + materiel.getQuantiteComposant().get(key) + '\n';
        }
        System.out.println(valeur);
        return valeur;
    }

    public static String affichageComposant (Composant composant){
        String valeur = "Composant [Identifiant = " + composant.getIdComposant() + ", Nom = " + composant.getNomComposant() + "]";
        System.out.println(valeur);
        return valeur;
    }

    public static String affichageCommande(Commande commande){
        String result = "";
        result += "Commande de " + commande.getClient().getPrenomClient() + " " + commande.getClient().getNomClient() + " pour le magasin " + commande.getMagasin().getNomMagasin() + "\n";
        for (Materiel key : commande.getMateriels().keySet()){
            result += key.getNomMateriel() + " | Quantité : " + commande.getMateriels().get(key) + "\n";
        }
        System.out.println(result);
        return result;
    }
}
