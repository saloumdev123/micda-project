package sen.saloum.Ramli.service.impl;

import sen.saloum.Ramli.dto.user.UtilisateurDto;

import java.util.List;

public interface IUtilisateurService {
    UtilisateurDto create(UtilisateurDto dto);
    List<UtilisateurDto> getAll();
    UtilisateurDto getById(Long id);
    UtilisateurDto update(Long id, UtilisateurDto dto);
    void delete(Long id);
}
