package sen.saloum.Ramli.dto.figure;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import sen.saloum.Ramli.enums.NomFigureBase;
import sen.saloum.Ramli.enums.TypeFigure;
import sen.saloum.Ramli.models.Interpretation;

import java.time.LocalDateTime;

public class InterpretationDto {

    private Long id;
    private String texteInterpretation;
    private LocalDateTime dateInterpretation;
    private Long ramliId;
    private Long tirageId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexteInterpretation() {
        return texteInterpretation;
    }

    public void setTexteInterpretation(String texteInterpretation) {
        this.texteInterpretation = texteInterpretation;
    }

    public LocalDateTime getDateInterpretation() {
        return dateInterpretation;
    }

    public void setDateInterpretation(LocalDateTime dateInterpretation) {
        this.dateInterpretation = dateInterpretation;
    }

    public Long getRamliId() {
        return ramliId;
    }

    public void setRamliId(Long ramliId) {
        this.ramliId = ramliId;
    }

    public Long getTirageId() {
        return tirageId;
    }

    public void setTirageId(Long tirageId) {
        this.tirageId = tirageId;
    }


}
