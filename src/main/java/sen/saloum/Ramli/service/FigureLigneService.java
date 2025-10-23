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

    // Initialisation des points à partir des valeurs binaires
    private void initPoints(FigureLigne entity) {
        String[] points = entity.getValeurs().split(" "); // suppose séparés par un espace
        if (points.length >= 4) {
            entity.setPoint1(Integer.parseInt(points[0]));
            entity.setPoint2(Integer.parseInt(points[1]));
            entity.setPoint3(Integer.parseInt(points[2]));
            entity.setPoint4(Integer.parseInt(points[3]));
        }
    }


    /**
     * 🔹 Génère les 4 lignes d’une figure à partir d’une liste de 16 points binaires
     */
    public List<FigureLignesDto> genererLignesDepuisTirage(List<Integer> tirage, Long figureId) {
        if (tirage == null || tirage.size() < 16) {
            throw new IllegalArgumentException("Le tirage doit contenir au moins 16 valeurs binaires (0 ou 1).");
        }

        FigureRamli figure = figureRamliRepository.findById(figureId)
                .orElseThrow(() -> new RuntimeException("Figure non trouvée avec ID : " + figureId));

        List<FigureLignesDto> lignesDtos = new ArrayList<>();

        // Générer 4 lignes à partir des 16 valeurs
        for (int i = 0; i < 16; i += 4) {
            FigureLignesDto dto = new FigureLignesDto();

            // Concatène les 4 valeurs séparées par un espace
            dto.setValeurs(tirage.get(i) + " " + tirage.get(i + 1) + " " + tirage.get(i + 2) + " " + tirage.get(i + 3));
            dto.setPosition(i / 4 + 1);
            dto.setLigneIndex(i / 4);
            dto.setFigureId(figure.getId());

            // Affichage console pour debug
            System.out.println("Valeurs générées (ligne " + dto.getPosition() + ") : " + dto.getValeurs());

            // Sauvegarde en base
            FigureLigne ligne = new FigureLigne();
            ligne.setValeurs(dto.getValeurs());
            ligne.setPosition(dto.getPosition());
            ligne.setLigneIndex(dto.getLigneIndex());
            ligne.setFigure(figure);
            figureLigneRepository.save(ligne);

            lignesDtos.add(dto);
        }

        return lignesDtos;
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
