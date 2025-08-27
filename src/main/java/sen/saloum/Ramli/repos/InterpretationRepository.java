package sen.saloum.Ramli.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import sen.saloum.Ramli.enums.NomFigureBase;
import sen.saloum.Ramli.enums.TypeFigure;
import sen.saloum.Ramli.models.Interpretation;
import java.util.List;
import java.util.Optional;

@Component
public interface InterpretationRepository extends JpaRepository<Interpretation, Long> {
    List<Interpretation> findByRamli_Id(Long ramliId);

}
