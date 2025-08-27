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

@Service
public class TirageService {
    private final FigureRamliRepository figureRepo;
    private final FigureLigneService ligneService;
    private final InterpretationService interpretationService;
    private final TirageRepository tirageRepo;
    private final UtilisateurRepository utilisateurRepository;
    private final TirageMapper tirageMapper;

    public TirageService(FigureRamliRepository figureRepo,
                         FigureLigneService ligneService,
                         InterpretationService interpretationService,
                         TirageRepository tirageRepo, UtilisateurRepository utilisateurRepository, TirageMapper tirageMapper) {
        this.figureRepo = figureRepo;
        this.ligneService = ligneService;
        this.interpretationService = interpretationService;
        this.tirageRepo = tirageRepo;
        this.utilisateurRepository = utilisateurRepository;
        this.tirageMapper = tirageMapper;
    }

    public TirageDto effectuerTirage(Long userId) {
        // 1. Générer 4 lignes aléatoires
        List<Integer> tirage = genererPoints(16); // 4 x 4 points

        // 2. Créer une Figure
        FigureRamli figure = new FigureRamli();
        figure.setNomFigure("Al-Lahjah"); // ou déterminer dynamiquement
        figure.setDescription("Description auto...");
        figureRepo.save(figure);

        // 3. Associer les lignes
        ligneService.genererLignesDepuisTirage(tirage, figure.getId());

        // 4. Créer une interprétation
        Interpretation interpretation = interpretationService.genererPourFigure(figure);

        // 5. Récupérer utilisateur
        Utilisateur user = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec id " + userId));

        // 6. Créer le tirage
        Tirage tirageEntity = new Tirage();
        tirageEntity.setUtilisateur(user);
        tirageEntity.setDateTirage(LocalDateTime.now());
        tirageEntity.setFigures(List.of(figure));
        tirageEntity.setInterpretation(interpretation);

        tirageRepo.save(tirageEntity);

        // ⚡ ICI on mappe bien l'entité, pas la liste d’entiers
        return tirageMapper.toDto(tirageEntity);
    }


    private List<Integer> genererPoints(int nb) {
        Random rand = new Random();
        List<Integer> points = new ArrayList<>();
        for (int i = 0; i < nb; i++) {
            points.add(rand.nextBoolean() ? 1 : 0); // 1 = •, 0 = ○
        }
        return points;
    }
    public List<TirageDto> getTiragesByUser(Long userId) {
        List<Tirage> tirages = tirageRepo.findByUtilisateurId(userId);
        return tirageMapper.toDtoList(tirages);
    }

}
