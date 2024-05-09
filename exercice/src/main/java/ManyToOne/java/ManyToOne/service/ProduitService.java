package ManyToOne.java.ManyToOne.service;

import ManyToOne.java.ManyToOne.model.Produit;
import ManyToOne.java.ManyToOne.model.Utilisateur;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProduitService {
    public ResponseEntity<Produit> createProduit(Produit produit);
    public ResponseEntity<List<Produit>> getAllProduit();
   /* public ResponseEntity<Produit> getProduit(int produitId);
    public ResponseEntity<Produit> putProduit(int produitId, Produit newProduit);

    public ResponseEntity<String> deleteProduit(int produitId);*/
}
