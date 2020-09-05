import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LoadingController } from '@ionic/angular';
import { RssMessageService } from 'src/app/services/rss.message/rss.message.service';
import { RssMessageModel } from 'src/app/models/rss.message.model';

@Component({
  selector: 'app-view-news',
  templateUrl: './view-news.page.html',
  styleUrls: ['./view-news.page.scss'],
})
export class ViewNewsPage implements OnInit {

  public item: RssMessageModel = new RssMessageModel();

  constructor(
    private activatedRoute: ActivatedRoute,
    public loadingController: LoadingController,
    private rssMessageService: RssMessageService) { }

  ngOnInit() {
    var itemId: number = +this.activatedRoute.snapshot.paramMap.get('id');
    this.loadingController.create({
      message: 'Téléchargement...'
    }).then((res) => {
      res.present();
      this.rssMessageService.getById(itemId)
        .subscribe(data => {
          this.item = data;
          res.dismiss();
        });
    });
  }

}
