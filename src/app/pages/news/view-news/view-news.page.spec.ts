import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { ViewNewsPage } from './view-news.page';

describe('ViewNewsPage', () => {
  let component: ViewNewsPage;
  let fixture: ComponentFixture<ViewNewsPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewNewsPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(ViewNewsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
