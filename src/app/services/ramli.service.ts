import { Injectable } from '@angular/core';
import { delay, Observable, of } from 'rxjs';
import { Ramli } from '../modele/ramli';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RamliService {
private apiUrl = 'http://localhost:22000/api/ramlis'; 

  constructor(private http: HttpClient) {}

  // 🔹 Récupérer toutes les figures
  getFigures(): Observable<Ramli[]> {
    return this.http.get<Ramli[]>(this.apiUrl);
  }

  // 🔹 Récupérer une figure par ID
  getFigureById(id: number): Observable<Ramli> {
    return this.http.get<Ramli>(`${this.apiUrl}/${id}`);
  }

  // 🔹 Créer une nouvelle figure
  createFigure(figure: Ramli): Observable<Ramli> {
    return this.http.post<Ramli>(this.apiUrl, figure);
  }

  // 🔹 Mettre à jour une figure
  updateFigure(id: number, figure: Ramli): Observable<Ramli> {
    return this.http.put<Ramli>(`${this.apiUrl}/${id}`, figure);
  }

  // 🔹 Supprimer une figure
  deleteFigure(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  // 🔹 Récupérer une figure aléatoire
  getRandomFigure(): Observable<Ramli> {
    return this.http.get<Ramli>(`${this.apiUrl}/random`);
  }
}
