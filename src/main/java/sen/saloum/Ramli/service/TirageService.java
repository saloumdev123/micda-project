package sen.saloum.Ramli.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import sen.saloum.Ramli.dto.TirageDto;
import sen.saloum.Ramli.mapStruct.TirageMapper;
import sen.saloum.Ramli.models.Tirage;
import sen.saloum.Ramli.repos.TirageRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TirageService {

    private final TirageRepository tirageRepository;
    private final TirageMapper tirageMapper;

    public TirageDto create(TirageDto dto) {
        Tirage tirage = tirageMapper.toEntity(dto);
        tirage.setDateTirage(LocalDateTime.now());
        Tirage saved = tirageRepository.save(tirage);
        return tirageMapper.toDto(saved);
    }

    public TirageDto getById(Long id) {
        Tirage tirage = tirageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tirage non trouvé avec ID: " + id));
        return tirageMapper.toDto(tirage);
    }

    public List<TirageDto> getAll() {
        return tirageRepository.findAll()
                .stream()
                .map(tirageMapper::toDto)
                .collect(Collectors.toList());
    }

    public TirageDto update(Long id, TirageDto dto) {
        Tirage existing = tirageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tirage non trouvé avec ID: " + id));

        existing.setNomTirage(dto.getNomTirage());
        existing.setQuestion(dto.getQuestion());
        existing.setFigureResultats(dto.getFigureResultats());
        existing.setDateTirage(dto.getDateTirage());

        return tirageMapper.toDto(tirageRepository.save(existing));
    }

    public void delete(Long id) {
        if (!tirageRepository.existsById(id)) {
            throw new EntityNotFoundException("Tirage non trouvé avec ID: " + id);
        }
        tirageRepository.deleteById(id);
    }
}
