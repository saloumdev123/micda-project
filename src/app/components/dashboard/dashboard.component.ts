import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { User } from '../../modele/user';
import { Tirage } from '../../modele/tirage';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { TirageService } from '../../services/tirage.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']

})


export class DashboardComponent implements OnInit {
  currentUser: User | null = null;
  recentTirages: Tirage[] = [];

  constructor(
    private authService: AuthService,
    private tirageService: TirageService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.currentUser = this.authService.getCurrentUser();
    if (this.currentUser) {
      this.tirageService.getTiragesUtilisateur(this.currentUser.id).subscribe({
        next: (tirages) => {
          this.recentTirages = tirages;
        }
      });
    }
  }

  navigateToTirage(): void {
    this.router.navigate(['/tirage']);
  }

  navigateToHistorique(): void {
    this.router.navigate(['/historique']);
  }

  navigateToProfile(): void {
    this.router.navigate(['/profile']);
  }

  viewTirage(tirageId: number): void {
    this.router.navigate(['/tirage', tirageId]);
  }
}


