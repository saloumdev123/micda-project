import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { SubscrberService } from '../../services/subscrber.service';
import { Subscription } from '../../interfaces/subscription';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-subscription',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './subscription.component.html',
  styleUrl: './subscription.component.css',
})
export class SubscriptionComponent {
  subscriptionForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private subscriptionService: SubscrberService
  ) {
    this.subscriptionForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      message: ['', Validators.required],
    });
  }
  createSubscription(): void {
    console.log(this.subscriptionForm.value);
    if (this.subscriptionForm.valid) {
      console.log(this.subscriptionForm.value);
      const newSubscription: Subscription = this.subscriptionForm.value;
      this.subscriptionService.createSubscription(newSubscription).subscribe({
        next: () => {
          this.subscriptionForm.reset();
        },
        error: (error) => {
          console.error('Error when subscribing:', error);
        },
      });
    }
  }
}
