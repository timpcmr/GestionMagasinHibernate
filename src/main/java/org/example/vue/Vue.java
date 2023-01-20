package org.example.vue;

import jakarta.persistence.EntityManager;
import org.example.modele.*;

import java.util.List;

public interface Vue {
    public void afficherMenu();
    public String choixMagasin();
    public void arretProgramme(String message);
    public void afficherAlerte(String message);
    public void afficherMessage(String message);
    public void afficherException(Exception e);
    public String recupererTexte(String message);
    public int recupererEntier(String message);
    public Commande saisieCommande(Client client, Magasin magasin, EntityManager em);
    public String afficherListe(List<?> liste, Class<?> classe);

    public String affichageClient(Client client);
    public String affichageCommande(Commande commande);
    public String affichageMagasin(Magasin magasin);
    public String affichageMateriel(Materiel materiel);
    public String affichageComposant(Composant composant);

}
