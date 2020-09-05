import { TestBed } from '@angular/core/testing';

import { RssMessageService } from './rss.message.service';

describe('RssMessageService', () => {
  let service: RssMessageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RssMessageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
