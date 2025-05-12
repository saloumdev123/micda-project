package sen.saloum.Ramli.dto.tirage;

import sen.saloum.Ramli.models.Utilisateur;

public class TirageRequestDto {
    private Utilisateur utilisateur;
    private DonneesDeBaseDto lignesDeDepart;

    public TirageRequestDto() {
    }

    public TirageRequestDto(Utilisateur utilisateur, DonneesDeBaseDto lignesDeDepart) {
        this.utilisateur = utilisateur;
        this.lignesDeDepart = lignesDeDepart;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public DonneesDeBaseDto getLignesDeDepart() {
        return lignesDeDepart;
    }

    public void setLignesDeDepart(DonneesDeBaseDto lignesDeDepart) {
        this.lignesDeDepart = lignesDeDepart;
    }
}
