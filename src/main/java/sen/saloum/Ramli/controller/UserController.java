package sen.saloum.Ramli.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sen.saloum.Ramli.dto.user.UtilisateurDto;
import sen.saloum.Ramli.service.UtilisateurService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UtilisateurService userService;

    public UserController(UtilisateurService userService) {
        this.userService = userService;

    }

    @PostMapping
    public ResponseEntity<UtilisateurDto> createUser(@RequestBody UtilisateurDto userDto) {
        UtilisateurDto createdUser = userService.create(userDto);
        return ResponseEntity.ok(createdUser);
    }

    /**
     * Récupérer tous les utilisateurs
     */
    @GetMapping
    public ResponseEntity<List<UtilisateurDto>> getAllUsers() {
        List<UtilisateurDto> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    /**
     * Récupérer un utilisateur par son ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDto> getUserById(@PathVariable Long id) {
        UtilisateurDto user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Supprimer un utilisateur par son ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
