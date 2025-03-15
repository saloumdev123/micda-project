import { Job } from './job';

export interface Subscription {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  message: string;
  job: Job;
}
