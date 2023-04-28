package fr.polytech.simulatorspring.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "utilisateur")
public class User {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    @Column(name = "NomUtil")
    private String name;
    @Column(name = "MotPasse")
    private String password;
    @Column(name = "salt")
    private String salt;
    @Column(name = "role")
    private String role;
    @Column(name = "email")
    private String email;
    @Column(name = "surname")
    private String surname;
    @Column(name = "forename")
    private String forename;

}
