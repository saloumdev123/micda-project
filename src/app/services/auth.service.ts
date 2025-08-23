import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { User } from '../modele/user';
import { AuthResponse } from '../modele/auth-response';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUserSubject = new BehaviorSubject<User | null>(null);
  public currentUser$ = this.currentUserSubject.asObservable();
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);
  public isAuthenticated$ = this.isAuthenticatedSubject.asObservable();

  constructor() {
    // Check for stored user on service initialization
    const storedUser = localStorage.getItem('currentUser');
    if (storedUser) {
      const user = JSON.parse(storedUser);
      this.currentUserSubject.next(user);
      this.isAuthenticatedSubject.next(true);
    }
  }

  login(email: string, password: string): Observable<AuthResponse> {
    // Mock authentication - replace with actual API call
    const mockUser: User = {
      id: 1,
      nom: 'Doe',
      prenom: 'John',
      email: email,
      role: 'CLIENT',
      dateInscription: new Date()
    };

    const response: AuthResponse = {
      token: 'mock-jwt-token',
      user: mockUser
    };

    localStorage.setItem('currentUser', JSON.stringify(mockUser));
    localStorage.setItem('token', response.token);
    this.currentUserSubject.next(mockUser);
    this.isAuthenticatedSubject.next(true);

    return of(response);
  }

  register(userData: any): Observable<AuthResponse> {
    // Mock registration - replace with actual API call
    const mockUser: User = {
      id: Date.now(),
      nom: userData.nom,
      prenom: userData.prenom,
      email: userData.email,
      role: 'CLIENT',
      dateInscription: new Date()
    };

    const response: AuthResponse = {
      token: 'mock-jwt-token',
      user: mockUser
    };

    localStorage.setItem('currentUser', JSON.stringify(mockUser));
    localStorage.setItem('token', response.token);
    this.currentUserSubject.next(mockUser);
    this.isAuthenticatedSubject.next(true);

    return of(response);
  }

  logout(): void {
    localStorage.removeItem('currentUser');
    localStorage.removeItem('token');
    this.currentUserSubject.next(null);
    this.isAuthenticatedSubject.next(false);
  }

  getCurrentUser(): User | null {
    return this.currentUserSubject.value;
  }

  isAuthenticated(): boolean {
    return this.isAuthenticatedSubject.value;
  }
}