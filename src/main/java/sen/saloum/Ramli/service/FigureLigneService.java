package sen.saloum.Ramli.service;

import org.springframework.stereotype.Service;
import sen.saloum.Ramli.dto.figure.FigureLignesDto;
import sen.saloum.Ramli.mapStruct.FigureLignesMapper;
import sen.saloum.Ramli.models.FigureLigne;
import sen.saloum.Ramli.models.FigureLigneId;
import sen.saloum.Ramli.models.FigureRamli;
import sen.saloum.Ramli.repos.FigureLignesRepository;
import sen.saloum.Ramli.repos.FigureRamliRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FigureLigneService {

    private final FigureLignesRepository figureLigneRepository;
    private final FigureRamliRepository figureRamliRepository;
    private final FigureLignesMapper figureLignesMapper;

    public FigureLigneService(FigureLignesRepository figureLigneRepository, FigureRamliRepository figureRamliRepository, FigureLignesMapper figureLignesMapper) {
        this.figureLigneRepository = figureLigneRepository;
        this.figureRamliRepository = figureRamliRepository;
        this.figureLignesMapper = figureLignesMapper;
    }

    public FigureLignesDto create(FigureLignesDto dto) {
        FigureLigne entity = figureLignesMapper.toEntity(dto);

        // This is the fix
        FigureRamli figure = figureRamliRepository.findById(dto.getFigureId())
                .orElseThrow(() -> new RuntimeException("FigureRamli not found"));
        entity.setFigure(figure);  // set the full entity

        entity = figureLigneRepository.save(entity);
        return figureLignesMapper.toDto(entity);
    }
    public List<FigureLignesDto> getByFigureId(Long figureId) {
        return figureLigneRepository.findByFigureId(figureId)
                .stream()
                .map(figureLignesMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<FigureLignesDto> genererLignesDepuisTirage(List<Integer> tirage, Long figureId) {
        if (tirage == null || tirage.size() % 4 != 0) {
            throw new IllegalArgumentException("Le tirage doit contenir un nombre de bits multiple de 4");
        }

        List<FigureLigne> lignes = new ArrayList<>();
        int ligneIndex = 0;

        for (int i = 0; i < tirage.size(); i += 4) {
            FigureLigne ligne = new FigureLigne();
            ligne.setPoint1(tirage.get(i));
            ligne.setPoint2(tirage.get(i + 1));
            ligne.setPoint3(tirage.get(i + 2));
            ligne.setPoint4(tirage.get(i + 3));
            ligne.setValeur(ligne.getPoint1() + ligne.getPoint2() + ligne.getPoint3() + ligne.getPoint4());
            ligne.setNomLigne("Ligne " + (ligneIndex + 1));

            FigureLigneId id = new FigureLigneId();
            id.setFigureId(figureId);
            id.setLigneIndex(ligneIndex);
            ligne.setId(id);

            lignes.add(ligne);
            ligneIndex++;
        }

        return lignes.stream()
                .map(figureLignesMapper::toDto)
                .collect(Collectors.toList());
    }


    public void saveAll(List<FigureLigne> lignes) {
        figureLigneRepository.saveAll(lignes);
    }
}
