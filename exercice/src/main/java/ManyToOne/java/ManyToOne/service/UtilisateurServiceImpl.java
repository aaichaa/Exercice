package ManyToOne.java.ManyToOne.service;

import ManyToOne.java.ManyToOne.exceptions.httpexceptions.NotFoundException;
import ManyToOne.java.ManyToOne.model.Utilisateur;
import ManyToOne.java.ManyToOne.repository.UtilisateurRepository;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
    @Autowired
    public UtilisateurRepository utilisateurRepository;

    @Override
    public ResponseEntity<Utilisateur> createUtilisateur(Utilisateur utilisateur) {

        return ResponseEntity.status(HttpStatus.CREATED).body(utilisateurRepository.save(utilisateur));
    }

    @Override
    public ResponseEntity<List<Utilisateur>> getAllUtilisateur() {
        return ResponseEntity.ok(utilisateurRepository.findAll());
    }

    @Override
    public ResponseEntity<Utilisateur> getUtilisateur(int utilisateurId) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(utilisateurId);
        if (optionalUtilisateur.isPresent()) {
            Utilisateur user = optionalUtilisateur.get();
            return ResponseEntity.ok(user);
        } else {
            throw new NotFoundException("cet utilisateur n'existe pas ");
        }
    }

    @Override
    public ResponseEntity<Utilisateur> putUtilisateur(int utilisateurId, Utilisateur newUtilisateur) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(utilisateurId);
        if (optionalUtilisateur.isPresent()) {
            Utilisateur user = optionalUtilisateur.get();
            user.setNom(newUtilisateur.getNom());
            user.setEmail(newUtilisateur.getEmail());
            return ResponseEntity.ok(utilisateurRepository.save(user));
        } else {
            throw new NotFoundException("cet utilisateur n'existe pas ");
        }
    }

    @Override
    public ResponseEntity<String> deleteUtilisateur(int utilisateurId) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(utilisateurId);
        if (optionalUtilisateur.isPresent()) {
            utilisateurRepository.deleteById(utilisateurId);

            return ResponseEntity.ok("Utilisateur supprimé avec succès");
        } else {
            throw new NotFoundException("cet utilisateur n'existe pas ");
        }
    }





   /*
      @Override
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurByCommandeId(Integer CommandeId) {
        return null;
    }*/

}

