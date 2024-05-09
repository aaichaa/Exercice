package ManyToOne.java.ManyToOne.service;

import ManyToOne.java.ManyToOne.model.Commande;
import ManyToOne.java.ManyToOne.model.Produit;
import ManyToOne.java.ManyToOne.model.Utilisateur;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommandeService {
    public ResponseEntity<Commande> createCommande(Commande commande,int userId, int produitId);
    public ResponseEntity<List<Commande>> getAllCommandeByUtilisateurId(Integer utilisateurId);
    //public ResponseEntity<List<Produit>> getAllProduitByCommandeId(Integer commandeId);
    // public ResponseEntity<Commande> getCommande(Integer commandeId);
    public ResponseEntity<Commande> getCommande (Integer commandeId);
    public ResponseEntity<List<Commande>> getAllCommande();





   /*
    public ResponseEntity<List<Commande>> getAllCommandeByUtilisateurId(Integer utilisateurId);
    public ResponseEntity<String> deleteCommande(int commandeId);
    public ResponseEntity<Commande> putCommande(int commandeId, Commande newCommande);*/
}
