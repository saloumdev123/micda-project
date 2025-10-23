package sen.saloum.Ramli.dto.user;

import sen.saloum.Ramli.enums.Role;
import sen.saloum.Ramli.models.Utilisateur;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class UtilisateurDto {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Role role;
    private OffsetDateTime dateInscription;

    public UtilisateurDto(Long id, String nom, String prenom, String email, String password, Role role, OffsetDateTime dateInscription) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
        this.dateInscription = dateInscription;
    }

    public UtilisateurDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
