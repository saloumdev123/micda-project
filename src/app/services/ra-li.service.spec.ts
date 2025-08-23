import { TestBed } from '@angular/core/testing';

import { RaLiService } from './ra-li.service';

describe('RaLiService', () => {
  let service: RaLiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RaLiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
