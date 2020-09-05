import { AuditModel } from './audit.model';

export class RssMessageModel extends AuditModel {

    feedTitle: string = '';
    title: string = '';
    pubdate: Date = new Date();
    description: string = '';
    link: string = '';
    author: string = '';
    guid: string = '';

}
