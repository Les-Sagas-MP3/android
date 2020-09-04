import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ListSagasPage } from './list-sagas.page';

const routes: Routes = [
  {
    path: '',
    component: ListSagasPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ListSagasPageRoutingModule {}
