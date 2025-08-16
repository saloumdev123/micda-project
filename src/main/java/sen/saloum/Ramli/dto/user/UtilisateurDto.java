package sen.saloum.Ramli.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import sen.saloum.Ramli.enums.Role;

public class UtilisateurDto {
    private Long id;
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;
    @NotBlank(message = "Le username est obligatoire")
    @Size(min = 4, max = 20)
    private String username;
    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères")
    private String password;
    private boolean enabled = true;
    private Role role;
    private Long version;

    public Long getVersion() {
        return version;
    }




    public void setVersion(Long version) {
        this.version = version;
    }

    public UtilisateurDto() {
    }

    public UtilisateurDto(Long id, String nom, String username, String password,
                          boolean enabled, Role role,Long version) {
        this.id = id;
        this.nom = nom;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
        this.version= version;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
