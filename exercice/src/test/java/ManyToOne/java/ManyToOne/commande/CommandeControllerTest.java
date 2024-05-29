package ManyToOne.java.ManyToOne.commande;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

import ManyToOne.java.ManyToOne.controller.CommandeController;
import ManyToOne.java.ManyToOne.model.Commande;
import ManyToOne.java.ManyToOne.model.Produit;
import ManyToOne.java.ManyToOne.model.Utilisateur;
import ManyToOne.java.ManyToOne.exceptions.ErrorMessageService;
import ManyToOne.java.ManyToOne.service.CommandeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;


/**
 *
 * @author Diallo iliassou
 * @version 0.0.1
 * @since 0.0.1
 */

@Log4j2
@WebMvcTest(value = CommandeController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class CommandeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommandeService commandeService;

    /**
     * since @ControllerAdvice scans all controllers, so they all have a dependency to board controller
     * and here board controller is ErrorMessageController
     * and it has a dependency to ErrorMessageService so they will all have a dependency to ErrorMessageService
     */
    @MockBean
    private ErrorMessageService errorMessageService;


    @Autowired
    private ObjectMapper objectMapper;
    private Commande commande;
    private final String uri = "/Commande";

    private  ResponseEntity<Commande> commandeReturn;

    private String commandeInJSON;

    private Utilisateur utilisateur;

    List<Produit> produitList;

    List<Commande> commandeList;

    private Produit produit;

    private Commande commande1;


    private final int userId= 5;
    private final int produitId= 8;



    @BeforeEach
    void setup() {
        utilisateur = new Utilisateur();
        utilisateur.setId(7);
        utilisateur.setNom("Diallo");
        utilisateur.setEmail("abd2004@gmail.com");


        produit = new Produit();
        produit.setId(7);
        produit.setNom("Tomate");
        produit.setPrix(60);
        produit.setDescription("rouge");
        produit.setQuantiteEnStock(9);

        produitList = new ArrayList<>();
        produitList.add(produit);



        commande = new Commande();
        commande.setId(4);
        commande.setDateCommande("12-03-2000");
        commande.setUtilisateur(utilisateur);
        commande.setProduits(produitList);


        commande1 = new Commande();
        commande1.setId(2);
        commande1.setDateCommande("12-03-3333");
        commande1.setUtilisateur(utilisateur);
        commande1.setProduits(produitList);

        commandeList = new ArrayList<>();
        commandeList.add(commande);
        commandeList.add(commande1);


        try {
            commandeInJSON = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commande);
        } catch (JsonProcessingException e) {
            log.info(e.getMessage());
        }

        commandeReturn = ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(commande);
    }


    @DisplayName("Junit test for create commande and return commande")
    @Test
    public void testCreateCommande() throws Exception {

        ResponseEntity<Commande> test = ResponseEntity.status(HttpStatus.CREATED).body(commande);

        when(commandeService.createCommande(any(Commande.class),eq(userId),eq(produitId))).thenReturn(test);

        String uriUpdate = uri.concat("/user/{userId}/produit/{produitId}");


        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uriUpdate,userId,produitId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(commandeInJSON);

        ResultActions response = mockMvc.perform(requestBuilder);

        response

                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",
                        equalTo(commande.getId())
                ))
                .andExpect(jsonPath("$.dateCommande",
                        equalTo(commande.getDateCommande())
                ))
                .andExpect(jsonPath("$.produits[0].nom",
                        equalTo(commande.getProduits().get(0).getNom())
                ))

                .andExpect(jsonPath("$.utilisateur.id",
                        equalTo(commande.getUtilisateur().getId())
                ))
                .andDo(print());



    }

    @DisplayName("Junit test for get all commande and return commande")
    @Test
    public void testGetAllCommande() throws Exception {
        when(commandeService.getAllCommande()).thenReturn(ResponseEntity.ok(commandeList));


        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(commandeInJSON);

        ResultActions response = mockMvc.perform(requestBuilder);

        response

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[1].id",
                        equalTo(commandeList.get(1).getId())
                ))
                .andExpect(jsonPath("$.[0].dateCommande",
                        equalTo(commandeList.get(0).getDateCommande())
                ))
                .andExpect(jsonPath("$.[1].produits[0].nom",
                        equalTo(commandeList.get(1).getProduits().get(0).getNom())
                ))

                .andExpect(jsonPath("$.[0].utilisateur.id",
                        equalTo(commandeList.get(0).getUtilisateur().getId())
                ))
                .andDo(print());

    }


    @DisplayName("Junit test for get all commande by user id and return commande")
    @Test
    public void testGetAllCommandeByUtilisateurId() throws Exception {
        when(commandeService.getAllCommandeByUtilisateurId(utilisateur.getId())).thenReturn(ResponseEntity.ok(commandeList));

        String updatedUri = uri.concat("/{utilisateurId}");


        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(updatedUri,utilisateur.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(commandeInJSON);

        ResultActions response = mockMvc.perform(requestBuilder);

        response

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[1].id",
                        equalTo(commandeList.get(1).getId())
                ))
                .andExpect(jsonPath("$.[0].dateCommande",
                        equalTo(commandeList.get(0).getDateCommande())
                ))
                .andExpect(jsonPath("$.[1].produits[0].nom",
                        equalTo(commandeList.get(1).getProduits().get(0).getNom())
                ))

                .andExpect(jsonPath("$.[0].utilisateur.id",
                        equalTo(commandeList.get(0).getUtilisateur().getId())
                ))
                .andDo(print());



    }
    @DisplayName("Junit test for get all commande by  id and return commande")
    @Test
    public void testGetCommandeById() throws Exception {
        when(commandeService.getCommande(commande.getId())).thenReturn(ResponseEntity.ok(commande));

        String updatedUri = uri.concat("/com/{commandeId}");


        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(updatedUri,commande.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(commandeInJSON);

        ResultActions response = mockMvc.perform(requestBuilder);

        response

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",
                        equalTo(commande.getId())
                ))
                .andExpect(jsonPath("$.dateCommande",
                        equalTo(commande.getDateCommande())
                ))
                .andExpect(jsonPath("$.produits[0].nom",
                        equalTo(commande.getProduits().get(0).getNom())
                ))

                .andExpect(jsonPath("$.utilisateur.id",
                        equalTo(commande.getUtilisateur().getId())
                ))
                .andDo(print());



    }






}
