package ManyToOne.java.ManyToOne.controller;

import ManyToOne.java.ManyToOne.model.Produit;
import ManyToOne.java.ManyToOne.model.Utilisateur;
import ManyToOne.java.ManyToOne.service.ProduitService;
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
@Tag(name = "Product", description = "All resources referencing a product controller.")
@RequestMapping("/Produits")
public class ProduitController {
    @Autowired
    public ProduitService produitService;

    @Operation(summary = "Create a new product", responses = {
            @ApiResponse(responseCode = "201", description = "Product is created successfully.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Produit.class)) }),
            @ApiResponse(responseCode = "400", description = "Missing Request Header", content = @Content),
            @ApiResponse(responseCode = "401", description = "You don't have the authorization to access this resource", content = @Content),
            @ApiResponse(responseCode = "403", description = "You don't have accreditation to access this resource", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product project not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping()
    public ResponseEntity<Produit> createProduit(@RequestBody Produit produit) {
        return produitService.createProduit(produit);
    }

    @Operation(summary = "Get all products", responses = {
            @ApiResponse(responseCode = "200", description = "Product successfully retrieved.", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "Missing Request Header", content = @Content),
            @ApiResponse(responseCode = "401", description = "You don't have the authorization to access this resource", content = @Content),
            @ApiResponse(responseCode = "403", description = "You don't have accreditation to access this resource", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<Produit>> getAllProduit() {
            return produitService.getAllProduit();
        }
}
