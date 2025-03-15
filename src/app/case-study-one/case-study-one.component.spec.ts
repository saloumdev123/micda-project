import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CaseStudyOneComponent } from './case-study-one.component';

describe('CaseStudyOneComponent', () => {
  let component: CaseStudyOneComponent;
  let fixture: ComponentFixture<CaseStudyOneComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CaseStudyOneComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CaseStudyOneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
