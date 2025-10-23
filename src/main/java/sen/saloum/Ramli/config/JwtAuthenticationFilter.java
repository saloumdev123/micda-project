package sen.saloum.Ramli.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sen.saloum.Ramli.models.Utilisateur;
import sen.saloum.Ramli.repos.UtilisateurRepository;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtUtil jwtUtil;
    private final UtilisateurRepository utilisateurRepository;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UtilisateurRepository utilisateurRepository) {
        this.jwtUtil = jwtUtil;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();
        // Ignorer les endpoints publics
        if (path.equals("/api/auth/login")
                || path.equals("/api/auth/register")
                || path.equals("/api/auth/forgot-password")
                || path.equals("/api/auth/reset-password")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                jwt = authHeader.substring(7);

                // Vérification du token
                if (jwtUtil.validateToken(jwt)) {
                    username = jwtUtil.getUsernameFromToken(jwt);
                } else {
                    logger.warn("JWT invalide : {}", jwt);
                }
            }

            // Authentification si pas encore faite
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                Utilisateur utilisateur = utilisateurRepository.findByEmail(username).orElse(null);

                if (utilisateur != null) {
                    String roleName = "ROLE_" + (utilisateur.getRole() != null ? utilisateur.getRole().name() : "CLIENT");

                    // ✅ Modification : principal = email
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    utilisateur.getEmail(), // ici on passe l'email comme principal
                                    null,
                                    Collections.singletonList(new SimpleGrantedAuthority(roleName))
                            );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                    logger.info("Utilisateur authentifié : {} avec rôle {}", utilisateur.getEmail(), roleName);
                } else {
                    logger.warn("Utilisateur non trouvé pour username : {}", username);
                }
            }
        } catch (Exception e) {
            logger.error("Erreur dans JwtAuthenticationFilter : {}", e.getMessage(), e);
        }

        filterChain.doFilter(request, response);
    }
}
