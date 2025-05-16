package sen.saloum.Ramli.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import sen.saloum.Ramli.dto.figure.FigureLignesDto;
import sen.saloum.Ramli.dto.figure.FigureRamliDto;
import sen.saloum.Ramli.enums.NomFigureBase;
import sen.saloum.Ramli.enums.TypeFigure;
import sen.saloum.Ramli.mapStruct.FigureLigneMapper;
import sen.saloum.Ramli.mapStruct.FigureRamliMapper;
import sen.saloum.Ramli.models.FigureLigne;
import sen.saloum.Ramli.models.FigureRamli;
import sen.saloum.Ramli.models.Interpretation;
import sen.saloum.Ramli.repos.FigureRamliRepository;
import sen.saloum.Ramli.repos.InterpretationRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FigureRamliService {

    private final FigureRamliRepository figureRamliRepository;
    private final FigureRamliMapper figureRamliMapper;
    private final FigureLigneMapper figureLigneMapper;
    private final InterpretationRepository interpretationRepository;

    // Map qui contient toutes les interprétations chargées une fois
    private final Map<NomFigureBase, Map<TypeFigure, Interpretation>> interpretationMap = new HashMap<>();

    public FigureRamliService(
            FigureRamliRepository figureRamliRepository,
            FigureRamliMapper figureRamliMapper,
            FigureLigneMapper figureLigneMapper,
            InterpretationRepository interpretationRepository
    ) {
        this.figureRamliRepository = figureRamliRepository;
        this.figureRamliMapper = figureRamliMapper;
        this.figureLigneMapper = figureLigneMapper;
        this.interpretationRepository = interpretationRepository;
    }

    @PostConstruct
    public void initInterpretationMap() {
        interpretationRepository.findAll().forEach(interp -> {
            NomFigureBase nomBase = interp.getNomFigureBase();
            TypeFigure type = interp.getTypeFigure();

            interpretationMap
                    .computeIfAbsent(nomBase, k -> new HashMap<>())
                    .put(type, interp);
        });
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
        NomFigureBase nomFigureBase = detectNomFigureBase(lignesDto);

        figure.setNomFigureBase(nomFigureBase);
        figure.setNom(nomFigureBase.getLabel());
        figure.setTypeFigure(typeFigure);
        // 🔍 Ajout automatique de l'interprétation
        Interpretation interpretation = null;
        if (interpretationMap.containsKey(nomFigureBase)) {
            interpretation = interpretationMap.get(nomFigureBase).get(typeFigure);
        }

        if (interpretation != null) {
            figure.setDescription(interpretation.getSignification());
        } else {
            figure.setDescription("Description pour " + nomFigureBase.getLabel());
        }

        figure.setImage("http://localhost:8080/images/" + nomFigureBase.name().toLowerCase() + ".png");
        figure.setLignes(lignesEntity);

//        Integer maxOrdre = figureRamliRepository.findMaxOrdreByTirageId(figure.getTirage().getId());
//        figure.setOrdre((maxOrdre == null ? 0 : maxOrdre) + 1);

        FigureRamli savedFigure = figureRamliRepository.save(figure);
        return List.of(savedFigure);
    }

    public List<FigureRamliDto> genererEtRetournerDto(List<FigureLignesDto> lignesDto) {
        FigureRamli figure = new FigureRamli();
        return genererFigures(lignesDto, figure).stream()
                .map(figureRamliMapper::toDto)
                .collect(Collectors.toList());
    }

    private NomFigureBase detectNomFigureBase(List<FigureLignesDto> lignesDto) {
        int[] points = lignesDto.stream()
                .mapToInt(l -> l.getPoint1() + l.getPoint2() + l.getPoint3() + l.getPoint4())
                .toArray();
        if (points.length != 4) {
            throw new IllegalArgumentException("Il faut exactement 4 lignes pour détecter une figure");
        }

        int p1 = lignesDto.get(0).getPoint1();
        int p2 = lignesDto.get(1).getPoint1();
        int p3 = lignesDto.get(2).getPoint1();
        int p4 = lignesDto.get(3).getPoint1();

        if (p1 == 1 && p2 == 1 && p3 == 1 && p4 == 1) return NomFigureBase.ACQUISITIO;
        if (p1 == 0 && p2 == 0 && p3 == 0 && p4 == 0) return NomFigureBase.POPULUS;
        if (p1 == 1 && p2 == 1 && p3 == 1 && p4 == 0) return NomFigureBase.FORTUNA_MAJOR;
        if (p1 == 0 && p2 == 1 && p3 == 1 && p4 == 1) return NomFigureBase.FORTUNA_MINOR;
        if (p1 == 1 && p2 == 1 && p3 == 0 && p4 == 1) return NomFigureBase.LAETITIA;
        if (p1 == 1 && p2 == 0 && p3 == 1 && p4 == 1) return NomFigureBase.TRISTITIA;
        if (p1 == 0 && p2 == 1 && p3 == 1 && p4 == 0) return NomFigureBase.PUELLA;
        if (p1 == 1 && p2 == 0 && p3 == 0 && p4 == 1) return NomFigureBase.PUER;
        if (p1 == 0 && p2 == 1 && p3 == 0 && p4 == 1) return NomFigureBase.RUBEUS;
        if (p1 == 1 && p2 == 0 && p3 == 1 && p4 == 0) return NomFigureBase.ALBUS;
        if (p1 == 0 && p2 == 1 && p3 == 1 && p4 == 1) return NomFigureBase.CONJUNCTIO;
        if (p1 == 1 && p2 == 0 && p3 == 0 && p4 == 0) return NomFigureBase.AMISSIO;
        if (p1 == 1 && p2 == 1 && p3 == 0 && p4 == 0) return NomFigureBase.CAPUT_DRACONIS;
        if (p1 == 0 && p2 == 0 && p3 == 1 && p4 == 1) return NomFigureBase.CAUDA_DRACONIS;
        if (p1 == 0 && p2 == 0 && p3 == 1 && p4 == 0) return NomFigureBase.CARCER;
        if (p1 == 1 && p2 == 1 && p3 == 1 && p4 == 1) return NomFigureBase.VIA;

        return NomFigureBase.VIA; // Par défaut (ou lève une exception si tu préfères)
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

        validerFigure(figure);
        figureRamliRepository.save(figure);
    }

    public FigureRamli saveAndReturn(FigureRamli figure) {
        validerFigure(figure);
        return figureRamliRepository.save(figure);
    }
}
