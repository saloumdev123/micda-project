package sen.saloum.Ramli.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sen.saloum.Ramli.dto.InterpretationDto;
import sen.saloum.Ramli.mapStruct.InterpretationMapper;
import sen.saloum.Ramli.models.Interpretation;
import sen.saloum.Ramli.repos.InterpretationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterpretationService {

    private final InterpretationRepository interpretationRepository;
    private final InterpretationMapper interpretationMapper;

    public InterpretationDto create(InterpretationDto dto) {
        Interpretation entity = interpretationMapper.toEntity(dto);
        return interpretationMapper.toDto(interpretationRepository.save(entity));
    }

    public List<InterpretationDto> getAll() {
        return interpretationRepository.findAll()
                .stream()
                .map(interpretationMapper::toDto)
                .collect(Collectors.toList());
    }

    public InterpretationDto getById(Long id) {
        Interpretation entity = interpretationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Interpretation non trouvée avec id: " + id));
        return interpretationMapper.toDto(entity);
    }

    public InterpretationDto update(Long id, InterpretationDto dto) {
        Interpretation existing = interpretationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Interpretation non trouvée avec id: " + id));
        
        existing.setSignification(dto.getSignification());
        // Ajoute d'autres champs ici si nécessaire

        return interpretationMapper.toDto(interpretationRepository.save(existing));
    }

    public void delete(Long id) {
        if (!interpretationRepository.existsById(id)) {
            throw new EntityNotFoundException("Interpretation non trouvée avec id: " + id);
        }
        interpretationRepository.deleteById(id);
    }
}
