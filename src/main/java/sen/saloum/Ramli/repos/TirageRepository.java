package sen.saloum.Ramli.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import sen.saloum.Ramli.models.Tirage;

import java.util.List;

@Component
public interface TirageRepository extends JpaRepository<Tirage, Long> {
    List<Tirage> findByUtilisateurId(Long utilisateurId);
}
