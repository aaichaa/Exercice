package ManyToOne.java.ManyToOne.produit;


import ManyToOne.java.ManyToOne.controller.ProduitController;
import ManyToOne.java.ManyToOne.service.ProduitService;
import org.junit.jupiter.api.BeforeEach;



import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ManyToOne.java.ManyToOne.model.Produit;
import ManyToOne.java.ManyToOne.exceptions.ErrorMessageService;

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

import java.util.ArrayList;
import java.util.List;


/**
 * @author Diallo iliassou
 * @version 0.0.1
 * @since 0.0.1
 */

@Log4j2
@WebMvcTest(value = ProduitController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})

public class ProduitControllerTest {

    /**
     * since @ControllerAdvice scans all controllers, so they all have a dependency to board controller
     * and here board controller is ErrorMessageController
     * and it has a dependency to ErrorMessageService so they will all have a dependency to ErrorMessageService
     */




    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProduitService produitService;


    @MockBean
    private ErrorMessageService errorMessageService;

    private Produit produit;

    @Autowired
    private ObjectMapper objectMapper;
    private final String uri = "/Produits";

    private ResponseEntity<Produit> produitReturn;

    private String produitInJSON;

    private Produit produit1;

    List<Produit> produitList;




    @BeforeEach
    void setup() {
        produit1 = new Produit();
        produit1.setId(0);
        produit1.setNom("orange");
        produit1.setPrix(30);
        produit1.setDescription("Vert");
        produit1.setQuantiteEnStock(9);

        produit = new Produit();
        produit.setId(7);
        produit.setNom("Tomate");
        produit.setPrix(60);
        produit.setDescription("rouge");
        produit.setQuantiteEnStock(9);

        produitList = new ArrayList<>();
        produitList.add(produit);
        produitList.add(produit1);





        try {
            produitInJSON = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(produit);
        } catch (JsonProcessingException e) {
            log.info(e.getMessage());
        }

        produitReturn = ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(produit);
    }

    @DisplayName("Junit test for create Product and return produit")
    @Test
    public void testCreateProduit() throws Exception {

        ResponseEntity<Produit> test = ResponseEntity.status(HttpStatus.CREATED).body(produit);

        when(produitService.createProduit(any(Produit.class))).thenReturn(test);


        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(produitInJSON);

        ResultActions response = mockMvc.perform(requestBuilder);

        response

                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",
                        equalTo(produit.getId())
                ))
                .andExpect(jsonPath("$.nom",
                        equalTo(produit.getNom())
                ))
                .andExpect(jsonPath("$.prix",
                        equalTo(produit.getPrix())
                ))

                .andExpect(jsonPath("$.description",
                        equalTo(produit.getDescription())
                ))
                .andExpect(jsonPath("$.quantiteEnStock",
                        equalTo(produit.getQuantiteEnStock())
                ))
                .andDo(print());


    }
    @DisplayName("Junit test for get all Produit and return produit")
    @Test
    public void testGetAllProduit() throws Exception {

        when(produitService.getAllProduit()).thenReturn(ResponseEntity.ok(produitList));


        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(produitInJSON);

        ResultActions response = mockMvc.perform(requestBuilder);

        response

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[1].id",
                        equalTo(produitList.get(1).getId())
                ))
                .andExpect(jsonPath("$.[0].nom",
                        equalTo(produitList.get(0).getNom())
                ))
                .andExpect(jsonPath("$.[1].prix",
                        equalTo(produitList.get(1).getPrix())
                ))

                .andExpect(jsonPath("$.[0].description",
                        equalTo(produitList.get(0).getDescription())
                ))
                .andExpect(jsonPath("$.[1].quantiteEnStock",
                        equalTo(produitList.get(1).getQuantiteEnStock())
                ))
                .andDo(print());


    }









}
