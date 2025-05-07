package sen.saloum.Ramli.dto;

import lombok.Data;

@Data
public class UtilisateurDto {
    private Long id;
    private String nom;
    private String email;
    private String motDePasse;
    private String role;
}
