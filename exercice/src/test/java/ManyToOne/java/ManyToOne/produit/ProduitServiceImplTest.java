package ManyToOne.java.ManyToOne.produit;

import ManyToOne.java.ManyToOne.model.Produit;
import ManyToOne.java.ManyToOne.repository.CommandeRepository;
import ManyToOne.java.ManyToOne.repository.ProduitRepository;
import ManyToOne.java.ManyToOne.service.CommandeServiceImpl;
import ManyToOne.java.ManyToOne.service.ProduitService;
import ManyToOne.java.ManyToOne.service.ProduitServiceImpl;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Log4j2
public class ProduitServiceImplTest {


    @InjectMocks
    ProduitServiceImpl produitServiceImplementation;

    @Mock
    ProduitRepository produitRepository;


    private Produit produit;
    private Produit produit1;

    private Produit produit4;

    List<Produit> produitList;





    @BeforeEach
    void setup() {
        produit = new Produit();
        produit.setId(7);
        produit.setNom("Tomate");
        produit.setPrix(60);
        produit.setDescription("rouge");
        produit.setQuantiteEnStock(9);


        produit1= new Produit();
        produit1.setId(9);
        produit1.setNom("Citron");
        produit1.setPrix(30);
        produit1.setDescription("jaune");
        produit1.setQuantiteEnStock(10);


        produit4= new Produit();
        produit4.setId(10);
        produit4.setNom("Orange");
        produit4.setPrix(30);
        produit4.setDescription("orange");
        produit4.setQuantiteEnStock(70);



        produitList = new ArrayList<>();
        produitList.add(produit);
        produitList.add(produit);
        produitList.add(produit4);

    }




    @DisplayName("Junit test for create Produit and return produit")
    @Test
    void testCreatePRoduit_thenReturnProduit() {
        when(produitRepository.save(produit)).thenReturn(produit);
        ResponseEntity<Produit> createProduit = produitServiceImplementation.createProduit(produit);
        assertThat(createProduit).isNotNull();
        assertThat(createProduit.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        log.info(createProduit.getBody());
        assertThat(createProduit.getBody())
                .isNotNull()
                .hasFieldOrPropertyWithValue("id",7)
                .hasFieldOrPropertyWithValue("description", produit.getDescription());

        verify(produitRepository, times(1)).save(produit);


    }

    @DisplayName("Junit test for get list Produit and return list produit")
    @Test
    void testGetAllProduct_thenReturnAllProduct(){

        when(produitRepository.findAll()).thenReturn(produitList);
        ResponseEntity<List<Produit>> getAllProduit = produitServiceImplementation.getAllProduit();

        log.info(getAllProduit.getBody());
        assertEquals("Tomate", getAllProduit.getBody().get(0).getNom());
        assertEquals(3,getAllProduit.getBody().size());
        verify(produitRepository, times(1)).findAll();

    }








}
