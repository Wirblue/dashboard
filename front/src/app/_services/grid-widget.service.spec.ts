import { TestBed } from '@angular/core/testing';

import { GridWidgetService } from './grid-widget.service';

describe('GridWidgetService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GridWidgetService = TestBed.get(GridWidgetService);
    expect(service).toBeTruthy();
  });
});
