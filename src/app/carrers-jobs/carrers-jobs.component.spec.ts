import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarrersJobsComponent } from './carrers-jobs.component';

describe('CarrersJobsComponent', () => {
  let component: CarrersJobsComponent;
  let fixture: ComponentFixture<CarrersJobsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CarrersJobsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CarrersJobsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
