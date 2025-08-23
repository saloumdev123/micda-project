import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { TirageComponent } from './components/tirage/tirage.component';
import { HistoriqueComponent } from './components/historique/historique.component';
import { ProfileComponent } from './components/profile/profile.component';

export const routes: Routes = [

      { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'tirage', component: TirageComponent },
  { path: 'historique', component: HistoriqueComponent },
  { path: 'profile', component: ProfileComponent },
  { path: '**', redirectTo: '/dashboard' }
];
