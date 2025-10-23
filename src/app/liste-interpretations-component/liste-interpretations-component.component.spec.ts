import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeInterpretationsComponentComponent } from './liste-interpretations-component.component';

describe('ListeInterpretationsComponentComponent', () => {
  let component: ListeInterpretationsComponentComponent;
  let fixture: ComponentFixture<ListeInterpretationsComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListeInterpretationsComponentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ListeInterpretationsComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
