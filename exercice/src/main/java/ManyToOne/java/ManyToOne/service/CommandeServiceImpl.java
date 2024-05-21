package ManyToOne.java.ManyToOne.service;

import ManyToOne.java.ManyToOne.exceptions.httpexceptions.NotFoundException;
import ManyToOne.java.ManyToOne.model.Commande;
import ManyToOne.java.ManyToOne.model.Produit;
import ManyToOne.java.ManyToOne.model.Utilisateur;
import ManyToOne.java.ManyToOne.repository.CommandeRepository;
import ManyToOne.java.ManyToOne.repository.ProduitRepository;
import ManyToOne.java.ManyToOne.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeServiceImpl implements CommandeService {
    @Autowired
    public ProduitRepository produitRepository;
    @Autowired
    public UtilisateurRepository utilisateurRepository;
    @Autowired
    public CommandeRepository commandeRepository;
    @Override
    public ResponseEntity<Commande> createCommande(Commande commande,int userId, int produitId) {
        Optional<Produit> optionalProduit = produitRepository.findById(produitId);
        Optional<Utilisateur> optionalUser = utilisateurRepository.findById(userId);

        if (optionalProduit.isPresent() && optionalUser.isPresent()) {
            Produit produit = optionalProduit.get();

            Utilisateur utilisateur = optionalUser.get();

            // Créer une nouvelle liste de produits pour la commande
            List<Produit> produits = new ArrayList<>();
            produits.add(produit);
            commande.setProduits(produits);
            commande.setUtilisateur(utilisateur);

            // Sauvegarder la commande avec le produit associé
            Commande savedCommande = commandeRepository.save(commande);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedCommande);
        } else {
            throw new NotFoundException("Ce produit et cet utilisateur n'existe pas");
        }
    }


    @Override
    public ResponseEntity<List<Commande>> getAllCommande() {

        return ResponseEntity.ok(commandeRepository.findAll());
    }

    @Override
    public ResponseEntity<List<Commande>> getAllCommandeByUtilisateurId(Integer utilisateurId) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(utilisateurId);
        if (optionalUtilisateur.isPresent()){
            List<Commande> commandeList = commandeRepository.findAllCommandeByUtilisateurId(utilisateurId);
            return ResponseEntity.ok(commandeList);
        }
        else {
            throw new NotFoundException("Ce utilisateur n'existe pas");
        }
    }

    @Override
    public ResponseEntity<Commande> getCommande(Integer commandeId) {
        Optional<Commande> optionalCommande = commandeRepository.findById(commandeId);
        if (optionalCommande.isPresent()){
            Commande com = optionalCommande.get();
           return ResponseEntity.ok(com);
        }else {
            throw new NotFoundException("Cette commande n'existe pas");
        }
    }






   /* @Override
    public ResponseEntity<List<Produit>> getAllProduitByCommandeId(Integer commandeId) {
        Optional<Commande> optionalCommande = commandeRepository.findById(commandeId);
        if (optionalCommande.isPresent()){
            List<Produit> produitList = commandeRepository.finAllProduitByCommandeId(commandeId);
            return ResponseEntity.ok(produitList);
        }
        else {
            throw new NotFoundException("Ce utilisateur n'existe pas");
        }
    }


/*





    @Override
    public ResponseEntity<String> deleteCommande(int commandeId) {
        return null;
    }

    @Override
    public ResponseEntity<Commande> putCommande(int commandeId, Commande newCommande) {
        return null;
    }
   */
}
