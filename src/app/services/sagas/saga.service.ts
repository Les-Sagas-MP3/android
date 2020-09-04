import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { SagaModel } from 'src/app/models/saga.model';

@Injectable({
  providedIn: 'root'
})
export class SagaService {

  constructor(private http: HttpClient) { }
  
  getAll() : Observable<SagaModel[]> {
    return this.http.get<SagaModel[]>(`${environment.apiUrl}/sagas`);
  }

}
