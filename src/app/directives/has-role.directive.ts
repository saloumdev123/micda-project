// src/app/core/directives/has-role.directive.ts
import { Directive, Input, TemplateRef, ViewContainerRef } from '@angular/core';
import { Role } from '../enums/role';
import { AuthService } from '../services/auth.service';

@Directive({
  selector: '[appHasRole]',
  standalone: true
})
export class HasRoleDirective {
  private allowedRoles: Role[] = [];

  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef,
    private auth: AuthService
  ) {}

  @Input()
  set appHasRole(role: Role | Role[]) {
    // On accepte soit un seul rôle, soit un tableau
    this.allowedRoles = Array.isArray(role) ? role : [role];
    this.updateView();
  }

  private updateView() {
    const userRole = this.auth.getRole();
    this.viewContainer.clear();
    if (userRole && this.allowedRoles.includes(userRole)) {
      this.viewContainer.createEmbeddedView(this.templateRef);
    }
  }
}
