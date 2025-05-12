package sen.saloum.Ramli.service;

import org.springframework.stereotype.Service;
import sen.saloum.Ramli.dto.figure.FigureLignesDto;
import sen.saloum.Ramli.dto.figure.FigureRamliDto;
import sen.saloum.Ramli.enums.TypeFigure;
import sen.saloum.Ramli.mapStruct.FigureLignesMapper;
import sen.saloum.Ramli.mapStruct.FigureRamliMapper;
import sen.saloum.Ramli.models.FigureLigne;
import sen.saloum.Ramli.models.FigureRamli;
import sen.saloum.Ramli.repos.FigureRamliRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FigureRamliService {


    private final FigureRamliRepository figureRamliRepository;
    private final FigureRamliMapper figureRamliMapper;
    private final FigureLignesMapper figureLignesMapper;

    public FigureRamliService(FigureRamliRepository figureRamliRepository, FigureRamliMapper figureRamliMapper, FigureLignesMapper figureLignesMapper) {
        this.figureRamliRepository = figureRamliRepository;
        this.figureRamliMapper = figureRamliMapper;
        this.figureLignesMapper = figureLignesMapper;
    }


    public List<FigureRamliDto> getAll() {
        return figureRamliRepository.findAll()
                .stream()
                .map(figureRamliMapper::toDto)
                .collect(Collectors.toList());
    }

    public void validerFigure(FigureRamli figure) {
        if (figure.getLignes().size() != 4) {
            throw new IllegalArgumentException("Une figure doit avoir exactement 4 lignes.");
        }
    }
    /**
     * Génère des figures Ramli à partir d'une liste de lignes de base.
     */
    public List<FigureRamli> genererFigures(List<FigureLignesDto> lignesDto) {
        // Création d'une seule figure Ramli pour ce lot de lignes
        FigureRamli figure = new FigureRamli();

        // Conversion des lignes DTO en entités et liaison à la figure
        List<FigureLigne> lignesEntity = lignesDto.stream()
                .map(figureLignesMapper::toEntity) // conversion DTO -> entité
                .peek(ligne -> ligne.setFigure(figure)) // association de chaque ligne à la figure
                .collect(Collectors.toList());

        // Détermination du type de la figure à partir des lignes
        TypeFigure typeFigure = determineTypeFigure(lignesDto.get(0));
        figure.setTypeFigure(typeFigure);
        figure.setNom(typeFigure.getLabel());
        figure.setDescription("Description pour " + typeFigure.getLabel());

        // Affectation des lignes à la figure
        figure.setLignes(lignesEntity);

        // Retour sous forme de liste (1 seule figure ici)
        return List.of(figure);
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
                return TypeFigure.QUEUE; // Par défaut si valeur inattendue
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
}
