import { Component } from '@angular/core';
import { JobApplication } from '../../interfaces/job-application';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { JobApplicationService } from '../../services/job-application.service';
import { Router } from '@angular/router';
import { JobService } from '../../services/job.service';
import { JobsviewComponent } from '../../../jobsview/jobsview.component';

@Component({
  selector: 'app-job-application',
  standalone: true,
  imports: [JobsviewComponent],
  templateUrl: './job-application.component.html',
  styleUrl: './job-application.component.css',
})
export class JobApplicationComponent {
  application: JobApplication = {
    id: 0,
    firstName: '',
    lastName: '',
    email: '',
    country: '',
    city: '',
    job: null,
    jobTitle: '',
    jobDescription: '',
  };

  jobService?: JobService | any;

  jobAppForm: FormGroup | any;

  jobs = ['Developer', 'Designer', 'Manager'];

  ngOnInit(): void {
    this.jobAppForm = new FormGroup({
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      country: new FormControl('', Validators.required),
      city: new FormControl('', Validators.required),
      job: new FormControl('', Validators.required),
    });
  }

  constructor(
    private jobApplicationService: JobApplicationService,
    private fb: FormBuilder,
    private router: Router
  ) {
    this.jobAppForm = this.fb.group({
      id: [null],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      country: ['', Validators.required],
      city: ['', Validators.required],
      job: this.fb.group({
        title: ['', Validators.required],
        description: ['', Validators.required],
      }),
    });
  }

  apply(): void {
    console.log(this.jobAppForm.value);
    if (this.jobAppForm.valid) {
      console.log(this.jobAppForm.value);
      const newJobApplication: JobApplication = this.jobAppForm.value;
      this.jobApplicationService.create(newJobApplication).subscribe({
        next: () => {
          this.jobAppForm.reset();
        },
        error: (error) => {
          console.error('Error when applying to this job:', error);
        },
      });
    }
  }
}
