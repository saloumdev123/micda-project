import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-reset-password',
  standalone: true,
  imports: [],
  templateUrl: './reset-password.component.html',
  styleUrl: './reset-password.component.css'
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
    this.authService.resetPassword({ token: this.token, newPassword: this.newPassword }).subscribe({
      next: () => {
        this.loading = false;
        window.alert('Password reset successfully! Please login.');
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
