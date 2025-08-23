import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { User } from '../../modele/user';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})

export class ProfileComponent {
  currentUser: User | null = null;
  formData = {
    prenom: '',
    nom: '',
    email: ''
  };
  passwordData = {
    currentPassword: '',
    newPassword: '',
    confirmPassword: ''
  };
  preferences = {
    emailNotifications: true,
    autoSave: true,
    darkMode: false
  };

  constructor(private authService: AuthService) {
    this.currentUser = this.authService.getCurrentUser();
    if (this.currentUser) {
      this.formData = {
        prenom: this.currentUser.prenom,
        nom: this.currentUser.nom,
        email: this.currentUser.email
      };
    }
  }

  getUserInitials(): string {
    if (this.currentUser) {
      return (this.currentUser.prenom.charAt(0) + this.currentUser.nom.charAt(0)).toUpperCase();
    }
    return '';
  }

  getRoleLabel(): string {
    const roleLabels = {
      'ADMIN': 'Administrateur',
      'CLIENT': 'Client',
      'DEVIN': 'Praticien'
    };
    return this.currentUser ? roleLabels[this.currentUser.role] : '';
  }

  updateProfile(): void {
    if (this.passwordData.currentPassword && 
        this.passwordData.newPassword !== this.passwordData.confirmPassword) {
      alert('Les nouveaux mots de passe ne correspondent pas');
      return;
    }

    console.log('Profile update:', this.formData);
    console.log('Password update:', this.passwordData);
    // Here you would call your API to update the profile
    alert('Profil mis à jour avec succès!');
  }

  resetForm(): void {
    if (this.currentUser) {
      this.formData = {
        prenom: this.currentUser.prenom,
        nom: this.currentUser.nom,
        email: this.currentUser.email
      };
    }
    this.passwordData = {
      currentPassword: '',
      newPassword: '',
      confirmPassword: ''
    };
  }

  isFormValid(): boolean {
    return !!(this.formData.prenom && this.formData.nom && this.formData.email);
  }

  savePreferences(): void {
    console.log('Preferences saved:', this.preferences);
    alert('Préférences sauvegardées!');
  }
}
