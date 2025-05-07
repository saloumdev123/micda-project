package sen.saloum.Ramli.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import sen.saloum.Ramli.models.FigureRamli;

import java.util.List;

public interface FigureRamliRepository extends JpaRepository<FigureRamli, Long> {
    List<FigureRamli> findByTirageId(Long tirageId);
}
