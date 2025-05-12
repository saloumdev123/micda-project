package sen.saloum.Ramli.service;

import org.springframework.stereotype.Service;
import sen.saloum.Ramli.dto.figure.FigureLignesDto;
import sen.saloum.Ramli.dto.tirage.DonneesDeBaseDto;
import sen.saloum.Ramli.dto.figure.FigureRamliDto;
import sen.saloum.Ramli.dto.tirage.TirageDto;
import sen.saloum.Ramli.mapStruct.FigureLignesMapper;
import sen.saloum.Ramli.models.FigureLigne;
import sen.saloum.Ramli.models.FigureRamli;
import sen.saloum.Ramli.models.Utilisateur;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RamliService {

    private final TirageService tirageService;
    private final FigureLigneService figureLigneService;
    private final FigureRamliService figureRamliService;
    private final FigureLignesMapper figureLignesMapper;

    public RamliService(TirageService tirageService,
                        FigureLigneService figureLigneService,
                        FigureRamliService figureRamliService, FigureLignesMapper figureLignesMapper) {
        this.tirageService = tirageService;
        this.figureLigneService = figureLigneService;
        this.figureRamliService = figureRamliService;
        this.figureLignesMapper = figureLignesMapper;
    }

    public List<FigureRamliDto> effectuerTirageEtGenererFigures() {
        List<Integer> tirage = tirageService.genererTirage();

        // Créez et sauvegardez une nouvelle figure pour générer un ID
        FigureRamli figure = new FigureRamli();
        figureRamliService.save(figure);
        Long figureId = figure.getId();

        // Utilisez le bon appel de méthode
        List<FigureLignesDto> lignesDTO = figureLigneService.genererLignesDepuisTirage(tirage, figureId);

        List<FigureRamli> figures = figureRamliService.genererFigures(lignesDTO);

        return figures.stream()
                .map(figureRamliService::toDto)
                .collect(Collectors.toList());
    }

    public TirageDto realiserTirage(Utilisateur utilisateur, DonneesDeBaseDto lignesDeDepart) {
        // Étape 1 : Générer le tirage aléatoire
        List<Integer> tirage = tirageService.genererTirage();

// Étape 2 : Créer une figure vide pour obtenir un ID
        FigureRamli figure = new FigureRamli();
        figureRamliService.save(figure); // Génère l'ID

        Long figureId = figure.getId();

// Étape 3 : Générer les lignes liées à cette figure (DTOs)
        List<FigureLignesDto> lignesDto = figureLigneService.genererLignesDepuisTirage(tirage, figureId);

// Étape 4 : Mapper les DTOs vers des entités
        List<FigureLigne> lignesEntity = lignesDto.stream()
                .map(figureLignesMapper::toEntity)
                .collect(Collectors.toList());

// Étape 5 : Sauvegarder les lignes et mettre à jour la figure
        figureLigneService.saveAll(lignesEntity);
        figureRamliService.save(figure);

// Étape 6 : Créer et retourner le DTO du tirage
        TirageDto tirageDto = new TirageDto();
        tirageDto.setValeurs(tirage.stream().map(String::valueOf).collect(Collectors.joining()));
        tirageDto.setUtilisateur(utilisateur);

        return tirageDto;

    }
}
