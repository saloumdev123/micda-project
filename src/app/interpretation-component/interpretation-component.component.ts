import { Component } from '@angular/core';
import { InterpretationRequest } from '../modele/interpretationRequest';
import { InterpretationService } from '../services/interpretation.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Interpretation } from '../modele/interpretation';

@Component({
  selector: 'app-interpretation-component',
  standalone: true,
  imports: [CommonModule,RouterModule, ReactiveFormsModule, FormsModule],
  templateUrl: './interpretation-component.component.html',
  styleUrl: './interpretation-component.component.css'
})
export class InterpretationComponentComponent {

  ramliId: number | null = null;
  tirageId: number | null = null;
  interpretationText: string | null = null;
  loading = false;

  constructor(private interpretationService: InterpretationService,private router: Router ) {}

  genererInterpretation(): void {
    if (!this.ramliId) {
      alert('Veuillez entrer l’ID du Ramli.');
      return;
    }

    const request: InterpretationRequest = {
      ramliId: this.ramliId,
      tirageId: this.tirageId || null
    };

    this.loading = true;
    this.interpretationService.generateInterpretation(request).subscribe({
      next: (data: Interpretation) => {
        this.interpretationText = data.texteInterpretation;
        console.log('✅ Interprétation reçue:', data);
        this.loading = false;
      },
      error: (err) => {
        console.error('❌ Erreur lors de la génération:', err);
        this.loading = false;
      }
    });
  }

    goToInterpretationList(): void {
    this.router.navigate(['/interpretations']);
  }
}
