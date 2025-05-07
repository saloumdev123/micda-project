package sen.saloum.Ramli.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@IdClass(FigureLigneId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FigureLigne {

    @Id
    @ManyToOne
    @JoinColumn(name = "figure_id")
    private FigureRamli figure;

    @Id
    private int ligneIndex;
    private int valeur;


}
