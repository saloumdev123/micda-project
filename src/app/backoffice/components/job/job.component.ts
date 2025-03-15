import { Component, inject } from '@angular/core';
import { Job } from '../../interfaces/job';
import { JobService } from '../../services/job.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { JobsviewComponent } from '../../../jobsview/jobsview.component';

@Component({
  selector: 'app-job',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, FormsModule, JobsviewComponent],
  templateUrl: './job.component.html',
  styleUrl: './job.component.css',
})
export class JobComponent {
  private joService = inject(JobService);

  jobs: Job[] = [];
  selectedJob: Job | undefined;

  filterLocation: string = '';

  filterCriteria: string = 'name'; // Default filter criteria
  filterTerm: string = '';
  filteredJobs(): any[] {
    if (!this.filterLocation) {
      return this.jobs;
    } else {
      return this.jobs.filter((job) =>
        job.location.toLowerCase().includes(this.filterLocation.toLowerCase())
      );
    }
  }
  filteredJobsByCriteria() {
    if (this.filterCriteria === 'name') {
      return this.jobs.filter((job) =>
        job.title.toLowerCase().includes(this.filterTerm.toLowerCase())
      );
    } else if (this.filterCriteria === 'location') {
      return this.jobs.filter((job) =>
        job.location.toLowerCase().includes(this.filterTerm.toLowerCase())
      );
    }
    return [];
  }
  constructor(private jobService: JobService) {}

  ngOnInit(): void {
    this.loadAllJobs();
  }

  loadAllJobs(): void {
    this.jobService.getJobs().subscribe((data) => {
      this.jobs = data;
    });
  }

  loadJobById(id: number): void {
    this.jobService.getJobById(id).subscribe((job) => {
      this.selectedJob = job;
    });
  }
}
