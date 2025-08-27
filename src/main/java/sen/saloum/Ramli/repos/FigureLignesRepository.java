package sen.saloum.Ramli.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import sen.saloum.Ramli.models.FigureLigne;

import java.util.List;

@Component
public interface FigureLignesRepository extends JpaRepository<FigureLigne, Long> {
    List<FigureLigne> findByFigureId(Long figureId);
}
