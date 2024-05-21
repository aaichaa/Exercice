package ManyToOne.java.ManyToOne.commande;


import ManyToOne.java.ManyToOne.exceptions.httpexceptions.NotFoundException;
import ManyToOne.java.ManyToOne.model.Commande;
import ManyToOne.java.ManyToOne.model.Produit;
import ManyToOne.java.ManyToOne.model.Utilisateur;
import ManyToOne.java.ManyToOne.repository.CommandeRepository;
import ManyToOne.java.ManyToOne.repository.ProduitRepository;
import ManyToOne.java.ManyToOne.repository.UtilisateurRepository;
import ManyToOne.java.ManyToOne.service.CommandeServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Log4j2
public class CommandeExceptionTest  {


    @InjectMocks
    CommandeServiceImpl commandeServiceImplementation;

    @Mock
    CommandeRepository commandeRepository;

    @Mock
    UtilisateurRepository utilisateurRepository;

    @Mock
    ProduitRepository produitRepository;

    private Commande commande;
    List<Produit> produitList;

    List<Commande> commandeList;

    private Utilisateur utilisateur;

    private Produit produit;


    @BeforeEach
    void setup() {

        produit = new Produit();
        produit.setId(7);
        produit.setNom("Tomate");
        produit.setPrix(60);
        produit.setDescription("rouge");
        produit.setQuantiteEnStock(9);

        utilisateur = new Utilisateur();
        utilisateur.setId(7);
        utilisateur.setNom("Diallo");
        utilisateur.setEmail("abd2004@gmail.com");


        produitList = new ArrayList<>();
        produitList.add(produit);


        commande = new Commande();
        commande.setId(4);
        commande.setDateCommande("12-03-2000");
        commande.setUtilisateur(utilisateur);
        commande.setProduits(produitList);

        commandeList = new ArrayList<>();
        commandeList.add(commande);



    }


    @DisplayName("JUnit test for createCommande")
    @Test
    void testcreateCommande_thenThrowNotFoundException() {

        when(produitRepository.findById(produit.getId())).thenReturn(Optional.empty());

        when(utilisateurRepository.findById(utilisateur.getId())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(NotFoundException.class, () -> {
            commandeServiceImplementation.createCommande(commande, utilisateur.getId(), produit.getId());
        });

        assertEquals("Ce produit et cet utilisateur n'existe pas", exception.getMessage());

        log.info(exception.getMessage());

    }





    @DisplayName("JUnit test for getAllCommandeByUtilisateurId")
    @Test
    void testgetAllCommandeByUtilisateurId_thenThrowNotFoundException() {

        when(utilisateurRepository.findById(utilisateur.getId())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(NotFoundException.class, () ->{
            commandeServiceImplementation.getAllCommandeByUtilisateurId(utilisateur.getId());
        });
        assertEquals("Ce utilisateur n'existe pas", exception.getMessage());
        log.info(exception.getMessage());


    }



    @DisplayName("JUnit test for getCommande")
    @Test
    void testgetCommande_thenThrowNotFoundException() {
        when(commandeRepository.findById(commande.getId())).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(NotFoundException.class, () ->{
            commandeServiceImplementation.getCommande(commande.getId());
        });
        assertEquals("Cette commande n'existe pas", exception.getMessage());
        log.info(exception.getMessage());

        verify(commandeRepository, times(1)).findById(commande.getId());

    }
}





