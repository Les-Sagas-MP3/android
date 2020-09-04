export class IdentityModel {
    
    id: number = 0;

    static valueOf(id: number): IdentityModel {
        var model = new IdentityModel();
        model.id = id;
        return model;
    }
}
