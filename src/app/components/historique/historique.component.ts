import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RamliService } from '../../services/ramli.service';
import { AuthService } from '../../services/auth.service';
import { Tirage } from '../../modele/tirage';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-historique',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './historique.component.html',
  styleUrl: './historique.component.css'
})

export class HistoriqueComponent implements OnInit {
  tirages: Tirage[] = [];

  constructor(
    private authService: AuthService,
    private ramliService: RamliService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const currentUser = this.authService.getCurrentUser();
    if (currentUser) {
      this.ramliService.getTiragesUtilisateur(currentUser.id).subscribe({
        next: (tirages) => {
          this.tirages = tirages;
        }
      });
    }
  }

  navigateToTirage(): void {
    this.router.navigate(['/tirage']);
  }

  viewTirageDetails(tirage: Tirage, event?: Event): void {
    if (event) {
      event.stopPropagation();
    }
    // For now, just navigate to a new tirage - in a real app, you'd show details
    console.log('View tirage details:', tirage);
  }

  repeatTirage(event: Event): void {
    event.stopPropagation();
    this.router.navigate(['/tirage']);
  }

  trackByTirageId(index: number, tirage: Tirage): number {
    return tirage.id;
  }

  getDots(valeurs: string): boolean[] {
    return valeurs.split('').map(char => char === '•');
  }

  getInterpretationPreview(interpretation: string): string {
    return interpretation.length > 120 ? 
      interpretation.substring(0, 120) + '...' : 
      interpretation;
  }

  getUniqueFigures(): number {
    const uniqueFigures = new Set(this.tirages.map(t => t.figures[0]?.nomFigure));
    return uniqueFigures.size;
  }

  getFavoriteDay(): string {
    if (this.tirages.length === 0) return 'N/A';
    
    const dayCount = this.tirages.reduce((acc, tirage) => {
      const day = new Date(tirage.dateTirage).toLocaleDateString('fr-FR', { weekday: 'long' });
      acc[day] = (acc[day] || 0) + 1;
      return acc;
    }, {} as Record<string, number>);

    return Object.keys(dayCount).reduce((a, b) => dayCount[a] > dayCount[b] ? a : b);
  }
}

