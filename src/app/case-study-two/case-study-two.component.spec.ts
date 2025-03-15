import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CaseStudyTwoComponent } from './case-study-two.component';

describe('CaseStudyTwoComponent', () => {
  let component: CaseStudyTwoComponent;
  let fixture: ComponentFixture<CaseStudyTwoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CaseStudyTwoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CaseStudyTwoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
