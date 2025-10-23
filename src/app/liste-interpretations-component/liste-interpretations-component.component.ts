import { Component, OnInit } from '@angular/core';
import { InterpretationService } from '../services/interpretation.service';
import { Interpretation } from '../modele/interpretation';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-liste-interpretations-component',
  standalone: true,
  imports: [CommonModule,ReactiveFormsModule, FormsModule, RouterModule],
  templateUrl: './liste-interpretations-component.component.html',
  styleUrls: ['./liste-interpretations-component.component.css']
})
export class ListeInterpretationsComponentComponent  implements OnInit {
 interpretations: Interpretation[] = [];
  loading = true;
  errorMessage: string | null = null;

  constructor(private interpretationService: InterpretationService) {}

  ngOnInit(): void {
    this.loadInterpretations();
  }

  loadInterpretations(): void {
    this.loading = true;
    this.interpretationService.getAllInterpretations().subscribe({
      next: (data) => {
        this.interpretations = data;
        this.loading = false;
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors du chargement des interprétations.';
        console.error('❌ Erreur:', err);
        this.loading = false;
      }
    });
  }
}
