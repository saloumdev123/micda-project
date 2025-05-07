package sen.saloum.Ramli.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@EqualsAndHashCode
public class FigureLigneId implements Serializable {
    private Long figure;
    private int ligneIndex;

    // equals() & hashCode()
}
