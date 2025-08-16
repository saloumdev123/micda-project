package sen.saloum.Ramli.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import sen.saloum.Ramli.dto.figure.FigureLignesDto;
import sen.saloum.Ramli.models.FigureLigne;
import sen.saloum.Ramli.models.FigureLigneId;

@Mapper(componentModel = "spring")
public interface FigureLigneMapper {

    @Mappings({
        @Mapping(source = "figure.id", target = "figureId"),
        @Mapping(source = "id.ligneIndex", target = "ligneIndex"),
        @Mapping(source = "nomLigne", target = "nomLigne"),
        @Mapping(source = "point1", target = "point1"),
        @Mapping(source = "point2", target = "point2"),
        @Mapping(source = "point3", target = "point3"),
        @Mapping(source = "point4", target = "point4"),
        @Mapping(target = "valeur", expression = "java(calculerValeur(entity))")
    })
    FigureLignesDto toDto(FigureLigne entity);

    @Mappings({
        @Mapping(target = "id", expression = "java(mapToId(dto.getFigureId(), dto.getLigneIndex()))"),
        @Mapping(source = "nomLigne", target = "nomLigne"),
        @Mapping(source = "point1", target = "point1"),
        @Mapping(source = "point2", target = "point2"),
        @Mapping(source = "point3", target = "point3"),
        @Mapping(source = "point4", target = "point4"),
        // recalculer la valeur ici, ou bien le laisser vide si calculé ailleurs
        @Mapping(target = "valeur", expression = "java((dto.getPoint1() + dto.getPoint2() + dto.getPoint3() + dto.getPoint4()) % 2)")
    })
    FigureLigne toEntity(FigureLignesDto dto);

    default FigureLigneId mapToId(Long figureId, int ligneIndex) {
        FigureLigneId id = new FigureLigneId();
        id.setFigureId(figureId);
        id.setLigneIndex(ligneIndex);
        return id;
    }

    default int calculerValeur(FigureLigne ligne) {
        return (ligne.getPoint1() + ligne.getPoint2() + ligne.getPoint3() + ligne.getPoint4()) % 2;
    }
}
