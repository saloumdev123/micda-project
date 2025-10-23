package sen.saloum.Ramli.service;

import org.springframework.stereotype.Service;
import sen.saloum.Ramli.dto.tirage.TirageDto;
import sen.saloum.Ramli.mapStruct.TirageMapper;
import sen.saloum.Ramli.models.FigureRamli;
import sen.saloum.Ramli.models.Interpretation;
import sen.saloum.Ramli.models.Tirage;
import sen.saloum.Ramli.models.Utilisateur;
import sen.saloum.Ramli.repos.FigureRamliRepository;
import sen.saloum.Ramli.repos.TirageRepository;
import sen.saloum.Ramli.repos.UtilisateurRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TirageService {
    private final FigureRamliRepository figureRepo;
    private final FigureLigneService ligneService;
    private final InterpretationService interpretationService;
    private final TirageRepository tirageRepo;
    private final UtilisateurRepository utilisateurRepository;
    private final TirageMapper tirageMapper;

    public TirageService(
            FigureRamliRepository figureRepo,
            FigureLigneService ligneService,
            InterpretationService interpretationService,
            TirageRepository tirageRepo,
            UtilisateurRepository utilisateurRepository,
            TirageMapper tirageMapper
    ) {
        this.figureRepo = figureRepo;
        this.ligneService = ligneService;
        this.interpretationService = interpretationService;
        this.tirageRepo = tirageRepo;
        this.utilisateurRepository = utilisateurRepository;
        this.tirageMapper = tirageMapper;
    }

    public TirageDto effectuerTirage(Long userId) {
        // 1️⃣ Générer les points aléatoires
        List<Integer> tirage = genererPoints(16);

        // 2️⃣ Créer et sauvegarder une figure
        FigureRamli figure = new FigureRamli();
        figure.setNomFigure("Al-Lahjah");
        figure.setDescription("Description auto...");
        figure = figureRepo.save(figure);

        // 3️⃣ Générer les lignes associées
        ligneService.genererLignesDepuisTirage(tirage, figure.getId());

        // 4️⃣ Recharger la figure complète
        figure = figureRepo.findById(figure.getId())
                .orElseThrow(() -> new RuntimeException("Figure non trouvée après création."));

        // 5️⃣ Créer une interprétation pour la figure
        Interpretation interpretation = interpretationService.genererPourFigure(figure);

        // 6️⃣ Récupérer l'utilisateur
        Utilisateur user = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec id " + userId));

        // 7️⃣ Créer le tirage et lier les entités
        Tirage tirageEntity = new Tirage();
        tirageEntity.setUtilisateur(user);
        tirageEntity.setDateTirage(LocalDateTime.now());
        tirageEntity.setFigures(List.of(figure));

        // ✅ Lier interprétation et tirage
        interpretation.setTirage(tirageEntity);
        tirageEntity.setInterpretations(List.of(interpretation));

        // 8️⃣ Sauvegarder et retourner
        tirageRepo.save(tirageEntity);

        return tirageMapper.toDto(tirageEntity);
    }

    private List<Integer> genererPoints(int nb) {
        Random rand = new Random();
        List<Integer> points = new ArrayList<>();
        for (int i = 0; i < nb; i++) {
            points.add(rand.nextBoolean() ? 1 : 0);
        }
        return points;
    }

    public List<TirageDto> getTiragesByUser(Long userId) {
        List<Tirage> tirages = tirageRepo.findByUtilisateurId(userId);
        return tirageMapper.toDtoList(tirages);
    }

    public TirageDto getTirageById(Long tirageId) {
        Tirage tirage = tirageRepo.findById(tirageId)
                .orElseThrow(() -> new RuntimeException("Tirage non trouvé"));
        return tirageMapper.toDto(tirage);
    }

    public List<TirageDto> getAllTirages() {
        return tirageRepo.findAll()
                .stream()
                .map(tirageMapper::toDto)
                .collect(Collectors.toList());
    }

}
