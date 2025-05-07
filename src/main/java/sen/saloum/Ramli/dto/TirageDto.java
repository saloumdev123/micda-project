package sen.saloum.Ramli.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TirageDto {
    private Long id;
    private String nomTirage;
    private LocalDateTime dateTirage;
    private String interpretation;
    private Long utilisateurId;
}
