import { Injectable } from '@angular/core';
import { Tirage } from '../modele/tirage';
import { Observable} from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TirageService {

  private apiUrl = 'http://localhost:22000/api/tirages'; // adapte selon ton backend

  constructor(private http: HttpClient) {}

  // 🔹 Effectuer un nouveau tirage pour un utilisateur
  effectuerTirage(userId: number): Observable<Tirage> {
    return this.http.post<Tirage>(`${this.apiUrl}/user/${userId}/generate`, {});
  }

  // 🔹 Récupérer tous les tirages d’un utilisateur
  getTiragesUtilisateur(userId: number): Observable<Tirage[]> {
    return this.http.get<Tirage[]>(`${this.apiUrl}/user/${userId}`);
  }

  // 🔹 Récupérer un tirage spécifique par son ID
  getTirageById(tirageId: number): Observable<Tirage> {
    return this.http.get<Tirage>(`${this.apiUrl}/${tirageId}`);
  }

  // 🔹 Récupérer tous les tirages (optionnel)
  getAllTirages(): Observable<Tirage[]> {
    return this.http.get<Tirage[]>(`${this.apiUrl}/all`);
  }
}
