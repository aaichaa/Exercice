package ManyToOne.java.ManyToOne.utlisateur;






import ManyToOne.java.ManyToOne.exceptions.httpexceptions.NotFoundException;
import ManyToOne.java.ManyToOne.model.Produit;
import ManyToOne.java.ManyToOne.model.Utilisateur;
import ManyToOne.java.ManyToOne.repository.UtilisateurRepository;

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


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Log4j2
public class UtilisateurExceptionTest {

    @InjectMocks
    UtilisateurServiceImpl utilisateurServiceImplementation;

    @Mock
    UtilisateurRepository utilisateurRepository;

    private Utilisateur utilisateur;

    private Utilisateur utilisateur2;

    private Utilisateur newUser;

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

        newUser = new Utilisateur();
        newUser.setNom("updatedname");
        newUser.setEmail("abd@gmail.commmmmmmmmmmmmm");



        utilisateurList = new ArrayList<>();
        utilisateurList.add(utilisateur);
        utilisateurList.add(utilisateur2);

    }



    @DisplayName("JUnit test for getUtilisateur")
    @Test
    void testgetUtilisateur_thenThrowNotFoundException() {
        when(utilisateurRepository.findById(utilisateur.getId())).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(NotFoundException.class, () ->{
            utilisateurServiceImplementation.getUtilisateur(utilisateur.getId());
        });
        assertEquals("cet utilisateur n'existe pas", exception.getMessage());
        log.info(exception.getMessage());

        verify(utilisateurRepository, times(1)).findById(utilisateur.getId());

    }



    @DisplayName("JUnit test for putUtilisateur")
    @Test
    void testputUtilisateur_thenThrowNotFoundException() {
        when(utilisateurRepository.findById(utilisateur.getId())).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(NotFoundException.class, () ->{
            utilisateurServiceImplementation.putUtilisateur(utilisateur.getId(), newUser);
        });
        assertEquals("cet utilisateur n'existe pas", exception.getMessage());
        log.info(exception.getMessage());

        verify(utilisateurRepository, times(1)).findById(utilisateur.getId());

    }




       @DisplayName("JUnit test for deleteProduct")
    @Test
    void testdeleteProduct_thenThrowNotFoundException() {
        when(utilisateurRepository.findById(utilisateur.getId())).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(NotFoundException.class, () ->{
            utilisateurServiceImplementation.deleteUtilisateur(utilisateur.getId());
        });
        assertEquals("cet utilisateur n'existe pas", exception.getMessage());
        log.info(exception.getMessage());

        verify(utilisateurRepository, times(1)).findById(utilisateur.getId());

    }

}
