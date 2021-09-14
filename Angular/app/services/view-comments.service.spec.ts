import { TestBed } from '@angular/core/testing';

import { ViewCommentsService } from './view-comments.service';

describe('ViewCommentsService', () => {
  let service: ViewCommentsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ViewCommentsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
