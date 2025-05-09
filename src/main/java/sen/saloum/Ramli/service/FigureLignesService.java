package sen.saloum.Ramli.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sen.saloum.Ramli.dto.FigureLignesDto;
import sen.saloum.Ramli.mapStruct.FigureLignesMapper;
import sen.saloum.Ramli.models.FigureLigne;
import sen.saloum.Ramli.repos.FigureLignesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FigureLignesService {

    private final FigureLignesRepository figureLignesRepository;
    private final FigureLignesMapper figureLignesMapper;

    public FigureLignesDto create(FigureLignesDto dto) {
        FigureLigne entity = figureLignesMapper.toEntity(dto);
        return figureLignesMapper.toDto(figureLignesRepository.save(entity));
    }

    public List<FigureLignesDto> getAll() {
        return figureLignesRepository.findAll()
                .stream()
                .map(figureLignesMapper::toDto)
                .collect(Collectors.toList());
    }

    public FigureLignesDto getById(Long id) {
        FigureLigne entity = figureLignesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FigureLignes non trouvé avec id: " + id));
        return figureLignesMapper.toDto(entity);
    }

    public FigureLignesDto update(Long id, FigureLignesDto dto) {
        FigureLigne existing = figureLignesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("FigureLignes non trouvé avec id: " + id));
        
        existing.setNom(dto.getNom());
        // Ajoute d'autres champs ici si nécessaire

        return figureLignesMapper.toDto(figureLignesRepository.save(existing));
    }

    public void delete(Long id) {
        if (!figureLignesRepository.existsById(id)) {
            throw new EntityNotFoundException("FigureLignes non trouvé avec id: " + id);
        }
        figureLignesRepository.deleteById(id);
    }
}
