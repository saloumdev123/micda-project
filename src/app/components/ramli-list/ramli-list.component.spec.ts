import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RamliListComponent } from './ramli-list.component';

describe('RamliListComponent', () => {
  let component: RamliListComponent;
  let fixture: ComponentFixture<RamliListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RamliListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RamliListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
