import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LigneService {

  private apiUrl = 'http://localhost:22000/api/figure-lignes';

  constructor(private http: HttpClient) {}

  createLigne(ligne: LigneService, figureId: number): Observable<LigneService> {
    return this.http.post<LigneService>(`${this.apiUrl}/create/${figureId}`, ligne);
  }

  getByFigureId(figureId: number): Observable<LigneService[]> {
    return this.http.get<LigneService[]>(`${this.apiUrl}/by-figure/${figureId}`);
  }

  updateLigne(id: number, ligne: LigneService): Observable<LigneService> {
    return this.http.put<LigneService>(`${this.apiUrl}/${id}`, ligne);
  }

  deleteLigne(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
