import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ResetPasswordRequest } from '../../modele/resetPasswordRequest ';

@Component({
  selector: 'app-reset-password',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css'] 
})
export class ResetPasswordComponent {
 token = '';
  newPassword = '';
  confirmPassword = '';
  loading = false;

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private router: Router
  ) {
    // 🔹 Récupérer le token depuis l’URL
    this.route.queryParams.subscribe(params => {
      this.token = params['token'] || '';
    });
  }

  onSubmit(): void {
    if (!this.newPassword || !this.confirmPassword) {
      window.alert('Please fill all fields.');
      return;
    }

    if (this.newPassword !== this.confirmPassword) {
      window.alert('Passwords do not match.');
      return;
    }

    this.loading = true;

    // 🔹 Construire l'objet ResetPasswordRequest
    const request: ResetPasswordRequest = {
      token: this.token,
      newPassword: this.newPassword
    };

    this.authService.resetPassword(request).subscribe({
      next: (response: any) => {
        this.loading = false;
        // 🔹 Affiche le message du backend
        window.alert(response.message || 'Password reset successfully! Please login.');
        // 🔹 Redirection vers la page login
        this.router.navigate(['/login']);
      },
      error: (err) => {
        this.loading = false;
        console.error('Reset password error:', err);
        window.alert('Failed to reset password. Token may be invalid or expired.');
      }
    });
  }
}
