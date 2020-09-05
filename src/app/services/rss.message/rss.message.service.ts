import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { RssMessageModel } from 'src/app/models/rss.message.model';

@Injectable({
  providedIn: 'root'
})
export class RssMessageService {

  constructor(private http: HttpClient) { }

  getByFeedTitle(feedTitle: string) : Observable<RssMessageModel[]> {
    return this.http.get<RssMessageModel[]>(`${environment.apiUrl}/rss?feedTitle=${feedTitle}`);
  }

  getById(id: number) : Observable<RssMessageModel> {
    return this.http.get<RssMessageModel>(`${environment.apiUrl}/rss/${id}`);
  }

}
