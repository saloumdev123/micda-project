import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'] 
})

export class LoginComponent {
   email = '';
  password = '';
  loading = false;

  // ⚡ Pour afficher le formulaire forgot password
  showForgotForm = false;
  forgotEmail = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  onSubmit(): void {
    if (this.email && this.password) {
      this.loading = true;
      this.authService.login({ email: this.email, password: this.password }).subscribe({
        next: (response) => {
          this.loading = false;
          this.router.navigate(['/dashboard']);
        },
        error: (error) => {
          this.loading = false;
          console.error('Login error:', error);
          window.alert('Login failed. Check your credentials.');
        }
      });
    }
  }

  navigateToRegister(): void {
    this.router.navigate(['/register']);
  }

  // ⚡ Afficher le formulaire forgot password
  onForgotPassword(): void {
    this.showForgotForm = true;
    this.forgotEmail = '';
  }

  // ⚡ Soumettre le formulaire forgot password
  submitForgotPassword(): void {
    if (!this.forgotEmail) {
      window.alert('Please enter your email.');
      return;
    }

    this.authService.forgotPassword({ email: this.forgotEmail }).subscribe({
      next: (response) => {
        window.alert('A password reset link has been sent to your email.');
        this.showForgotForm = false; // cacher le formulaire
        this.forgotEmail = ''; // vider le champ
      },
      error: (error) => {
        console.error('Forgot password error:', error);
        window.alert('Error sending reset link. Please try again.');
      }
    });
  }
}
