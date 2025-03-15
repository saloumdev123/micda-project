import { Injectable } from '@angular/core';
import { JobApplication } from '../interfaces/job-application';
import { Observable, catchError, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class JobApplicationService {
  private apiUrl = 'http://localhost:8080/api/jobApplications';

  constructor(private http: HttpClient) {}

  create(jobApplication: JobApplication): Observable<JobApplication> {
    return this.http
      .post<JobApplication>(this.apiUrl, jobApplication)
      .pipe(catchError(this.handleError<JobApplication>('create')));
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      console.error(error);
      return of(result as T);
    };
  }
}
