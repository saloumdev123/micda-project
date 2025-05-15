package sen.saloum.Ramli.service;

import org.springframework.stereotype.Service;
import sen.saloum.Ramli.dto.figure.FigureLignesDto;
import sen.saloum.Ramli.dto.figure.FigureRamliDto;
import sen.saloum.Ramli.enums.TypeFigure;
import sen.saloum.Ramli.mapStruct.FigureLigneMapper;
import sen.saloum.Ramli.mapStruct.FigureRamliMapper;
import sen.saloum.Ramli.models.FigureLigne;
import sen.saloum.Ramli.models.FigureRamli;
import sen.saloum.Ramli.repos.FigureRamliRepository;
import sen.saloum.Ramli.repos.InterpretationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FigureRamliService {

    private final FigureRamliRepository figureRamliRepository;
    private final FigureRamliMapper figureRamliMapper;
    private final FigureLigneMapper figureLigneMapper;
    private final InterpretationRepository interpretationRepository;


    public FigureRamliService(FigureRamliRepository figureRamliRepository, FigureRamliMapper figureRamliMapper, FigureLigneMapper figureLigneMapper, InterpretationRepository interpretationRepository) {
        this.figureRamliRepository = figureRamliRepository;
        this.figureRamliMapper = figureRamliMapper;
        this.figureLigneMapper = figureLigneMapper;
        this.interpretationRepository = interpretationRepository;
    }


    public List<FigureRamliDto> getAll() {
        return figureRamliRepository.findAllWithLignes()
                .stream()
                .map(figureRamliMapper::toDto)
                .collect(Collectors.toList());
    }


    public void validerFigure(FigureRamli figure) {
        if (figure.getLignes().size() != 4) {
            throw new IllegalArgumentException("Une figure doit avoir exactement 4 lignes.");
        }
    }
    public List<FigureRamliDto> findByTirageIdWithLinesAndInterpretations(Long tirageId) {
        List<FigureRamli> figures = figureRamliRepository.findByTirageIdWithLinesAndInterpretations(tirageId);

        if (figures.isEmpty()) {
            throw new IllegalArgumentException("Aucune figure trouvée pour le tirage ID: " + tirageId);
        }

        return figures.stream()
                .map(figureRamliMapper::toDto)
                .collect(Collectors.toList());
    }

    public FigureRamliDto getFigureRamliDto(FigureRamli figureRamli) {
        if (figureRamli.getTirage() == null) {
            throw new IllegalStateException("Tirage non défini pour la figure");
        }
        return figureRamliMapper.toDto(figureRamli);
    }
    public List<FigureRamli> genererFigures(List<FigureLignesDto> lignesDto, FigureRamli figure) {
        List<FigureLigne> lignesEntity = lignesDto.stream()
                .map(figureLigneMapper::toEntity)
                .peek(ligne -> ligne.setFigure(figure))
                .collect(Collectors.toList());

        TypeFigure typeFigure = determineTypeFigure(lignesDto.get(0));
        figure.setTypeFigure(typeFigure);
        figure.setNom(typeFigure.getLabel());
        figure.setDescription("Description pour " + typeFigure.getLabel());
        figure.setImage("https://example.com/images/figure3.png");
        figure.setLignes(lignesEntity);

        FigureRamli savedFigure = figureRamliRepository.save(figure);

        return List.of(savedFigure);
    }


    public List<FigureRamliDto> genererEtRetournerDto(List<FigureLignesDto> lignesDto) {
        FigureRamli figure = new FigureRamli();
        return genererFigures(lignesDto, figure).stream()
                .map(figureRamliMapper::toDto)
                .collect(Collectors.toList());
    }

    private TypeFigure determineTypeFigure(FigureLignesDto ligne) {
        int somme = ligne.getPoint1() + ligne.getPoint2() + ligne.getPoint3() + ligne.getPoint4();

        switch (somme) {
            case 0:
            case 1:
                return TypeFigure.TEMOIN_DROIT;
            case 2:
                return TypeFigure.TEMOIN_GAUCHE;
            case 3:
                return TypeFigure.JUGE;
            case 4:
                return TypeFigure.TETE;
            default:
                return TypeFigure.QUEUE;
        }
    }

    public FigureRamliDto toDto(FigureRamli figure) {
        return figureRamliMapper.toDto(figure);
    }

    public void save(FigureRamli figure) {
        if (figure == null) {
            throw new IllegalArgumentException("La figure ne peut pas être nulle.");
        }

        validerFigure(figure); // Vérifie que la figure contient bien 4 lignes
        figureRamliRepository.save(figure);
    }
    public FigureRamli saveAndReturn(FigureRamli figure) {
        validerFigure(figure); // si tu veux valider avant save
        return figureRamliRepository.save(figure);
    }

}
