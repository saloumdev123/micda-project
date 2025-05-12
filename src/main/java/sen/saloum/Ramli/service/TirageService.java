package sen.saloum.Ramli.service;

import org.springframework.stereotype.Service;
import sen.saloum.Ramli.dto.tirage.TirageDto;
import sen.saloum.Ramli.mapStruct.TirageMapper;
import sen.saloum.Ramli.models.Tirage;
import sen.saloum.Ramli.repos.TirageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TirageService {


    private final TirageRepository tirageRepository;
    private final TirageMapper tirageMapper;

    public TirageService(TirageRepository tirageRepository, TirageMapper tirageMapper) {
        this.tirageRepository = tirageRepository;
        this.tirageMapper = tirageMapper;
    }

    public TirageDto createTirage(TirageDto dto) {
        Tirage entity = tirageMapper.toEntity(dto);
        entity = tirageRepository.save(entity);
        return tirageMapper.toDto(entity);
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

    public List<Integer> genererTirage() {
        List<Integer> tirage = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 16; i++) {
            tirage.add(random.nextInt(2)); // 0 ou 1
        }

        // Convertir la liste en String "010101..."
        String tirageString = tirage.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());

        // Créer l'entité Tirage
        Tirage tirageEntity = new Tirage();
        tirageEntity.setValeurs(tirageString); // <-- Assure-toi que ce champ existe dans l'entité

        tirageRepository.save(tirageEntity);

        return tirage;
    }
}
