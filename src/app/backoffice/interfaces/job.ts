import { JobApplication } from './job-application';

export interface Job {
  id: number;
  title: string;
  description: string;
  employmentType: string;
  location: string;
  jobType: string;
  yearExperience: number;
  datePosted: string;
  jobApplication: JobApplication[];
  pay: string;
  shiftSchedule: string;
  requirements: string;
  fullDescription: string;
  profileInsight: boolean;
  job: null;
}
