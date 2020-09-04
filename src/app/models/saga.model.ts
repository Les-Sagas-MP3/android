import { AuditModel } from './audit.model';

export class SagaModel extends AuditModel {

    title: string = '';
    url: string = '';
    urlWiki: string = '';
    levelArt: number = 100;
    levelTech: number = 100;
    nbReviews: number = 0;
    urlReviews: string = '';
    nbBravos: number = 0;
    authorsRef: number[] = [];
    categoriesRef: number[] = [];

}
