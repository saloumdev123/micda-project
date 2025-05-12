package sen.saloum.Ramli.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @Column(unique = true)
    private String username;
    private String password;
    private boolean enabled = true;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Tirage> tirages;

    public Utilisateur() {
    }

    public Utilisateur(Long id, String nom, String username, String password, boolean enabled, Set<Role> roles, List<Tirage> tirages) {
        this.id = id;
        this.nom = nom;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
        this.tirages = tirages;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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



    public List<Tirage> getTirages() {
        return tirages;
    }

    public void setTirages(List<Tirage> tirages) {
        this.tirages = tirages;
    }
}
