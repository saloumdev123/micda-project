package sen.saloum.Ramli.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import sen.saloum.Ramli.dto.user.UtilisateurDto;
import sen.saloum.Ramli.mapStruct.UtilisateurMapper;
import sen.saloum.Ramli.models.Utilisateur;
import sen.saloum.Ramli.repos.UtilisateurRepository;

@Service
public class UtilisateurService {

    private final UtilisateurRepository userRepository;
    private final UtilisateurMapper userMapper;

    public UtilisateurService(UtilisateurRepository userRepository, UtilisateurMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public UtilisateurDto create(UtilisateurDto dto) {
        Utilisateur entity = userMapper.toEntity(dto);
        return userMapper.toDto(userRepository.save(entity));
    }

    public List<UtilisateurDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UtilisateurDto findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
    public UtilisateurDto update(Long id, UtilisateurDto dto) {
        Utilisateur existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setNom(dto.getNom());
        existingUser.setPrenom(dto.getPrenom());
        existingUser.setEmail(dto.getEmail());
        existingUser.setRole(dto.getRole());
        existingUser.setDateInscription(dto.getDateInscription());

        return userMapper.toDto(userRepository.save(existingUser));
    }

}
