package sen.saloum.Ramli.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sen.saloum.Ramli.config.ImageConfig;
import sen.saloum.Ramli.dto.figure.FigureLignesDto;
import sen.saloum.Ramli.dto.figure.FigureRamliDto;
import sen.saloum.Ramli.enums.NomFigureBase;
import sen.saloum.Ramli.enums.TypeFigure;
import sen.saloum.Ramli.mapStruct.FigureLigneMapper;
import sen.saloum.Ramli.mapStruct.FigureRamliMapper;
import sen.saloum.Ramli.models.FigureLigne;
import sen.saloum.Ramli.models.FigureRamli;
import sen.saloum.Ramli.models.Interpretation;
import sen.saloum.Ramli.models.Tirage;
import sen.saloum.Ramli.repos.FigureRamliRepository;
import sen.saloum.Ramli.repos.InterpretationRepository;
import sen.saloum.Ramli.repos.TirageRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FigureRamliService {
    private final FigureRamliRepository ramliRepository;
    private final FigureRamliMapper ramliMapper;

    public FigureRamliService(FigureRamliRepository ramliRepository, FigureRamliMapper ramliMapper) {
        this.ramliRepository = ramliRepository;
        this.ramliMapper = ramliMapper;
    }

    public FigureRamliDto create(FigureRamliDto dto) {
        FigureRamli entity = ramliMapper.toEntity(dto);

        if (entity.getLignes() != null) {
            entity.getLignes().forEach(ligne -> {
                ligne.setFigure(entity);                 // @ManyToOne
                ligne.setLigneIndex(ligne.getPosition()); // ligne_index
                if (ligne.getValeurs() == null) {        // valeurs par défaut -> binaire
                    ligne.setValeurs("0 0 0 0");
                }
                initPoints(ligne); // point1..point4
            });
        }

        return ramliMapper.toDto(ramliRepository.save(entity));
    }

    public List<FigureRamliDto> findAll() {
        return ramliRepository.findAll()
                .stream()
                .map(ramliMapper::toDto)
                .collect(Collectors.toList());
    }

    public FigureRamliDto findById(Long id) {
        return ramliRepository.findById(id)
                .map(ramliMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Ramli not found"));
    }

    public FigureRamliDto update(Long id, FigureRamliDto dto) {
        FigureRamli existing = ramliRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FigureRamli not found"));

        existing.setNomFigure(dto.getNomFigure());
        existing.setDescription(dto.getDescription());
        existing.setSymbolisme(dto.getSymbolisme());

        if (dto.getLignes() != null) {
            List<FigureLigne> lignes = dto.getLignes().stream()
                    .map(ligneDto -> {
                        FigureLigne ligne = new FigureLigne();
                        ligne.setId(ligneDto.getId());
                        ligne.setValeurs(ligneDto.getValeurs());
                        ligne.setPosition(ligneDto.getPosition());
                        ligne.setFigure(existing);
                        ligne.setLigneIndex(ligne.getPosition());
                        initPoints(ligne);
                        return ligne;
                    }).collect(Collectors.toList());
            existing.setLignes(lignes);
        }

        return ramliMapper.toDto(ramliRepository.save(existing));
    }

    public void delete(Long id) {
        ramliRepository.deleteById(id);
    }

    public FigureRamliDto getRandom() {
        List<FigureRamli> figures = ramliRepository.findAllWithLignes();
        if (figures.isEmpty()) {
            throw new RuntimeException("Aucune figure disponible");
        }
        Random rand = new Random();
        FigureRamli randomFigure = figures.get(rand.nextInt(figures.size()));
        return ramliMapper.toDto(randomFigure);
    }

    // ⚡ Maintenant on parse directement les 0/1
    private void initPoints(FigureLigne ligne) {
        if (ligne.getValeurs() != null) {
            String[] points = ligne.getValeurs().split(" ");
            ligne.setPoint1(points.length > 0 ? Integer.parseInt(points[0]) : 0);
            ligne.setPoint2(points.length > 1 ? Integer.parseInt(points[1]) : 0);
            ligne.setPoint3(points.length > 2 ? Integer.parseInt(points[2]) : 0);
            ligne.setPoint4(points.length > 3 ? Integer.parseInt(points[3]) : 0);
        } else {
            ligne.setPoint1(0);
            ligne.setPoint2(0);
            ligne.setPoint3(0);
            ligne.setPoint4(0);
        }
    }
}
