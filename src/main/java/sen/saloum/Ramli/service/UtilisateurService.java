package sen.saloum.Ramli.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import jakarta.persistence.EntityNotFoundException;
import sen.saloum.Ramli.dto.UtilisateurDto;
import sen.saloum.Ramli.mapStruct.UtilisateurMapper;
import sen.saloum.Ramli.models.Utilisateur;
import sen.saloum.Ramli.repos.UtilisateurRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;

    public UtilisateurDto create(UtilisateurDto dto) {
        Utilisateur entity = utilisateurMapper.toEntity(dto);
        return utilisateurMapper.toDto(utilisateurRepository.save(entity));
    }

    public List<UtilisateurDto> getAll() {
        return utilisateurRepository.findAll()
                .stream()
                .map(utilisateurMapper::toDto)
                .collect(Collectors.toList());
    }

    public UtilisateurDto getById(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé avec id: " + id));
        return utilisateurMapper.toDto(utilisateur);
    }

    public UtilisateurDto update(Long id, UtilisateurDto dto) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé avec id: " + id));

        utilisateur.setNom(dto.getNom());
        utilisateur.setEmail(dto.getEmail());
        utilisateur.setMotDePasse(dto.getMotDePasse());
        utilisateur.setRole(dto.getRole());

        return utilisateurMapper.toDto(utilisateurRepository.save(utilisateur));
    }

    public void delete(Long id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new EntityNotFoundException("Utilisateur non trouvé avec id: " + id);
        }
        utilisateurRepository.deleteById(id);
    }
}
