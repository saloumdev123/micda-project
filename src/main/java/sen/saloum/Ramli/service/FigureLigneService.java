package sen.saloum.Ramli.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sen.saloum.Ramli.dto.figure.FigureLignesDto;
import sen.saloum.Ramli.mapStruct.FigureLigneMapper;
import sen.saloum.Ramli.models.FigureLigne;
import sen.saloum.Ramli.models.FigureRamli;
import sen.saloum.Ramli.repos.FigureLignesRepository;
import sen.saloum.Ramli.repos.FigureRamliRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FigureLigneService {
    private static final Logger logger = LoggerFactory.getLogger(FigureLigneService.class);
    private final FigureLignesRepository figureLigneRepository;
    private final FigureRamliRepository figureRamliRepository;
    private final FigureLigneMapper figureLigneMapper;

    public FigureLigneService(FigureLignesRepository figureLigneRepository,
                              FigureRamliRepository figureRamliRepository,
                              FigureLigneMapper figureLigneMapper) {
        this.figureLigneRepository = figureLigneRepository;
        this.figureRamliRepository = figureRamliRepository;
        this.figureLigneMapper = figureLigneMapper;

    }


    public FigureLignesDto create(FigureLignesDto dto) {
        FigureLigne entity = figureLigneMapper.toEntity(dto);

        FigureRamli figure = figureRamliRepository.findById(dto.getFigureId())
                .orElseThrow(() -> new RuntimeException("FigureRamli not found"));
        entity.setFigure(figure);  // set the full entity

        entity = figureLigneRepository.save(entity);
        return figureLigneMapper.toDto(entity);
    }
    public List<FigureLignesDto> getByFigureId(Long figureId) {
        return figureLigneRepository.findByFigureId(figureId)
                .stream()
                .map(figureLigneMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<FigureLignesDto> genererLignesDepuisTirage(List<Integer> tirage, Long figureId) {
        if (tirage == null || tirage.size() % 4 != 0) {
            throw new IllegalArgumentException("Le tirage doit contenir un nombre de bits multiple de 4");
        }

        List<FigureLignesDto> lignesDto = new ArrayList<>();
        int ligneIndex = 0;

        for (int i = 0; i < tirage.size(); i += 4) {
            FigureLignesDto dto = new FigureLignesDto();
            dto.setFigureId(figureId);
            dto.setLigneIndex(ligneIndex);
            dto.setPoint1(tirage.get(i));
            dto.setPoint2(tirage.get(i + 1));
            dto.setPoint3(tirage.get(i + 2));
            dto.setPoint4(tirage.get(i + 3));
            dto.setNomLigne("Ligne " + (ligneIndex + 1));
            dto.calculerValeur();

            lignesDto.add(dto);
            ligneIndex++;
        }

        return lignesDto;
    }

    public void saveAll(List<FigureLigne> lignes)  {
        figureLigneRepository.saveAll(lignes);
    }
}
