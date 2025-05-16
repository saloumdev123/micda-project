package sen.saloum.Ramli.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sen.saloum.Ramli.dto.tirage.TirageDto;
import sen.saloum.Ramli.mapStruct.TirageMapper;
import sen.saloum.Ramli.models.FigureRamli;
import sen.saloum.Ramli.models.Tirage;
import sen.saloum.Ramli.models.Utilisateur;
import sen.saloum.Ramli.repos.TirageRepository;
import sen.saloum.Ramli.repos.UtilisateurRepository;
import sen.saloum.Ramli.utils.FigureUtils;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TirageService {

    private final TirageRepository tirageRepository;
    private final TirageMapper tirageMapper;
    private final UtilisateurRepository utilisateurRepository;

    public TirageService(TirageRepository tirageRepository, TirageMapper tirageMapper, UtilisateurRepository utilisateurRepository) {
        this.tirageRepository = tirageRepository;
        this.tirageMapper = tirageMapper;
        this.utilisateurRepository = utilisateurRepository;
    }
    public List<Integer> genererTirageValeurs() {
        List<Integer> tirage = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            tirage.add(random.nextInt(2)); // 0 ou 1
        }
        return tirage;
    }

    @Transactional
    public TirageDto creerTirageAleatoire(Long utilisateurId, TirageDto dto) {
        List<Integer> tirage = genererTirageValeurs();
        String tirageString = tirage.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());

        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Tirage tirageEntity = new Tirage();
        tirageEntity.setValeurs(tirageString);
        tirageEntity.setDateTirage(OffsetDateTime.now());
        tirageEntity.setUtilisateur(utilisateur);
        tirageEntity.setNomConsultant(utilisateur.getNom());

        // valeurs venant du JSON
        tirageEntity.setNomTirage(dto.getNomTirage());
        tirageEntity.setQuestion(dto.getQuestion());
        tirageEntity.setInterpretation(dto.getInterpretation());
        tirageEntity.setNomFigureBase(dto.getNomFigureBase());
        tirageEntity.setTypeFigure(dto.getTypeFigure());
        // 🔽 GÉNÉRATION DES FIGURES À PARTIR DES 16 VALEURS BINAIRES
        List<List<Integer>> figures = genererFiguresDepuis16Bits(tirage);
        tirageEntity.setFigures(new ArrayList<>()); // méthode à créer
        int ordre = 1;
        for (List<Integer> figure : figures) {
            FigureRamli f = new FigureRamli();
            f.setOrdre(ordre++);
            f.setValeurs(figureToString(figure)); // méthode utilitaire à écrire
            f.setTirage(tirageEntity);
            tirageEntity.getFigures().add(f); // assure-toi que tirageEntity.setFigures(new ArrayList<>()) a été fait avant
        }

        tirageEntity = tirageRepository.save(tirageEntity);
        return tirageMapper.toDto(tirageEntity);
    }
    private String figureToString(List<Integer> figure) {
        return figure.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private List<List<Integer>> genererFiguresDepuis16Bits(List<Integer> tirage) {

        List<List<Integer>> figures = new ArrayList<>();

        // Générer les 4 figures témoins : chaque figure est composée de 4 bits
        for (int i = 0; i < 16; i += 4) {
            List<Integer> figure = tirage.subList(i, i + 4); // sous-liste de 4 bits
            figures.add(new ArrayList<>(figure)); // copier la sous-liste
        }

        // Générer les figures dérivées à partir des figures témoins
        List<List<Integer>> derivees = genererFiguresDerivees(figures);

        return derivees;
    }

    public List<TirageDto> getAllTirages() {
        return tirageRepository.findAll()
                .stream()
                .map(tirageMapper::toDto)
                .collect(Collectors.toList());
    }
    public TirageDto getTirageById(Long id) {
        Tirage entity = tirageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tirage not found"));
        return tirageMapper.toDto(entity);
    }
    private List<List<Integer>> genererFiguresDerivees(List<List<Integer>> temoins) {
        List<List<Integer>> figures = new ArrayList<>(temoins); // Ajouter les témoins

        // Filles
        List<Integer> f1 = FigureUtils.creerFigureComposee(temoins.get(0), temoins.get(1));
        List<Integer> f2 = FigureUtils.creerFigureComposee(temoins.get(2), temoins.get(3));
        figures.add(f1);
        figures.add(f2);

        // Nièces
        List<Integer> f3 = FigureUtils.creerFigureComposee(f1, f2);
        figures.add(f3);

        // Juge
        List<Integer> juge = FigureUtils.creerFigureComposee(f3, temoins.get(3));
        figures.add(juge);

        return figures;
    }

    public void deleteTirage(Long id) {
        tirageRepository.deleteById(id);
    }

}
