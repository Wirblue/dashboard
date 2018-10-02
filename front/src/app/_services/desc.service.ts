import { Injectable } from '@angular/core';
import { ServiceDesc } from '../_class/service-desc';
import {config, Observable, of} from 'rxjs';
import { HttpClient, HttpClientModule} from '@angular/common/http';
import { GlobalVariable } from '../globals';
import {catchError} from 'rxjs/operators';
import {WidgetDesc, WidgetDestParams} from '../_class/widget-desc';

@Injectable({
  providedIn: 'root'
})
export class DescService {

  constructor(private http: HttpClient) {}

  tmp_service: ServiceDesc[] = [
    new ServiceDesc(
      'Service 1',
      'FIRST SERVICE LOL',
      [
        new WidgetDesc(
          's1w1',
          'service 1 widget 1',
          [
            new WidgetDestParams(
              's1w1p1',
              'integer'
            )
          ]
        ),
        new WidgetDesc(
          's1w2',
          'service 1 widget 2',
          [
            new WidgetDestParams(
              's1w2p1',
              'string'
            ),
            new WidgetDestParams(
              's1w2p3',
              'integer'
            ),
            new WidgetDestParams(
              's1w2p3',
              'string'
            )
          ]
        )
      ]
    ),
    new ServiceDesc(
      'Service 2',
      'FIRST SERVICE LOL',
      [
        new WidgetDesc(
          's1w1',
          'service 1 widget 1',
          [
            new WidgetDestParams(
              's1w1p1',
              'integer'
            )
          ]
        ),
        new WidgetDesc(
          's1w2',
          'service 1 widget 2',
          [
            new WidgetDestParams(
              's1w2p1',
              'string'
            ),
            new WidgetDestParams(
              's1w2p3',
              'integer'
            ),
            new WidgetDestParams(
              's1w2p3',
              'string'
            )
          ]
        )
      ]
    ), new ServiceDesc(
      'Service 3',
      'FIRST SERVICE LOL',
      [
        new WidgetDesc(
          's1w1',
          'service 1 widget 1',
          [
            new WidgetDestParams(
              's1w1p1',
              'integer'
            )
          ]
        ),
        new WidgetDesc(
          's1w2',
          'service 1 widget 2',
          [
            new WidgetDestParams(
              's1w2p1',
              'string'
            ),
            new WidgetDestParams(
              's1w2p3',
              'integer'
            ),
          ]
        ),
        new WidgetDesc(
          's1w2',
          'service 1 widget 2',
          [
            new WidgetDestParams(
              's1w2p1',
              'string'
            ),
            new WidgetDestParams(
              's1w2p3',
              'integer'
            ),
          ]
        )
      ]
    ),
  ];

  getServices(): Observable<ServiceDesc[]> {
    return of(this.tmp_service);
    /*return this.http.get<ServiceDesc[]>(GlobalVariable.BASE_API_URL + '/desc')
      .pipe(
        catchError(this.handleError('getServices', []))
      );*/
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}
