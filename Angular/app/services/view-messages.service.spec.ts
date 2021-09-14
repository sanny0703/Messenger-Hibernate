import { TestBed } from '@angular/core/testing';

import { ViewMessagesService } from './view-messages.service';

describe('ViewMessagesService', () => {
  let service: ViewMessagesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ViewMessagesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
