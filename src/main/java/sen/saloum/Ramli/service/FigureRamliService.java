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
import sen.saloum.Ramli.repos.FigureRamliRepository;
import sen.saloum.Ramli.repos.InterpretationRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FigureRamliService {
    private final Map<String, Map<String, String>> interpretationsMap = new HashMap<>();
    private final FigureRamliRepository figureRamliRepository;
    private final FigureRamliMapper figureRamliMapper;
    private final FigureLigneMapper figureLigneMapper;
    private final InterpretationRepository interpretationRepository;
    private final ImageConfig imageConfig;

    // Map qui contient toutes les interprétations chargées une fois
    private final Map<NomFigureBase, Map<TypeFigure, Interpretation>> interpretationMap = new HashMap<>();

    public FigureRamliService(
            FigureRamliRepository figureRamliRepository,
            FigureRamliMapper figureRamliMapper,
            FigureLigneMapper figureLigneMapper,
            InterpretationRepository interpretationRepository, ImageConfig imageConfig
    ) {
        this.figureRamliRepository = figureRamliRepository;
        this.figureRamliMapper = figureRamliMapper;
        this.figureLigneMapper = figureLigneMapper;
        this.interpretationRepository = interpretationRepository;
        this.imageConfig = imageConfig;
    }

    @PostConstruct
    public void initInterpretationMap() {
        chargerInterpretations();
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
    private void validerPointsLigne(FigureLigne ligne) {
        if (ligne == null) {
            throw new IllegalArgumentException("La ligne ne peut pas être nulle.");
        }
        int[] points = {ligne.getPoint1(), ligne.getPoint2(), ligne.getPoint3(), ligne.getPoint4()};
        for (int point : points) {
            if (point < 0 || point > 4) { // adapte les bornes selon le contexte exact
                throw new IllegalArgumentException("Les points doivent être entre 0 et 4.");
            }
        }
    }
    // Si tu veux un jour réactiver cette méthode :
    public void reloadInterpretationMap() {
        interpretationMap.clear();
        chargerInterpretations();
    }

    private void chargerInterpretations() {
        interpretationRepository.findAll().forEach(interp -> {
            NomFigureBase nomBase = interp.getNomFigureBase();
            TypeFigure type = interp.getTypeFigure();

            interpretationMap
                    .computeIfAbsent(nomBase, k -> new HashMap<>())
                    .put(type, interp);
        });
    }
//    public void reloadInterpretationMap() {
//        interpretationMap.clear();
//        interpretationRepository.findAll().forEach(interp -> {
//            NomFigureBase nomBase = interp.getNomFigureBase();
//            TypeFigure type = interp.getTypeFigure();
//
//            interpretationMap
//                    .computeIfAbsent(nomBase, k -> new HashMap<>())
//                    .put(type, interp);
//        });
//    }

    public void validerFigure(FigureRamli figure) {
        if (figure == null) {
            throw new IllegalArgumentException("La figure ne peut pas être nulle.");
        }
        if (figure.getLignes() == null || figure.getLignes().size() != 4) {
            throw new IllegalArgumentException("Une figure doit avoir exactement 4 lignes.");
        }
        for (FigureLigne ligne : figure.getLignes()) {
            validerPointsLigne(ligne);
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
        if (figureRamli == null) {
            throw new IllegalArgumentException("La figure ne peut pas être nulle.");
        }
        if (figureRamli.getTirage() == null) {
            throw new IllegalStateException("Tirage non défini pour la figure");
        }
        return figureRamliMapper.toDto(figureRamli);
    }

    public void enrichirFigure(FigureRamli figure) {
        validerFigure(figure);  // Valide la présence de 4 lignes

        List<FigureLigne> lignes = figure.getLignes();

        // Conversion temporaire en DTO pour utiliser les méthodes de détection
        List<FigureLignesDto> lignesDto = lignes.stream()
                .map(figureLigneMapper::toDto)
                .collect(Collectors.toList());

        NomFigureBase nomFigureBase = detectNomFigureBase(lignesDto);
        TypeFigure typeFigure = determineTypeFigure(lignesDto.get(0));

        figure.setNomFigureBase(nomFigureBase);
        figure.setNom(nomFigureBase.getLabel());
        figure.setTypeFigure(typeFigure);

        Interpretation interpretation = Optional.ofNullable(interpretationMap.get(nomFigureBase))
                .map(map -> map.get(typeFigure))
                .orElse(null);

        if (interpretation != null) {
            figure.setDescription(interpretation.getSignification());
        } else {
            figure.setDescription("Description pour " + nomFigureBase.getLabel());
        }

        String imageUrl = imageConfig.getBaseUrl() + nomFigureBase.name().toLowerCase() + ".png";
        figure.setImage(imageUrl);
    }
    @Transactional
    public List<FigureRamli> genererFigures(List<FigureLignesDto> lignesDto) {
        if (lignesDto == null || lignesDto.isEmpty()) {
            throw new IllegalArgumentException("La liste des lignes ne peut pas être vide.");
        }

        // Diviser en groupes de 4
        List<List<FigureLignesDto>> groupsOf4 = new ArrayList<>();
        for (int i = 0; i < lignesDto.size(); i += 4) {
            if (i + 4 <= lignesDto.size()) {
                groupsOf4.add(lignesDto.subList(i, i + 4));
            } else {
                throw new IllegalArgumentException("Le nombre de lignes n'est pas un multiple de 4.");
            }
        }

        // Grouper par NomFigureBase
        Map<NomFigureBase, List<List<FigureLignesDto>>> grouped = groupsOf4.stream()
                .collect(Collectors.groupingBy(this::detectNomFigureBase));

        List<FigureRamli> figures = new ArrayList<>();

        for (Map.Entry<NomFigureBase, List<List<FigureLignesDto>>> entry : grouped.entrySet()) {
            List<List<FigureLignesDto>> groupes = entry.getValue();

            for (List<FigureLignesDto> groupLignes : groupes) {
                FigureRamli figure = new FigureRamli();

                List<FigureLigne> lignesEntity = groupLignes.stream()
                        .map(figureLigneMapper::toEntity)
                        .peek(ligne -> ligne.setFigure(figure))
                        .collect(Collectors.toList());

                figure.setLignes(lignesEntity);

                enrichirFigure(figure);

                figures.add(figure); // Pas de save ici
            }
        }

        // Sauvegarde groupée à la fin
        return figureRamliRepository.saveAll(figures);
    }




    public List<FigureRamliDto> genererEtRetournerDto(List<FigureLignesDto> lignesDto) {
        List<FigureRamli> figures = genererFigures(lignesDto);

        for (FigureRamli figure : figures) {
            validerFigure(figure);
            figureRamliRepository.save(figure);
        }

        return figures.stream()
                .map(figureRamliMapper::toDto)
                .collect(Collectors.toList());
    }

    private static final Map<String, NomFigureBase> FIGURE_MAP = Map.ofEntries(
            Map.entry("1000", NomFigureBase.AMISSIO),
            Map.entry("1001", NomFigureBase.CARCER),
            Map.entry("0110", NomFigureBase.CONJUNCTIO),
            Map.entry("1101", NomFigureBase.FORTUNA_MAJOR),
            Map.entry("1011", NomFigureBase.FORTUNA_MINOR),
            Map.entry("0101", NomFigureBase.LAETITIA),
            Map.entry("0011", NomFigureBase.PUER),
            Map.entry("1111", NomFigureBase.POPULUS),
            Map.entry("1100", NomFigureBase.RUBEUS),
            Map.entry("1110", NomFigureBase.TRISTITIA),
            Map.entry("0001", NomFigureBase.VIA),
            Map.entry("0010", NomFigureBase.ACQUISITIO),
            Map.entry("0000", NomFigureBase.CAUDA_DRACONIS),
            Map.entry("0111", NomFigureBase.CAPUT_DRACONIS),
            Map.entry("0100", NomFigureBase.ALBUS)
    );

    private NomFigureBase detectNomFigureBase(boolean p1, boolean p2, boolean p3, boolean p4) {
        String key = "" + (p1 ? 1 : 0) + (p2 ? 1 : 0) + (p3 ? 1 : 0) + (p4 ? 1 : 0);
        return FIGURE_MAP.getOrDefault(key, NomFigureBase.VIA);
    }

    private NomFigureBase detectNomFigureBase(List<FigureLignesDto> lignesDto) {
        if (lignesDto == null || lignesDto.size() != 4) {
            throw new IllegalArgumentException("La liste des lignes doit contenir exactement 4 éléments.");
        }

        StringBuilder binaryKey = new StringBuilder();

        for (FigureLignesDto ligne : lignesDto) {
            if (ligne == null) {
                throw new IllegalArgumentException("Une des lignes est nulle.");
            }
            binaryKey.append(ligne.getValeur() == 1 ? "1" : "0");
        }

        NomFigureBase nomFigure = FIGURE_MAP.get(binaryKey.toString());

        if (nomFigure == null) {
            throw new IllegalStateException("Aucune figure trouvée pour la clé binaire: " + binaryKey);
        }

        return nomFigure;
    }



    private TypeFigure determineTypeFigure(FigureLignesDto ligne) {
        if (ligne == null) {
            throw new IllegalArgumentException("La ligne ne peut pas être nulle pour déterminer le type");
        }

        int somme = ligne.getPoint1() + ligne.getPoint2() + ligne.getPoint3() + ligne.getPoint4();

        return switch (somme) {
            case 0, 1 -> TypeFigure.TEMOIN_DROIT;
            case 2 -> TypeFigure.TEMOIN_GAUCHE;
            case 3 -> TypeFigure.JUGE;
            case 4 -> TypeFigure.TETE;
            default -> TypeFigure.QUEUE;
        };
    }

    public FigureRamliDto toDto(FigureRamli figure) {
        if (figure == null) {
            throw new IllegalArgumentException("La figure ne peut pas être nulle.");
        }
        return figureRamliMapper.toDto(figure);
    }
    @Transactional
    public void save(FigureRamli figure) {
        if (figure == null) {
            throw new IllegalArgumentException("La figure ne peut pas être nulle.");
        }
        if (figure.getNom() == null || figure.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de la figure est obligatoire.");
        }
        validerFigure(figure);
        figureRamliRepository.save(figure);
    }
    @Transactional
    public FigureRamli saveAndReturn(FigureRamli figure) {
        validerFigure(figure);
        return figureRamliRepository.save(figure);
    }

    public String getInterpretation(String nomFigureBase, String typeFigure) {
        if (interpretationsMap.containsKey(nomFigureBase)) {
            return interpretationsMap.get(nomFigureBase).get(typeFigure);
        }
        return null;
    }
}
