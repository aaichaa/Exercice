package ManyToOne.java.ManyToOne.utlisateur;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




import ManyToOne.java.ManyToOne.controller.UtilisateurController;

import ManyToOne.java.ManyToOne.model.Utilisateur;
import ManyToOne.java.ManyToOne.exceptions.ErrorMessageService;

import ManyToOne.java.ManyToOne.service.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.BeanUtils;
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
 *
 * @author Diallo iliassou
 * @version 0.0.1
 * @since 0.0.1
 */

@Log4j2
@WebMvcTest(value = UtilisateurController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class UtilisateurControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UtilisateurService utilisateurService;

    /**
     * since @ControllerAdvice scans all controllers, so they all have a dependency to board controller
     * and here board controller is ErrorMessageController
     * and it has a dependency to ErrorMessageService so they will all have a dependency to ErrorMessageService
     */
    @MockBean
    private ErrorMessageService errorMessageService;


    @Autowired
    private ObjectMapper objectMapper;

    private final String uri = "/Users";

    private  ResponseEntity<Utilisateur> utilisateurReturn;

    private String utilisateurInJSON;

    private Utilisateur utilisateur;

    private Utilisateur utilisateur1;

    List<Utilisateur> utilisateurList;

    private Utilisateur newUser;







    @BeforeEach
    void setup() {
        utilisateur = new Utilisateur();
        utilisateur.setId(7);
        utilisateur.setNom("Diallo");
        utilisateur.setEmail("abd2004@gmail.com");

        utilisateur1 = new Utilisateur();
        utilisateur1.setId(7);
        utilisateur1.setNom("Diallo");
        utilisateur1.setEmail("abd2004@gmail.com");

        utilisateurList = new ArrayList<>();
        utilisateurList.add(utilisateur);
        utilisateurList.add(utilisateur1);

        newUser = new Utilisateur();
        newUser.setNom("updatedname");
        newUser.setEmail("abd@gmail.commmmmmmmmmmmmm");



        try {
            utilisateurInJSON = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(utilisateur);
        } catch (JsonProcessingException e) {
            log.info(e.getMessage());
        }

       utilisateurReturn = ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(utilisateur);

    }



    @DisplayName("Junit test for create utilisateur and return utilisateur")
    @Test
    public void testCreateUtilisateur() throws Exception {


        when(utilisateurService.createUtilisateur(any(Utilisateur.class)))
                .thenReturn( ResponseEntity.status(HttpStatus.CREATED).body(utilisateur));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(utilisateurInJSON);


        ResultActions response = mockMvc.perform(requestBuilder);

        response

                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",
                        equalTo(utilisateur.getId())
                ))
                .andExpect(jsonPath("$.nom",
                        equalTo(utilisateur.getNom())
                ))
                .andExpect(jsonPath("$.email",
                        equalTo(utilisateur.getEmail())

                ))
                .andDo(print());

    }

    @DisplayName("Junit test for get all utilisateur and return utilisateur")
    @Test
    public void testGetAllUtilisateur() throws Exception {
        when(utilisateurService.getAllUtilisateur()).thenReturn(ResponseEntity.ok(utilisateurList));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(utilisateurInJSON);


        ResultActions response = mockMvc.perform(requestBuilder);

        response

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[1].id",
                        equalTo(utilisateurList.get(1).getId())
                ))
                .andExpect(jsonPath("$.[1].nom",
                        equalTo(utilisateurList.get(1).getNom())
                ))
                .andExpect(jsonPath("$.[0].email",
                        equalTo(utilisateurList.get(0).getEmail())

                ))
                .andDo(print());

    }
    @DisplayName("Junit test for get  utilisateur by id and return utilisateur")
    @Test
    public void testGetUtilisateur() throws Exception {

        when(utilisateurService.getUtilisateur(utilisateur.getId())).thenReturn(ResponseEntity.ok(utilisateur));

       //         String uriUpdate = uri.concat("/{id}");
                String updateduri =uri + "/{utilisateurId}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(updateduri, utilisateur.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(utilisateurInJSON);


        ResultActions response = mockMvc.perform(requestBuilder);

        response

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",
                        equalTo(utilisateur.getId())
                ))
                .andExpect(jsonPath("$.nom",
                        equalTo(utilisateur.getNom())
                ))
                .andExpect(jsonPath("$.email",
                        equalTo(utilisateur.getEmail())

                ))
                .andDo(print());

    }


    @DisplayName("Junit test for update utilisateur by id ")
    @Test
    public void testUpdateUtilisateur() throws Exception {

        utilisateur.setNom(newUser.getNom());
        utilisateur.setEmail(newUser.getEmail());


        when(utilisateurService.putUtilisateur(utilisateur.getId(), newUser)).thenReturn(ResponseEntity.ok(utilisateur));


        String updateduri =uri + "/{utilisateurId}";


        String userJson = objectMapper.writeValueAsString(newUser);


        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(updateduri, utilisateur.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson);


        ResultActions response = mockMvc.perform(requestBuilder);

        response

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",
                        equalTo(utilisateur.getId())
                ))
                .andExpect(jsonPath("$.nom",
                        equalTo(utilisateur.getNom())
                ))
                .andExpect(jsonPath("$.email",
                        equalTo(utilisateur.getEmail())

                ))
                .andDo(print());




    }


    @DisplayName("Junit test for update2 utilisateur by id ")
    @Test
    public void testUpdate2Utilisateur() throws Exception {

        BeanUtils.copyProperties(newUser, utilisateur, "id");


        when(utilisateurService.putUtilisateur(utilisateur.getId(), newUser)).thenReturn(ResponseEntity.ok(utilisateur));


        String updateduri =uri + "/{utilisateurId}";


        String userJson = objectMapper.writeValueAsString(newUser);


        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(updateduri, utilisateur.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson);


        ResultActions response = mockMvc.perform(requestBuilder);

        response

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",
                        equalTo(utilisateur.getId())
                ))
                .andExpect(jsonPath("$.nom",
                        equalTo(utilisateur.getNom())
                ))
                .andExpect(jsonPath("$.email",
                        equalTo(utilisateur.getEmail())

                ))
                .andDo(print());




    }

















    @DisplayName("Junit test for delete utilisateur by id ")
    @Test
    public void testDeleteUtilisateur() throws Exception {
        String text = "Utilisateur deleted successfully";

        when(utilisateurService.deleteUtilisateur(utilisateur.getId())).thenReturn(ResponseEntity.ok(text));

        String uriUpdate = uri.concat("/{id}");


        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(uriUpdate, utilisateur.getId());

        ResultActions response = mockMvc.perform(requestBuilder);
        response
                .andExpect(status().isOk())
                .andExpect(content().string(text))
                .andDo(print());
    }

}
