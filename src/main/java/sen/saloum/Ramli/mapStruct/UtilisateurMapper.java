package sen.saloum.Ramli.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import sen.saloum.Ramli.dto.user.UtilisateurDto;
import sen.saloum.Ramli.models.Utilisateur;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

    Utilisateur toEntity(UtilisateurDto dto);
    UtilisateurDto toDto(Utilisateur user);
}
