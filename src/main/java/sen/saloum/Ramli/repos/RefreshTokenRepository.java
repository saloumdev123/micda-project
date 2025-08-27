package sen.saloum.Ramli.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import sen.saloum.Ramli.models.Utilisateur;
import sen.saloum.Ramli.models.security.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUtilisateur(Utilisateur utilisateur);
}
