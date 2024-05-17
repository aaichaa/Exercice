package ManyToOne.java.ManyToOne.commande;

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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Log4j2
public class CommandeServiceImplTest {


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
    @DisplayName("Junit test for create Commande and return commande")
    @Test
    void testCreateCommande_thenReturnCommande() {

        when(produitRepository.findById(produit.getId())).thenReturn(Optional.of(produit));
        when(utilisateurRepository.findById(utilisateur.getId())).thenReturn(Optional.of(utilisateur));
        when(commandeRepository.save(commande)).thenReturn(commande);

        ResponseEntity<Commande> createCommande = commandeServiceImplementation.createCommande(commande,utilisateur.getId(),produit.getId());

        assertThat(createCommande).isNotNull();
        assertThat(createCommande.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        log.info(createCommande.getBody());
        assertThat(createCommande.getBody())
                .isNotNull()
                .hasFieldOrPropertyWithValue("id",4)
                .hasFieldOrPropertyWithValue("dateCommande", commande.getDateCommande());

        verify(commandeRepository, times(1)).save(commande);


    }



    @DisplayName("Junit test for get Commande by utilisateur id and return list commande")
    @Test
    void testGetCommandeByUtilisateurId_thenReturnCommande(){

        when(utilisateurRepository.findById(utilisateur.getId())).thenReturn(Optional.of(utilisateur));
        when(commandeRepository.findAllCommandeByUtilisateurId(utilisateur.getId())).thenReturn(commandeList);


        ResponseEntity<List<Commande>> getAllByUtilisateurId = commandeServiceImplementation.getAllCommandeByUtilisateurId(utilisateur.getId());

        log.info(getAllByUtilisateurId.getBody());
        assertThat(getAllByUtilisateurId).isNotNull();
        assertThat(getAllByUtilisateurId.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @DisplayName("Junit test for get Commande by id and return list commande by id")
    @Test
    void testGetCommandeById_thenReturnCommande(){
        when(commandeRepository.findById(commande.getId())).thenReturn(Optional.of(commande));

        ResponseEntity<Commande> getCommande = commandeServiceImplementation.getCommande(commande.getId());
        log.info(getCommande.getBody());
        assertThat(getCommande).isNotNull();
        assertThat(getCommande.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @DisplayName("Junit test for get list Commande and return list commande")
    @Test
    void testGetAllCommande_thenReturnAllCommande(){

        when(commandeRepository.findAll()).thenReturn(commandeList);

        ResponseEntity<List<Commande>> getAllCommande = commandeServiceImplementation.getAllCommande();

        log.info(getAllCommande.getBody());
        assertThat(getAllCommande).isNotNull();
        assertThat(getAllCommande.getStatusCode()).isEqualTo(HttpStatus.OK);


    }









}
