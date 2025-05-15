package sen.saloum.Ramli.models;

import jakarta.persistence.*;
import lombok.*;
import sen.saloum.Ramli.enums.NomFigureBase;
import sen.saloum.Ramli.enums.TypeFigure;


@Entity
public class Interpretation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String signification;

    private String culture;

    private String source;

    @ManyToOne
    @JoinColumn(name = "figure_id")
    private FigureRamli figure;

    public Interpretation() {
    }
    public Interpretation(Long id, String signification, String culture, String source,
                          FigureRamli figure) {
        this.id = id;
        this.signification = signification;
        this.culture = culture;
        this.source = source;
        this.figure = figure;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSignification() {
        return signification;
    }

    public void setSignification(String signification) {
        this.signification = signification;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public FigureRamli getFigure() {
        return figure;
    }

    public void setFigure(FigureRamli figure) {
        this.figure = figure;
    }
}
