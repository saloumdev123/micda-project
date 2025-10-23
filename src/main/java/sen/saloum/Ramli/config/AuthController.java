package sen.saloum.Ramli.config;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sen.saloum.Ramli.dto.user.UtilisateurDto;
import sen.saloum.Ramli.enums.Role;
import sen.saloum.Ramli.exceptions.UserNotFoundException;
import sen.saloum.Ramli.mapStruct.UtilisateurMapper;
import sen.saloum.Ramli.models.Utilisateur;
import sen.saloum.Ramli.models.security.AuthResponse;
import sen.saloum.Ramli.models.security.ForgotPasswordRequest;
import sen.saloum.Ramli.models.security.ResetPasswordRequest;
import sen.saloum.Ramli.repos.UtilisateurRepository;
import sen.saloum.Ramli.service.security.AuthService;
import sen.saloum.Ramli.utils.MessageResponse;

import java.time.OffsetDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final UtilisateurMapper utilisateurMapper;

    public AuthController(AuthService authService, UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder, UtilisateurMapper utilisateurMapper) {
        this.authService = authService;
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.utilisateurMapper = utilisateurMapper;
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(response);
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        if (utilisateurRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email déjà utilisé");
        }

        Utilisateur user = new Utilisateur();
        user.setEmail(request.getEmail());
        user.setPrenom(request.getPrenom());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setNom(request.getNom());
        user.setDateInscription(OffsetDateTime.now());
        user.setRole(Role.CLIENT);

        utilisateurRepository.save(user);
        return ResponseEntity.ok("Utilisateur créé avec succès");
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        AuthResponse response = authService.refreshToken(request.getRefreshToken());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody TokenRefreshRequest request) {
        authService.logout(request.getRefreshToken());
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        logger.info("Forgot password requested for email: {}", request.getEmail());

        try {
            // Appel au service
            authService.forgotPassword(request.getEmail());
            logger.info("Password reset link successfully sent to {}", request.getEmail());
            return ResponseEntity.ok("Reset link sent to your email.");
        } catch (UserNotFoundException e) {
            logger.error("User not found with email: {}", request.getEmail());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e) {
            logger.error("Unexpected error in forgot password for {}: {}", request.getEmail(), e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<MessageResponse> resetPassword(@RequestBody ResetPasswordRequest request) {
        authService.resetPassword(request.getToken(), request.getNewPassword());
        return ResponseEntity.ok(new MessageResponse("Mot de passe réinitialisé avec succès. Veuillez vous reconnecter."));
    }

    @GetMapping("/profile")
    public ResponseEntity<UtilisateurDto> getProfile(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = authentication.getName();
        Utilisateur user = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new org.springframework.security.core.userdetails.UsernameNotFoundException("Utilisateur non trouvé"));

        UtilisateurDto dto = new UtilisateurDto();
        dto.setId(user.getId());
        dto.setNom(user.getNom());
        dto.setPrenom(user.getPrenom());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setDateInscription(OffsetDateTime.now());
        return ResponseEntity.ok(dto);
    }
    @PutMapping("/profile")
    public ResponseEntity<UtilisateurDto> updateProfile(
            Authentication authentication,
            @RequestBody UtilisateurDto dto
    ) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = authentication.getName();
        Utilisateur user = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        user.setNom(dto.getNom());
        user.setPrenom(dto.getPrenom());

        utilisateurRepository.save(user);

        UtilisateurDto updated = new UtilisateurDto();
        updated.setId(user.getId());
        updated.setNom(user.getNom());
        updated.setPrenom(user.getPrenom());
        updated.setEmail(user.getEmail());
        updated.setRole(user.getRole());
        updated.setDateInscription(OffsetDateTime.now());

        return ResponseEntity.ok(updated);
    }


}


