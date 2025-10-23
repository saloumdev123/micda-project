package sen.saloum.Ramli.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import sen.saloum.Ramli.dto.user.UtilisateurDto;
import sen.saloum.Ramli.models.Utilisateur;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

    @Mapping(source = "prenom", target = "prenom")
    @Mapping(source = "nom", target = "nom")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "dateInscription", target = "dateInscription")
    Utilisateur toEntity(UtilisateurDto dto);
    UtilisateurDto toDto(Utilisateur user);
}
