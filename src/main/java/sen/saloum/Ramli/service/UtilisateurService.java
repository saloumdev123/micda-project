package sen.saloum.Ramli.service;
;
import org.springframework.stereotype.Service;
import sen.saloum.Ramli.dto.user.UtilisateurDto;
import sen.saloum.Ramli.mapStruct.UtilisateurMapper;
import sen.saloum.Ramli.models.Utilisateur;
import sen.saloum.Ramli.repos.UtilisateurRepository;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    private final UtilisateurMapper utilisateurMapper;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, UtilisateurMapper utilisateurMapper) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurMapper = utilisateurMapper;
    }

    public UtilisateurDto create(UtilisateurDto dto) {
        Utilisateur entity = utilisateurMapper.toEntity(dto);
        entity = utilisateurRepository.save(entity);
        return utilisateurMapper.toDto(entity);
    }

    public UtilisateurDto getById(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur not found"));
        return utilisateurMapper.toDto(utilisateur);
    }
}
