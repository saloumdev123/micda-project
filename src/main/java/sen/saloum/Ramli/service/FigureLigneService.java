package sen.saloum.Ramli.service;

import org.springframework.stereotype.Service;
import sen.saloum.Ramli.dto.figure.FigureLignesDto;
import sen.saloum.Ramli.mapStruct.FigureLigneMapper;
import sen.saloum.Ramli.models.FigureLigne;
import sen.saloum.Ramli.models.FigureRamli;
import sen.saloum.Ramli.repos.FigureLignesRepository;
import sen.saloum.Ramli.repos.FigureRamliRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FigureLigneService {
    private final FigureLignesRepository figureLigneRepository;
    private final FigureRamliRepository figureRamliRepository;
    private final FigureLigneMapper figureLigneMapper;

    public FigureLigneService(FigureLignesRepository repo, FigureRamliRepository figureRepo, FigureLigneMapper mapper) {
        this.figureLigneRepository = repo;
        this.figureRamliRepository = figureRepo;
        this.figureLigneMapper = mapper;
    }

    // Créer une ligne
    public FigureLignesDto create(FigureLignesDto dto, Long figureId) {
        FigureRamli figure = figureRamliRepository.findById(figureId)
                .orElseThrow(() -> new RuntimeException("FigureRamli not found"));

        FigureLigne entity = figureLigneMapper.toEntity(dto);
        entity.setFigure(figure); // nécessaire pour @ManyToOne

        // Initialiser les points
        initPoints(entity);

        return figureLigneMapper.toDto(figureLigneRepository.save(entity));
    }

    // Méthode pour transformer les valeurs en points
    private void initPoints(FigureLigne entity) {
        String[] points = entity.getValeurs().split(" "); // suppose séparés par un espace
        if (points.length >= 4) {
            entity.setPoint1(parsePoint(points[0]));
            entity.setPoint2(parsePoint(points[1]));
            entity.setPoint3(parsePoint(points[2]));
            entity.setPoint4(parsePoint(points[3]));
        }
    }

    // Conversion • → 1, ○ → 0
    private int parsePoint(String val) {
        return "•".equals(val) ? 1 : 0;
    }



    // Générer des lignes depuis un tirage
    public List<FigureLignesDto> genererLignesDepuisTirage(List<Integer> tirage, Long figureId) {
        if (tirage == null || tirage.size() % 4 != 0) {
            throw new IllegalArgumentException("Le tirage doit contenir un multiple de 4 points.");
        }

        FigureRamli figure = figureRamliRepository.findById(figureId)
                .orElseThrow(() -> new RuntimeException("FigureRamli not found"));

        List<FigureLigne> lignes = new ArrayList<>();
        for (int i = 0; i < tirage.size(); i += 4) {
            FigureLignesDto dto = new FigureLignesDto();
            dto.setPosition(i / 4 + 1);
            dto.setValeurs(tirage.get(i) + " " + tirage.get(i + 1) + " " + tirage.get(i + 2) + " " + tirage.get(i + 3));
            dto.setFigureId(figureId);

            FigureLigne entity = figureLigneMapper.toEntity(dto);
            entity.setFigure(figure);
            entity.setLigneIndex(entity.getPosition()); // <-- Ajouter ici
            lignes.add(entity);
        }

        figureLigneRepository.saveAll(lignes);

        return lignes.stream().map(figureLigneMapper::toDto).collect(Collectors.toList());
    }

    // Récupérer les lignes d'une figure
    public List<FigureLignesDto> getByFigureId(Long figureId) {
        return figureLigneRepository.findByFigureId(figureId)
                .stream()
                .map(figureLigneMapper::toDto)
                .collect(Collectors.toList());
    }

    // Mettre à jour une ligne
    public FigureLignesDto update(Long id, FigureLignesDto dto) {
        FigureLigne existing = figureLigneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FigureLigne not found"));

        existing.setValeurs(dto.getValeurs());
        existing.setPosition(dto.getPosition());

        return figureLigneMapper.toDto(figureLigneRepository.save(existing));
    }

    // Supprimer une ligne
    public void delete(Long id) {
        figureLigneRepository.deleteById(id);
    }




}
