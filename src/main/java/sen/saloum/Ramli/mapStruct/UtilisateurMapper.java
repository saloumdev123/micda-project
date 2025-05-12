package sen.saloum.Ramli.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import sen.saloum.Ramli.dto.user.UtilisateurDto;
import sen.saloum.Ramli.models.Utilisateur;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

    UtilisateurMapper INSTANCE = Mappers.getMapper(UtilisateurMapper.class);

    UtilisateurDto toDto(Utilisateur utilisateur);

    Utilisateur toEntity(UtilisateurDto utilisateurDto);
}
