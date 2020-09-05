import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'news',
    pathMatch: 'full'
  },
  {
    path: 'sagas',
    loadChildren: () => import('./pages/sagas/list-sagas/list-sagas.module').then( m => m.ListSagasPageModule)
  },
  {
    path: 'news',
    loadChildren: () => import('./pages/news/list-news/list-news.module').then( m => m.ListNewsPageModule)
  },
  {
    path: 'news/:id',
    loadChildren: () => import('./pages/news/view-news/view-news.module').then( m => m.ViewNewsPageModule)
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
