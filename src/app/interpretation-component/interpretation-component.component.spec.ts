import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InterpretationComponentComponent } from './interpretation-component.component';

describe('InterpretationComponentComponent', () => {
  let component: InterpretationComponentComponent;
  let fixture: ComponentFixture<InterpretationComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InterpretationComponentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InterpretationComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
