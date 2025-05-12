package sen.saloum.Ramli.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import sen.saloum.Ramli.models.FigureLigne;
import sen.saloum.Ramli.models.FigureLigneId;

import java.util.Arrays;
import java.util.List;

public interface FigureLignesRepository extends JpaRepository<FigureLigne, FigureLigneId> {
    List<FigureLigne> findByFigureId(Long figureId);
}
