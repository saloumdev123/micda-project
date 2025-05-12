package sen.saloum.Ramli.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import sen.saloum.Ramli.models.Utilisateur;

import java.util.Optional;


public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByUsername(String username);
}
