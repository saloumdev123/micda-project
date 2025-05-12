package sen.saloum.Ramli.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import sen.saloum.Ramli.enums.TypeFigure;
import sen.saloum.Ramli.models.FigureRamli;
import sen.saloum.Ramli.models.Interpretation;

import java.util.List;

public interface InterpretationRepository extends JpaRepository<Interpretation, Long> {
    List<Interpretation> findByFigureId(Long figureId);
    // pour evite les doublons Figure+FigureType dans les interpretation
    boolean existsByFigureAndTypeFigure(FigureRamli figure, TypeFigure typeFigure);

}
