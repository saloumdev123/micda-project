package sen.saloum.Ramli.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sen.saloum.Ramli.dto.tirage.TirageDto;
import sen.saloum.Ramli.mapStruct.TirageMapper;
import sen.saloum.Ramli.models.Tirage;
import sen.saloum.Ramli.models.Utilisateur;
import sen.saloum.Ramli.repos.TirageRepository;
import sen.saloum.Ramli.repos.UtilisateurRepository;

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

        tirageEntity = tirageRepository.save(tirageEntity);
        return tirageMapper.toDto(tirageEntity);
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
    public void deleteTirage(Long id) {
        tirageRepository.deleteById(id);
    }

}
