import { Injectable, inject } from '@angular/core';
import { Resource } from '../interfaces/resource';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ResourceService {
  private apiUrl = 'http://localhost:8080/api/resources';
  resources: Resource[] = [];

  constructor(private http: HttpClient) {}
  getResources(): Observable<Resource[]> {
    return this.http.get<Resource[]>(this.apiUrl);
  }

  getResourceById(id: number): Observable<Resource> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Resource>(url);
  }
}
