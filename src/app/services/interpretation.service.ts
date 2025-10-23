import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Interpretation } from '../modele/interpretation';
import { InterpretationRequest } from '../modele/interpretationRequest';

@Injectable({
  providedIn: 'root'
})
export class InterpretationService {
 private apiUrl = 'http://localhost:22000/api/interpretations';

  constructor(private http: HttpClient) {}

  /**
   * 🔹 Génère une interprétation à partir d’un Ramli (et éventuellement d’un Tirage)
   */
  generateInterpretation(request: InterpretationRequest): Observable<Interpretation> {
    const token = localStorage.getItem('accessToken');

    if (!token) {
      throw new Error('Token manquant. Veuillez vous reconnecter.');
    }

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    return this.http.post<Interpretation>(`${this.apiUrl}/generate`, request, { headers });
  }

  /**
   * 🔹 Récupère toutes les interprétations disponibles
   */
  getAllInterpretations(): Observable<Interpretation[]> {
    const token = localStorage.getItem('accessToken');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token || ''}`,
      'Content-Type': 'application/json'
    });

    return this.http.get<Interpretation[]>(this.apiUrl, { headers });
  }

}
