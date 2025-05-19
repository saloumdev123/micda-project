package sen.saloum.Ramli.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import sen.saloum.Ramli.enums.TypeFigure;
import sen.saloum.Ramli.models.FigureRamli;
import sen.saloum.Ramli.models.Interpretation;

import java.util.Collection;
import java.util.List;

@Component
public interface InterpretationRepository extends JpaRepository<Interpretation, Long> {
    List<Interpretation> findByFigureId(Long figureId);

    List<Interpretation> findByTypeFigure(TypeFigure type);
}
