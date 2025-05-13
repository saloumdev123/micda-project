package sen.saloum.Ramli.dto.user;

import sen.saloum.Ramli.enums.Role;

public class UtilisateurDto {
    private Long id;
    private String nom;
    private String username;
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
