import { IdentityModel } from './identity.model';

export class AuditModel extends IdentityModel {

    createdAt: Date = new Date();
    createdBy: string = '';
    updatedAt: Date = new Date();
    updatedBy: string = '';

}
