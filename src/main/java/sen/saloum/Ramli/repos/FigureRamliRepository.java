package sen.saloum.Ramli.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import sen.saloum.Ramli.models.FigureRamli;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public interface FigureRamliRepository extends JpaRepository<FigureRamli, Long> {
    List<FigureRamli> findByTirageId(Long tirageId);

    @Query("SELECT DISTINCT f FROM FigureRamli f " +
            "LEFT JOIN FETCH f.lignes " +
            "LEFT JOIN FETCH f.interpretations " +
            "WHERE f.tirage.id = :tirageId")
    List<FigureRamli> findByTirageIdWithLinesAndInterpretations(@Param("tirageId") Long tirageId);

    @Query("SELECT f FROM FigureRamli f LEFT JOIN FETCH f.lignes")
    List<FigureRamli> findAllWithLignes();

    Optional<FigureRamli> findById(Long fingureId);
}
