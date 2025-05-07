package sen.saloum.Ramli.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import sen.saloum.Ramli.models.Tirage;

import java.util.List;

public interface TirageRepository extends JpaRepository<Tirage, Long> {
    List<Tirage> findByUtilisateurId(Long utilisateurId);
}
