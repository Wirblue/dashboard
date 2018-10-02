import { TestBed } from '@angular/core/testing';

import { DescService } from './desc.service';

describe('DescService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DescService = TestBed.get(DescService);
    expect(service).toBeTruthy();
  });
});
