package sen.saloum.Ramli.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sen.saloum.Ramli.enums.TypeFigure;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FigureRamli {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private Integer ordre;

    private String image;

    @ManyToOne
    @JoinColumn(name = "tirage_id")
    private Tirage tirage;

    @ManyToOne
    @JoinColumn(name = "type")
    private TypeFigure typeFigure;

    @OneToMany(mappedBy = "figure", cascade = CascadeType.ALL)
    private List<FigureLigne> lignes;

    @OneToMany(mappedBy = "figure", cascade = CascadeType.ALL)
    private List<Interpretation> interpretations;
}
