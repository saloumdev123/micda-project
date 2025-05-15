package sen.saloum.Ramli.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sen.saloum.Ramli.dto.tirage.TirageDto;
import sen.saloum.Ramli.models.Tirage;
import sen.saloum.Ramli.models.Utilisateur;

@Mapper(componentModel = "spring")
public interface TirageMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nomTirage", target = "nomTirage")
    @Mapping(source = "question", target = "question")
    @Mapping(source = "nomConsultant", target = "nomConsultant")
    @Mapping(source = "dateTirage", target = "dateTirage")
    @Mapping(source = "interpretation", target = "interpretation")
    @Mapping(source = "valeurs", target = "valeurs")
    @Mapping(source = "nomFigureBase", target = "nomFigureBase")
    @Mapping(source = "typeFigure", target = "typeFigure")
    @Mapping(target = "utilisateur", ignore = true)  // Ignore the mapping of Utilisateur (as it will be set manually)
    Tirage toEntity(TirageDto tirageDto);

    default Tirage convertToEntity(TirageDto tirageDto, Utilisateur utilisateur) {
        Tirage tirage = new Tirage();
        tirage.setId(tirageDto.getId());
        tirage.setNomTirage(tirageDto.getNomTirage());
        tirage.setQuestion(tirageDto.getQuestion());
        tirage.setNomConsultant(tirageDto.getNomConsultant());
        tirage.setDateTirage(tirageDto.getDateTirage());
        tirage.setInterpretation(tirageDto.getInterpretation());
        tirage.setValeurs(tirageDto.getValeurs());
        tirage.setNomFigureBase(tirageDto.getNomFigureBase());
        tirage.setTypeFigure(tirageDto.getTypeFigure());
        tirage.setUtilisateur(utilisateur); // Set the actual Utilisateur entity
        return tirage;
    }

    // If needed, you can add the reverse mapping for converting from Tirage to TirageDto
    @Mapping(source = "utilisateur.id", target = "utilisateurId") // Assuming you want to map Utilisateur to a DTO
    TirageDto toDto(Tirage tirage);
}
