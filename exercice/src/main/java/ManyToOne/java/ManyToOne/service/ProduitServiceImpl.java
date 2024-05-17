package ManyToOne.java.ManyToOne.service;

import ManyToOne.java.ManyToOne.model.Produit;
import ManyToOne.java.ManyToOne.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitServiceImpl implements ProduitService{
    @Autowired
    public ProduitRepository produitRepository;
    @Override
    public ResponseEntity<Produit> createProduit(Produit produit) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produitRepository.save(produit));
    }

    @Override
    public ResponseEntity<List<Produit>> getAllProduit() {
        return ResponseEntity.ok(produitRepository.findAll());
    }

   /* @Override
    public ResponseEntity<Produit> getProduit(int produitId) {
        return null;
    }

    @Override
    public ResponseEntity<Produit> putProduit(int produitId, Produit newProduit) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteProduit(int produitId) {
        return null;
    }*/
}
