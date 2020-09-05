import { Component, OnInit } from '@angular/core';
import { LoadingController } from '@ionic/angular';
import { RssMessageService } from 'src/app/services/rss.message/rss.message.service';

@Component({
  selector: 'app-list-news',
  templateUrl: './list-news.page.html',
  styleUrls: ['./list-news.page.scss'],
})
export class ListNewsPage implements OnInit {

  public items: any[] = [];

  constructor(
    public loadingController: LoadingController,
    private rssMessageService: RssMessageService) { }

  ngOnInit() {
      this.loadingController.create({
        message: 'Téléchargement...'
      }).then((res) => {
        res.present();
        this.rssMessageService.getByFeedTitle('Nouveautés')
          .subscribe(data => {
            this.items = data;
            res.dismiss();
          });
      });
  }
}
