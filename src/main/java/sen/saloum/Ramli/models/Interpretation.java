package sen.saloum.Ramli.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Interpretation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomFigure;

    @Column(columnDefinition = "TEXT")
    private String signification;

    private String culture;

    private String source;

    // Relations
    @ManyToOne
    @JoinColumn(name = "figure_id")
    private FigureRamli figure;
}
