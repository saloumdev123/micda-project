import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Subscription } from '../interfaces/subscription';

@Injectable({
  providedIn: 'root',
})
export class SubscrberService {
  private apiUrl = 'http://localhost:8080/api/subscribers';

  private http = inject(HttpClient);
  constructor() {}

  createSubscription(subscription: Subscription): Observable<Subscription> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Subscription>(this.apiUrl, subscription, { headers });
  }
}
