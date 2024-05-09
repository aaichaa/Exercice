package ManyToOne.java.ManyToOne.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private int prix;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Integer quantiteEnStock;
   // @ManyToMany(mappedBy = "produits")
    //private List<Commande> commandes;

}
