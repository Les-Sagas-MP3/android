import { Component, OnInit } from '@angular/core';
import { LoadingController } from '@ionic/angular';
import { SagaModel } from 'src/app/models/saga.model';
import { SagaService } from 'src/app/services/sagas/saga.service';
import { forkJoin } from 'rxjs';
import { CategoryModel } from 'src/app/models/category.model';
import { CategoryService } from 'src/app/services/categories/category.service';

@Component({
  selector: 'app-list-sagas',
  templateUrl: './list-sagas.page.html',
  styleUrls: ['./list-sagas.page.scss'],
})
export class ListSagasPage implements OnInit {

  public items: any[] = [];

  constructor(
    public loadingController: LoadingController,
    private categoryService: CategoryService,
    private sagaService: SagaService) { }

  ngOnInit() {
      this.loadingController.create({
        message: 'Téléchargement...'
      }).then((res) => {
        res.present();
        forkJoin(this.categoryService.getAll(), this.sagaService.getAll())
        .subscribe(results => {
          this.items = results[1];
          this.items.forEach(item => {
            item.categories = []
            item.categoriesRef.forEach(categoryRef => {
              item.categories.push(results[0].find(result => result.id === categoryRef));
            });
          });
          res.dismiss();
        });
      });

  }

}
