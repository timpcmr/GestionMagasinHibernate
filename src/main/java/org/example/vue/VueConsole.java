package org.example.vue;

import jakarta.persistence.EntityManager;
import org.example.controleur.MaterielDAO;
import org.example.modele.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VueConsole implements Vue {
    public void afficherMenu() {
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
    public String choixMagasin(){
        System.out.println("Entrez le nom du magasin dont vous souhaitez voir le contenu : ");
        Scanner scanner1 = new Scanner(System.in);
        scanner1.useDelimiter(System.lineSeparator());
        return scanner1.nextLine();
    }

    public void arretProgramme(String message){
        System.out.println("Arret du programme : " + message);
        System.exit(0);
    }

    public String recupererTexte(String message){
        System.out.println(message);
        Scanner scanner4 = new Scanner(System.in);
        return scanner4.nextLine();
    }

    public int recupererEntier(String message){
        System.out.println(message);
        Scanner scanner5 = new Scanner(System.in);
        return scanner5.nextInt();
    }

    public Commande saisieCommande (Client client, Magasin magasin, EntityManager em) {
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
    public void afficherException(Exception e){
        System.out.println("Une exception a été levée : " + e.getMessage());
    }

    public void afficherAlerte(String message){
        System.out.println("Une erreur a été levée : " + message);
    }

    public void afficherMessage(String message){
        System.out.println(message);
    }

    // AFFICHAGE DE LISTES

    public String afficherListe(List<?> liste, Class<?> classe){
        String message = "Voici la liste des objets de la classe" + classe.getSimpleName() + " : ";
        for (var o : liste){
            switch (classe.getSimpleName()){
                case "Magasin" -> {affichageMagasin((Magasin) o);}
                case "Client" -> {affichageClient((Client) o);}
                case "Materiel" -> {affichageMateriel((Materiel) o);}
                case "Commande" -> {affichageCommande((Commande) o);}
                case "Composant" -> {affichageComposant((Composant) o);}
            }
        }
        return message;
    }

    // AFFICHAGE DES OBJETS

    public String affichageClient(Client client){
        String objet = "\n\n/-------------"+ client.getPrenomClient() + " " + client.getNomClient() + "------------\\ \n" +
                "* Client n°" + client.getIdClient() + "\n" +
                "* Adresse : " + client.getAdresseClient() + "\n" +
                "* Téléphone : " + client.getTelephoneClient() + "\n" +
                "* Magasin : " + client.getMagasin().getNomMagasin();

        System.out.println(objet);
        return objet;
    }

    public String affichageMagasin(Magasin magasin){
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

    public String affichageMateriel(Materiel materiel){
        String valeur = "Materiel : \n" +
                "Identifiant = " + materiel.getIdMateriel() +
                "\nNom = " + materiel.getNomMateriel() +
                "\nCatégorie = " + materiel.getCategorie().getNomCategorieMateriel() + '\n';
        for(Composant key : materiel.getQuantiteComposant().keySet()){
            valeur += affichageComposant(key) + " | Quantité : " + materiel.getQuantiteComposant().get(key) + '\n';
        }
        System.out.println(valeur);
        return valeur;
    }

    public String affichageComposant (Composant composant){
        return "Composant [Identifiant = " + composant.getIdComposant() + ", Nom = " + composant.getNomComposant() + "]";
    }

    public String affichageCommande(Commande commande){
        String result = "";
        result += "Commande n° "+ commande.getIdCommande() + " de " + commande.getClient().getPrenomClient() + " " + commande.getClient().getNomClient() + " pour le magasin " + commande.getMagasin().getNomMagasin() + "\n";
        for (Materiel key : commande.getMateriels().keySet()){
            result += key.getNomMateriel() + " | Quantité : " + commande.getMateriels().get(key) + "\n";
        }
        System.out.println(result);
        return result;
    }
}
