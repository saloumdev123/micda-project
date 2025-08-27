import { Injectable } from '@angular/core';
import { Ramli } from '../modele/ramli';

@Injectable({
  providedIn: 'root'
})
export class InterpretationService {
 private interpretations: Record<string, string> = {
    'Al-Lahjah': 'Votre tirage révèle une période propice à la communication...',
    'Al-Jamiah': 'L’union fait la force. Votre tirage suggère...',
    'Al-Nasrah': 'La victoire est à portée de main ! Vos efforts passés...'
  };

  generateInterpretation(figure: Ramli): string {
    return this.interpretations[figure.nomFigure] || 'Interprétation en cours de développement.';
  }
}
