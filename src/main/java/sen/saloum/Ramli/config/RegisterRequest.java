package sen.saloum.Ramli.config;

import sen.saloum.Ramli.enums.Role;

import java.time.OffsetDateTime;

public class RegisterRequest {
    private String email;
    private String password;
    private String nom;
    private String prenom;
    private OffsetDateTime dateInscription;

    public OffsetDateTime getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(OffsetDateTime dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
