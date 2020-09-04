import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { ListSagasPage } from './list-sagas.page';

describe('ListSagasPage', () => {
  let component: ListSagasPage;
  let fixture: ComponentFixture<ListSagasPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListSagasPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(ListSagasPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
