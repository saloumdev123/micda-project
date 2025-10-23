import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ligne } from '../modele/ligne';

@Injectable({
  providedIn: 'root'
})
export class LigneService {
  private apiUrl = 'http://localhost:22000/api/figure-lignes';

  constructor(private http: HttpClient) {}

  createLigne(ligne: Ligne, figureId: number): Observable<Ligne> {
    return this.http.post<Ligne>(`${this.apiUrl}/create/${figureId}`, ligne);
  }

  getByFigureId(figureId: number): Observable<Ligne[]> {
    return this.http.get<Ligne[]>(`${this.apiUrl}/by-figure/${figureId}`);
  }

  updateLigne(id: number, ligne: Ligne): Observable<Ligne> {
    return this.http.put<Ligne>(`${this.apiUrl}/${id}`, ligne);
  }

  deleteLigne(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
