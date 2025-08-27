import { Component } from '@angular/core';
import { bootstrapApplication } from '@angular/platform-browser';
import { RouterOutlet, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './app/components/header/header.component';
import { AuthService } from './app/services/auth.service';
import { appConfig } from './app/app.config';  // ✅ importe ton appConfig

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, HeaderComponent],
  template: `
    <div class="app-container mystical-pattern">
      <app-header *ngIf="showHeader"></app-header>
      <main class="main-content">
        <router-outlet></router-outlet>
      </main>
    </div>
  `,
  styles: [`
    .app-container {
      min-height: 100vh;
    }
    .main-content {
      position: relative;
    }
  `]
})
export class App {
  showHeader = false;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {
    this.authService.isAuthenticated$.subscribe(isAuthenticated => {
      this.showHeader = isAuthenticated;
      if (!isAuthenticated && !this.isPublicRoute()) {
        this.router.navigate(['/login']);
      }
    });

    this.router.events.subscribe(() => {
      this.updateHeaderVisibility();
    });
  }

  private isPublicRoute(): boolean {
    const currentUrl = this.router.url;
    return currentUrl === '/login' || currentUrl === '/register';
  }

  private updateHeaderVisibility(): void {
    const isAuthenticated = this.authService.isAuthenticated();
    const isPublicRoute = this.isPublicRoute();
    this.showHeader = isAuthenticated && !isPublicRoute;
  }
}

// ✅ ici on passe appConfig
bootstrapApplication(App, appConfig)
  .catch(err => console.error(err));
