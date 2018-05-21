package archive.wafa.demo.service;

import archive.wafa.demo.model.Holder;

public interface HolderService extends CRUDService<Holder> {
    public Holder newHolder (Long batchTypId , Long HolderTypId , String shelfNo , Long usernameId , String lang);
    public void createHolderBarcodePDF (String holderBarcode );

}
