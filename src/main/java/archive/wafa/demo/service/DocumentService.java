package archive.wafa.demo.service;

import archive.wafa.demo.model.Document;

import java.util.List;

public interface DocumentService extends CRUDService<Document> {

    public Document archiveDocument (Long docId , String holderNo , String username);
}
