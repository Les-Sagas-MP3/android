import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ListSagasPageRoutingModule } from './list-sagas-routing.module';

import { ListSagasPage } from './list-sagas.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ListSagasPageRoutingModule
  ],
  declarations: [ListSagasPage]
})
export class ListSagasPageModule {}
