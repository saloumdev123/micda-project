import { Component } from '@angular/core';
import { Tirage } from '../../modele/tirage';
import { AuthService } from '../../services/auth.service';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { TirageService } from '../../services/tirage.service';
import { InterpretationService } from '../../services/interpretation.service';
import { User } from '../../modele/user';
import { InterpretationRequest } from '../../modele/interpretationRequest';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-tirage',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule, RouterModule],
  templateUrl: './tirage.component.html',
  styleUrls: ['./tirage.component.css']
})
export class TirageComponent {
  loading = false;
  currentTirage: Tirage | null = null;

  constructor(
    private authService: AuthService,
    private tirageService: TirageService,
    private router: Router,
    private interpretationService: InterpretationService
  ) {}

  // 🔹 Lancer le tirage
  startTirage(): void {
    const currentUser = this.authService.getCurrentUser();

    if (!currentUser) {
      console.error('Aucun utilisateur courant trouvé. Veuillez vous reconnecter.');
      this.router.navigate(['/login']);
      return;
    }

    this.createTirage(currentUser);
  }

  // 🔹 Créer le tirage
  private createTirage(user: User): void {
    this.loading = true;

    this.tirageService.effectuerTirage(user.id).subscribe({
      next: (tirage) => {
        this.currentTirage = tirage;

        if (tirage.figures && tirage.figures.length > 0) {
          const firstFigure = tirage.figures[0];

          // ✅ Créer l'objet request attendu par le service
          const request: InterpretationRequest = {
            ramliId: firstFigure.id,
            tirageId: tirage.id
          };

          this.interpretationService.generateInterpretation(request).subscribe({
            next: (interp) => {
              this.currentTirage!.interpretation = interp;
            },
            error: (err) => console.error('Erreur interprétation:', err)
          });
        }

        this.loading = false;
      },
      error: (error) => {
        this.loading = false;
        console.error('Erreur lors du tirage :', error);
      }
    });
  }

  // 🔹 Convertir les valeurs en points
  getDots(valeurs: string): boolean[] {
    return valeurs.split(' ').map(char => char === '•');
  }

  // 🔹 Réinitialiser le tirage
  newTirage(): void {
    this.currentTirage = null;
  }

  // 🔹 Sauvegarder dans l'historique
  saveToHistory(): void {
    this.router.navigate(['/historique']);
  }
}
