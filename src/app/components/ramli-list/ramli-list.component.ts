import { Component } from '@angular/core';
import { RamliService } from '../../services/ramli.service';
import { Ramli } from '../../modele/ramli';
import { CommonModule } from '@angular/common';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-ramli-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './ramli-list.component.html',
  styleUrl: './ramli-list.component.css'
})
export class RamliListComponent {
 figures: Ramli[] = [];
  randomFigure?: Ramli;

  constructor(private ramliService: RamliService) {}

  ngOnInit(): void {
  forkJoin({
    figures: this.ramliService.getFigures(),
    random: this.ramliService.getRandomFigure()
  }).subscribe({
    next: (result) => {
      this.figures = result.figures;
      this.randomFigure = result.random;
    },
    error: (err) => console.error("Erreur lors du chargement des données:", err)
  });
}}

