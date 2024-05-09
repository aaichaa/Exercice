package ManyToOne.java.ManyToOne.service;

import ManyToOne.java.ManyToOne.model.Commande;
import ManyToOne.java.ManyToOne.model.Utilisateur;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UtilisateurService {
    public ResponseEntity<Utilisateur> createUtilisateur(Utilisateur utilisateur);
    public ResponseEntity<List<Utilisateur>> getAllUtilisateur();
    public ResponseEntity<Utilisateur> getUtilisateur(int utilisateurId);
    //public ResponseEntity<List<Utilisateur>> getAllUtilisateurByCommandeId(Integer CommandeId);
    public ResponseEntity<Utilisateur> putUtilisateur(int utilisateurId, Utilisateur newUtilisateur);

      public ResponseEntity<String> deleteUtilisateur(int utilisateurId);
}
