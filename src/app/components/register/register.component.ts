import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent {
userData = {
    prenom: '',
    nom: '',
    email: '',
    password: ''
  };
  confirmPassword = '';
  loading = false;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  onSubmit(): void {
    if (this.userData.password !== this.confirmPassword) {
      window.alert('Passwords do not match');
      return;
    }

    if (this.userData.prenom && this.userData.nom && this.userData.email && this.userData.password) {
      this.loading = true;
      this.authService.register(this.userData).subscribe({
      next: (response) => {
        this.loading = false;
        window.alert('User successfully created!');
        this.router.navigate(['/login']);
      },
      error: (error) => {
        this.loading = false;
        window.alert('Registration failed. Please try again.');
      }

      });
    }
  }

  navigateToLogin(): void {
    this.router.navigate(['/login']);
  }
}