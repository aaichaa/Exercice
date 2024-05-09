package ManyToOne.java.ManyToOne.repository;

import ManyToOne.java.ManyToOne.model.Commande;
import ManyToOne.java.ManyToOne.model.Produit;
import ManyToOne.java.ManyToOne.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer> {

    List<Commande> findAllCommandeByUtilisateurId(Integer utilisateurId);
    // List<Produit> finAllProduitByCommandeId(Integer commandeId);
}
