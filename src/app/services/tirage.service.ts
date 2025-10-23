import { Injectable } from '@angular/core';
import { Tirage } from '../modele/tirage';
import { catchError, map, Observable, throwError} from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { binaryToSymbols } from '../utils/ramli.utis';


@Injectable({
  providedIn: 'root'
})
export class TirageService {

 

   private apiUrl = 'http://localhost:22000/api/tirages';

  constructor(private http: HttpClient) {}

  // 🔹 Récupérer tous les tirages d’un utilisateur
  getTiragesUtilisateur(userId: number): Observable<Tirage[]> {
    return this.http.get<Tirage[]>(`${this.apiUrl}/user/${userId}`).pipe(
      map((tirages: Tirage[]) =>
        tirages.map(t => ({
          ...t,
          figures: t.figures?.map(fig => ({
            ...fig,
            lignes: fig.lignes?.map(l => ({
              ...l,
              valeurs: binaryToSymbols(l.valeurs) // ✅ conversion ici
            })) || []
          })) || []
        }))
      ),
      catchError(this.handleError)
    );
  }

  // 🔹 Récupérer un tirage spécifique par son ID
  getTirageById(tirageId: number): Observable<Tirage> {
    return this.http.get<Tirage>(`${this.apiUrl}/${tirageId}`).pipe(
      map(this.mapTirage),
      catchError(this.handleError)
    );
  }

  // 🔹 Récupérer tous les tirages
  getAllTirages(): Observable<Tirage[]> {
    return this.http.get<Tirage[]>(`${this.apiUrl}/all`).pipe(
      map((tirages: Tirage[]) =>
        tirages.map(t => ({
          ...t,
          figures: t.figures?.map(fig => ({
            ...fig,
            lignes: fig.lignes?.map(l => ({
              ...l,
              valeurs: binaryToSymbols(l.valeurs) // ✅ conversion pour tous
            })) || []
          })) || []
        }))
      ),
      catchError(this.handleError)
    );
  }

  // 🔹 Effectuer un tirage pour un utilisateur
  effectuerTirage(userId: number): Observable<Tirage> {
    const token = localStorage.getItem('accessToken');

    if (!token) {
      console.error('JWT manquant. Veuillez vous reconnecter.');
      return throwError(() => new Error('JWT manquant'));
    }

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    return this.http.post<Tirage>(`${this.apiUrl}/user/${userId}/generate`, {}, { headers }).pipe(
      map(this.mapTirage),
      catchError(this.handleError)
    );
  }

  // ✅ Générer une liste de points (1 = •, 0 = ○)
  genererPoints(nb: number): Observable<number[]> {
    return this.http.get<number[]>(`${this.apiUrl}/generer/${nb}`).pipe(
      catchError(this.handleError)
    );
  }

  // 🔹 Conversion binaire → symboles pour un seul tirage
  private mapTirage = (tirage: Tirage): Tirage => {
    if (tirage?.figures) {
      tirage.figures = tirage.figures.map(fig => ({
        ...fig,
        lignes: fig.lignes?.map(ligne => ({
          ...ligne,
          valeurs: binaryToSymbols(ligne.valeurs)
        })) || []
      }));
    }
    return tirage;
  };

  // 🔹 Gestion des erreurs HTTP
  private handleError(error: any): Observable<never> {
    console.error('Erreur HTTP:', error);
    let errorMessage = 'Une erreur inconnue est survenue.';

    if (error.status === 0) {
      errorMessage = 'Impossible de se connecter au serveur.';
    } else if (error.status === 404) {
      errorMessage = 'La ressource demandée est introuvable.';
    } else if (error.status === 401) {
      errorMessage = 'Authentification requise. Veuillez vous reconnecter.';
    } else if (error.error?.message) {
      errorMessage = error.error.message;
    }

    return throwError(() => new Error(errorMessage));
  }
}
