package ManyToOne.java.ManyToOne.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false)
    String nom;
    @Column(nullable = false)
    String email;
}
