//package sen.saloum.Ramli.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//import sen.saloum.Ramli.exceptions.UsernameNotFoundException;
//import sen.saloum.Ramli.models.Utilisateur;
//import sen.saloum.Ramli.repos.UtilisateurRepository;
//
//import java.util.stream.Collectors;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UtilisateurRepository utilisateurRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Utilisateur utilisateur = utilisateurRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec le nom: " + username));
//
//        return new org.springframework.security.core.userdetails.User(
//                utilisateur.getUsername(),
//                utilisateur.getPassword(),
//                utilisateur.getRoles().stream()
//                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
//                        .collect(Collectors.toList())
//        );
//    }
//
//}
