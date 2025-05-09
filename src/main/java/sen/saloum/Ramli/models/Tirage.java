package sen.saloum.Ramli.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tirage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomTirage;

    private LocalDateTime dateTirage;

    @Column(columnDefinition = "TEXT")
    private String interpretation;

    private String nom;
    private String question;
    private String figureResultats;

    // Relations
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "tirage", cascade = CascadeType.ALL)
    private List<FigureRamli> figures;
}
