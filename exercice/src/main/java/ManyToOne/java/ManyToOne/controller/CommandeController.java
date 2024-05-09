package ManyToOne.java.ManyToOne.controller;

import ManyToOne.java.ManyToOne.model.Commande;
import ManyToOne.java.ManyToOne.model.Produit;
import ManyToOne.java.ManyToOne.model.Utilisateur;
import ManyToOne.java.ManyToOne.repository.CommandeRepository;
import ManyToOne.java.ManyToOne.service.CommandeService;
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
@Tag(name = "Command", description = "All resources referencing a command controller.")
@RequestMapping("/Commande")
public class CommandeController {
    @Autowired
    public CommandeService commandeService;

    @Operation(summary = "Create a new command", responses = {
            @ApiResponse(responseCode = "201", description = "Command is created successfully.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Commande.class)) }),
            @ApiResponse(responseCode = "400", description = "Missing Request Header", content = @Content),
            @ApiResponse(responseCode = "401", description = "You don't have the authorization to access this resource", content = @Content),
            @ApiResponse(responseCode = "403", description = "You don't have accreditation to access this resource", content = @Content),
            @ApiResponse(responseCode = "404", description = "Command project not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/user/{userId}/produit/{produitId}")
    public ResponseEntity<Commande> createCommande(@RequestBody Commande commande,@PathVariable int userId
            ,@PathVariable int produitId) {
        return commandeService.createCommande(commande, userId, produitId);
    }
    @Operation(summary = "Get all command by user id", responses = {
            @ApiResponse(responseCode = "200", description = "SortSerie is Updated successfully.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Commande.class)) }),
            @ApiResponse(responseCode = "400", description = "Missing Request Header", content = @Content),
            @ApiResponse(responseCode = "401", description = "You don't have the authorization to access this resource", content = @Content),
            @ApiResponse(responseCode = "403", description = "You don't have accreditation to access this resource", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{utilisateurId}")
    public ResponseEntity<List<Commande>> getAllCommandeByUtilisateurId(@PathVariable Integer utilisateurId) {
        return commandeService.getAllCommandeByUtilisateurId(utilisateurId);
    }

    @Operation(summary = "Get command by id", responses = {
            @ApiResponse(responseCode = "200", description = "SortSerie is Updated successfully.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Commande.class)) }),
            @ApiResponse(responseCode = "400", description = "Missing Request Header", content = @Content),
            @ApiResponse(responseCode = "401", description = "You don't have the authorization to access this resource", content = @Content),
            @ApiResponse(responseCode = "403", description = "You don't have accreditation to access this resource", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })

    @GetMapping("/com/{commandeId}")
    public ResponseEntity<Commande> getCommande(@PathVariable Integer commandeId) {
        return commandeService.getCommande(commandeId);
    }
    @Operation(summary = "Get all command", responses = {
            @ApiResponse(responseCode = "200", description = "Product successfully retrieved.", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "Missing Request Header", content = @Content),
            @ApiResponse(responseCode = "401", description = "You don't have the authorization to access this resource", content = @Content),
            @ApiResponse(responseCode = "403", description = "You don't have accreditation to access this resource", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Commande>> getAllCommande() {
        return commandeService.getAllCommande();
    }


    }




