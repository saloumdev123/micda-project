import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { ForgotPasswordRequest, LoginRequest } from '../../modele/authResponse';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'] 
})
export class LoginComponent {
credentials: LoginRequest = { email: '', password: '' };
  forgotEmail: string = '';
  showForgotPassword: boolean = false;
  isLoading = false;
  loginError: string | null = null;
  showPassword: boolean = false;

  constructor(private authService: AuthService, private router: Router) {}
 

  toggleForgotPassword(event: Event): void {
    event.preventDefault();
    this.showForgotPassword = !this.showForgotPassword;
    this.loginError = null;
    this.isLoading = false;
  }

  onSubmit(): void {
    this.isLoading = true;
    this.loginError = null;

    this.authService.login(this.credentials).subscribe({
      next: () => {
        this.isLoading = false;
        this.router.navigate(['/home']);
      },
      error: (err: HttpErrorResponse) => {
        this.isLoading = false;
        if (err.status === 401) this.loginError = "Email ou mot de passe incorrect.";
        else if (err.status === 404) this.loginError = "Utilisateur introuvable.";
        else this.loginError = "Une erreur est survenue. Veuillez réessayer.";
      }
    });
  }

  onForgotPassword(): void {
    this.isLoading = true;

    const request: ForgotPasswordRequest = { email: this.forgotEmail };
    this.authService.forgotPassword(request).subscribe({
      next: () => {
        alert('Lien de réinitialisation envoyé ! Vérifiez votre email.');
        this.isLoading = false;
        this.toggleForgotPassword(new Event('click'));
      },
      error: () => {
        alert('Erreur lors de l\'envoi du lien.');
        this.isLoading = false;
      }
    });
  }

    togglePassword(): void {
  this.showPassword = !this.showPassword;
  }
  navigateToRegister(): void {
  this.router.navigate(['/register']);
}

}
