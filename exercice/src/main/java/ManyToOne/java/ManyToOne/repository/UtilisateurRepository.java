package ManyToOne.java.ManyToOne.repository;

import ManyToOne.java.ManyToOne.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
}
