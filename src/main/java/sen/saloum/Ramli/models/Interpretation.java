package sen.saloum.Ramli.models;

import jakarta.persistence.*;
import sen.saloum.Ramli.enums.NomFigureBase;
import sen.saloum.Ramli.enums.TypeFigure;

import java.time.LocalDateTime;


@Entity
public class Interpretation {
    @Id @GeneratedValue
    private Long id;
    private String texteInterpretation;
    private LocalDateTime dateInterpretation;

    @ManyToOne
    private FigureRamli ramli;

    @OneToOne
    private Tirage tirage;

    public Interpretation() {

    }

    public Interpretation(Long id, String texteInterpretation,
                          LocalDateTime dateInterpretation, FigureRamli ramli, Tirage tirage) {
        this.id = id;
        this.texteInterpretation = texteInterpretation;
        this.dateInterpretation = dateInterpretation;
        this.ramli = ramli;
        this.tirage = tirage;
    }



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

    public FigureRamli getRamli() {
        return ramli;
    }

    public void setRamli(FigureRamli ramli) {
        this.ramli = ramli;
    }

    public Tirage getTirage() {
        return tirage;
    }

    public void setTirage(Tirage tirage) {
        this.tirage = tirage;
    }

    public void setFigure(FigureRamli figure) {
    }
}
