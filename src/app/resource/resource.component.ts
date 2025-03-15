import { Component, inject } from '@angular/core';
import { Resource } from '../backoffice/interfaces/resource';
import { ResourceService } from '../backoffice/services/resource.service';
import { Router } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-resource',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './resource.component.html',
  //: './resource.component.css'
})
export class ResourceComponent {
  resources: Resource [] = []; 

  private resourceService = inject(ResourceService)
  constructor(private router: Router) { }

  ngOnInit(): void {
    this.getResources();
  }

  getResources(): void {
    this.resourceService.getResources().subscribe(
      (resources: Resource[]) => {
        this.resources = resources;
      },
      (error) => {
       console.error('Error fetching resources:', error);
      }
    );
  }
}
