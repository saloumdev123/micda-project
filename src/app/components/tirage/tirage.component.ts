import { Component } from '@angular/core';
import { Tirage } from '../../modele/tirage';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { TirageService } from '../../services/tirage.service';
 

@Component({
  selector: 'app-tirage',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './tirage.component.html',
  styleUrl: './tirage.component.css'
})
export class TirageComponent {
  loading = false;
  currentTirage: Tirage | null = null;

  constructor(
    private authService: AuthService,
     private tirageService: TirageService, 
    private router: Router
  ) {}

  startTirage(): void {
    const currentUser = this.authService.getCurrentUser();
    if (!currentUser) {
    this.router.navigate(['/login']);
      return;
    }

    this.loading = true;
    this.tirageService.effectuerTirage(currentUser.id).subscribe({
      next: (tirage) => {
        this.loading = false;
        this.currentTirage = tirage;
      },
      error: (error) => {
        this.loading = false;
        console.error('Tirage error:', error);
      }
    });
  }

  getDots(valeurs: string): boolean[] {
    return valeurs.split('').map(char => char === '•');
  }

  newTirage(): void {
    this.currentTirage = null;
  }

  saveToHistory(): void {
    // The tirage is already saved in the service
    this.router.navigate(['/historique']);
  }
}
