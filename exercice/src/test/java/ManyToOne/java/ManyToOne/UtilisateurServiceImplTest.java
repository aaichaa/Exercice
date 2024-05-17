package ManyToOne.java.ManyToOne;

import ManyToOne.java.ManyToOne.model.Produit;
import ManyToOne.java.ManyToOne.model.Utilisateur;
import ManyToOne.java.ManyToOne.repository.ProduitRepository;
import ManyToOne.java.ManyToOne.repository.UtilisateurRepository;
import ManyToOne.java.ManyToOne.service.ProduitServiceImpl;
import ManyToOne.java.ManyToOne.service.UtilisateurService;
import ManyToOne.java.ManyToOne.service.UtilisateurServiceImpl;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Log4j2
public class UtilisateurServiceImplTest {

    @InjectMocks
    UtilisateurServiceImpl utilisateurServiceImplementation;

    @Mock
    UtilisateurRepository utilisateurRepository;

    private Utilisateur utilisateur;

    private Utilisateur utilisateur2;

    List<Utilisateur> utilisateurList;


    @BeforeEach
    void setup() {
        utilisateur = new Utilisateur();
        utilisateur.setId(7);
        utilisateur.setNom("Diallo");
        utilisateur.setEmail("abd2004@gmail.com");


        utilisateur2 = new Utilisateur();
        utilisateur2.setId(2);
        utilisateur2.setNom("Balde");
        utilisateur2.setEmail("abd@gmail.com");



        utilisateurList = new ArrayList<>();
        utilisateurList.add(utilisateur);
        utilisateurList.add(utilisateur2);

    }

    @DisplayName("Junit test for create User and return user")
    @Test
    void testCreateUtilisateur_thenReturnUtilisateur() {

        when(utilisateurRepository.save(utilisateur2)).thenReturn(utilisateur2);
        ResponseEntity<Utilisateur> createUtilisateur = utilisateurServiceImplementation.createUtilisateur(utilisateur2);

        assertThat(createUtilisateur).isNotNull();
        assertThat(createUtilisateur.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        log.info(createUtilisateur.getBody());
        assertThat(createUtilisateur.getBody())
                .isNotNull()
                .hasFieldOrPropertyWithValue("id",2)
                .hasFieldOrPropertyWithValue("Email", utilisateur2.getEmail());

        verify(utilisateurRepository, times(1)).save(utilisateur2);


    }

    @DisplayName("Junit test for get list utilisateur and return list utilisateur")
    @Test
    void testGetAllUtilisateur_thenReturnAllUtilisateur(){
        when(utilisateurRepository.findAll()).thenReturn(utilisateurList);
        ResponseEntity<List<Utilisateur>> getAllUtilisateur = utilisateurServiceImplementation.getAllUtilisateur();

        log.info(getAllUtilisateur.getBody());
        assertEquals("Diallo", getAllUtilisateur.getBody().get(0).getNom());
        assertEquals(2,getAllUtilisateur.getBody().size());
        verify(utilisateurRepository, times(1)).findAll();

    }

     @DisplayName("Junit test for get Utilisateur by id and return list utilisateur by id")
    @Test
      void testGetUtilisateurById_thenReturnUtilisateur(){

        when(utilisateurRepository.findById(utilisateur2.getId())).thenReturn(Optional.of(utilisateur2));

        ResponseEntity<Utilisateur> getUtilisateur = utilisateurServiceImplementation.getUtilisateur(utilisateur2.getId());
         log.info(getUtilisateur.getBody());
         assertThat(getUtilisateur).isNotNull();
         assertThat(getUtilisateur.getStatusCode()).isEqualTo(HttpStatus.OK);

    }












}
