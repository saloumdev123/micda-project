package sen.saloum.Ramli.dto;

import lombok.Data;

@Data
public class FigureRamliDto {
    private Long id;
    private String nom;
    private int ordre;
    private String image;
    private Long tirageId;
    private String type;
}
