package sen.saloum.Ramli.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import sen.saloum.Ramli.enums.Role;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;


@Entity
public class Utilisateur {
    @Id @GeneratedValue
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private OffsetDateTime dateInscription;


    public Utilisateur() {
    }

    public Utilisateur(Long id,String password, String nom, String prenom, String email, Role role, OffsetDateTime dateInscription) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.role = role;
        this.dateInscription = dateInscription;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public OffsetDateTime getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(OffsetDateTime dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
