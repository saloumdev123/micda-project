import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TirageComponent } from './tirage.component';

describe('TirageComponent', () => {
  let component: TirageComponent;
  let fixture: ComponentFixture<TirageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TirageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TirageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
