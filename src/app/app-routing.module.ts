import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'sagas',
    pathMatch: 'full'
  },
  {
    path: 'sagas',
    loadChildren: () => import('./pages/sagas/list-sagas/list-sagas.module').then( m => m.ListSagasPageModule)
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
