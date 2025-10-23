import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { Role } from '../../enums/role';
export const roleGuard: CanActivateFn = (route, state) => {


  const requiredRoles = (route.data?.['roles'] as string[]).map(r => r as Role);

  const authService = inject(AuthService);
  const router = inject(Router);

  
  if (!requiredRoles || requiredRoles.length === 0) {
    return true;
  }

  if (authService.hasAnyRole(requiredRoles)) {
    return true;
  }

  router.navigate(['/dashboard']);
  return false;
};