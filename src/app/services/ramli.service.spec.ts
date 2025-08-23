import { TestBed } from '@angular/core/testing';

import { RamliService } from './ramli.service';

describe('RamliService', () => {
  let service: RamliService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RamliService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
