import { Injectable } from '@angular/core';
import { delay, Observable, of } from 'rxjs';
import { Tirage } from '../modele/tirage';
import { Ramli } from '../modele/ramli';
import { Interpretation } from '../modele/interpretation';

@Injectable({
  providedIn: 'root'
})
export class RamliService {

 private mockFigures: Ramli[] = [
    {
      id: 1,
      nomFigure: 'Al-Lahjah',
      description: 'Figure de la parole et de la communication',
      symbolisme: 'Cette figure représente l\'expression, la communication et les échanges. Elle indique une période favorable aux discussions importantes et aux négociations.',
      lignes: [
        { id: 1, valeurs: '••', position: 1 },
        { id: 2, valeurs: '•', position: 2 },
        { id: 3, valeurs: '•', position: 3 },
        { id: 4, valeurs: '••', position: 4 }
      ]
    },
    {
      id: 2,
      nomFigure: 'Al-Jamiah',
      description: 'Figure de l\'assemblée et du collectif',
      symbolisme: 'Cette figure évoque la communauté, les rassemblements et la force du groupe. Elle suggère l\'importance de l\'entraide et du soutien mutuel.',
      lignes: [
        { id: 5, valeurs: '••', position: 1 },
        { id: 6, valeurs: '••', position: 2 },
        { id: 7, valeurs: '•', position: 3 },
        { id: 8, valeurs: '•', position: 4 }
      ]
    },
    {
      id: 3,
      nomFigure: 'Al-Nasrah',
      description: 'Figure de la victoire et du triomphe',
      symbolisme: 'Symbole de réussite et d\'accomplissement. Cette figure annonce une période de succès et de reconnaissance de vos efforts.',
      lignes: [
        { id: 9, valeurs: '•', position: 1 },
        { id: 10, valeurs: '••', position: 2 },
        { id: 11, valeurs: '•', position: 3 },
        { id: 12, valeurs: '••', position: 4 }
      ]
    }
  ];

  private userTirages: Tirage[] = [];

  constructor() {}

  effectuerTirage(userId: number): Observable<Tirage> {
    // Simulate tirage generation
    const randomFigure = this.mockFigures[Math.floor(Math.random() * this.mockFigures.length)];
    
    const newTirage: Tirage = {
      id: Date.now(),
      dateTirage: new Date(),
      utilisateurId: userId,
      figures: [randomFigure]
    };

    // Generate interpretation
    const interpretation: Interpretation = {
      id: Date.now() + 1,
      texteInterpretation: this.generateInterpretation(randomFigure),
      ramli: randomFigure,
      tirageId: newTirage.id,
      dateInterpretation: new Date()
    };

    newTirage.interpretation = interpretation;
    this.userTirages.unshift(newTirage);

    return of(newTirage).pipe(delay(2000)); // Simulate API delay
  }

  getTiragesUtilisateur(userId: number): Observable<Tirage[]> {
    const userTirages = this.userTirages.filter(tirage => tirage.utilisateurId === userId);
    return of(userTirages);
  }

  getTirageById(id: number): Observable<Tirage | undefined> {
    const tirage = this.userTirages.find(t => t.id === id);
    return of(tirage);
  }

  private generateInterpretation(figure: Ramli): string {
    const interpretations = {
      'Al-Lahjah': 'Votre tirage révèle une période propice à la communication. Les échanges seront fructueux et vous devriez exprimer clairement vos intentions. Une discussion importante pourrait changer le cours des événements.',
      'Al-Jamiah': 'L\'union fait la force. Votre tirage suggère que c\'est le moment de vous rapprocher de vos proches ou de votre communauté. Le soutien mutuel sera la clé de votre réussite dans les projets à venir.',
      'Al-Nasrah': 'La victoire est à portée de main ! Vos efforts passés portent leurs fruits. Cette période sera marquée par des succès et des reconnaissances. Persévérez dans vos entreprises actuelles.'
    };

    return interpretations[figure.nomFigure as keyof typeof interpretations] || 'Interprétation en cours de développement.';
  }
}
