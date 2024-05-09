package ManyToOne.java.ManyToOne.controller;

import ManyToOne.java.ManyToOne.model.Utilisateur;
import ManyToOne.java.ManyToOne.service.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "User", description = "All resources referencing a user controller.")
@RequestMapping("/Users")
public class UtilisateurController {
    @Autowired
    public UtilisateurService utilisateurService;


    @Operation(summary = "Create a new user", responses = {
            @ApiResponse(responseCode = "201", description = "SortSerie is created successfully.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Utilisateur.class)) }),
            @ApiResponse(responseCode = "400", description = "Missing Request Header", content = @Content),
            @ApiResponse(responseCode = "401", description = "You don't have the authorization to access this resource", content = @Content),
            @ApiResponse(responseCode = "403", description = "You don't have accreditation to access this resource", content = @Content),
            @ApiResponse(responseCode = "404", description = "Patient project not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping()
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.createUtilisateur(utilisateur);
    }
    @Operation(summary = "Get all users", responses = {
            @ApiResponse(responseCode = "200", description = "User successfully retrieved.", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "Missing Request Header", content = @Content),
            @ApiResponse(responseCode = "401", description = "You don't have the authorization to access this resource", content = @Content),
            @ApiResponse(responseCode = "403", description = "You don't have accreditation to access this resource", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Utilisateur>> getAllUtilisateur() {
        return utilisateurService.getAllUtilisateur();
    }

    @Operation(summary = "Get user by id", responses = {
            @ApiResponse(responseCode = "200", description = "SortSerie is Updated successfully.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Utilisateur.class)) }),
            @ApiResponse(responseCode = "400", description = "Missing Request Header", content = @Content),
            @ApiResponse(responseCode = "401", description = "You don't have the authorization to access this resource", content = @Content),
            @ApiResponse(responseCode = "403", description = "You don't have accreditation to access this resource", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{utilisateurId}")
    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable int utilisateurId) {
        return utilisateurService.getUtilisateur(utilisateurId);
    }

    @Operation(summary = "Put user by id", responses = {
            @ApiResponse(responseCode = "200", description = "SortSerie is Updated successfully.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Utilisateur.class)) }),
            @ApiResponse(responseCode = "400", description = "Missing Request Header", content = @Content),
            @ApiResponse(responseCode = "401", description = "You don't have the authorization to access this resource", content = @Content),
            @ApiResponse(responseCode = "403", description = "You don't have accreditation to access this resource", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })

    @PutMapping("/{utilisateurId}")
    public ResponseEntity<Utilisateur> putUtilisateur(@PathVariable int utilisateurId,@RequestBody Utilisateur newUtilisateur) {
        return utilisateurService.putUtilisateur(utilisateurId, newUtilisateur);
    }

    @Operation(summary = "Delete user by id", responses = {
            @ApiResponse(responseCode = "200", description = "SortSerie is Updated successfully.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Utilisateur.class)) }),
            @ApiResponse(responseCode = "400", description = "Missing Request Header", content = @Content),
            @ApiResponse(responseCode = "401", description = "You don't have the authorization to access this resource", content = @Content),
            @ApiResponse(responseCode = "403", description = "You don't have accreditation to access this resource", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })

    @DeleteMapping("/{utilisateurId}")

    public ResponseEntity<String> deleteUtilisateur(@PathVariable int utilisateurId) {
        return utilisateurService.deleteUtilisateur(utilisateurId);
    }




    }
