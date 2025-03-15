import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleRoomComponent } from './article-room.component';

describe('ArticleRoomComponent', () => {
  let component: ArticleRoomComponent;
  let fixture: ComponentFixture<ArticleRoomComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ArticleRoomComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ArticleRoomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
