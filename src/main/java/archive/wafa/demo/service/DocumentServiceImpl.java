package archive.wafa.demo.service;

import archive.wafa.demo.model.Document;
import archive.wafa.demo.repository.DocumentRepository;
import archive.wafa.demo.repository.SortedProcedureRepository;
import org.springframework.stereotype.Service;

import javax.persistence.StoredProcedureQuery;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {

    private DocumentRepository documentRepository;
    private SortedProcedureRepository sortedProcedureRepository ;
    private EntityManager em;

    public DocumentServiceImpl(DocumentRepository documentRepository, SortedProcedureRepository sortedProcedureRepository, EntityManager em) {
        this.documentRepository = documentRepository;
        this.sortedProcedureRepository = sortedProcedureRepository;
        this.em = em;
    }

    @Override
    public List<?> listAll() {
        return null;
    }

    @Override
    public Document getById(Long id) {
        Optional<Document> documentOptional =  documentRepository.findById(id);
        return documentOptional.get();
    }

    @Override
    public Document saveOrUpdate(Document domainObject) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Document archiveDocument(Long docId , String holderNo , String username) {
        //int i = sortedProcedureRepository.archiveDoc(docId);

        StoredProcedureQuery Query = em.createNamedStoredProcedureQuery("Archive_Doc_NEW");
        System.out.println(holderNo+"--------------------------"+username);
        Query.setParameter("HOLDER_BARCODE_IN", holderNo);
        Query.setParameter("DOC_BARCODE_IN", docId);
        Query.setParameter("USERNAME_IN", username);
        Query.execute();
        List<Document> resultList = Query.getResultList();


         //sortedProcedureRepository.archiveDoc_new(docId);
        //Document document_new = sortedProcedureRepository.archiveDoc_new(docId);
       // System.out.println( "000000000000000----------------  "+ document_new.getDocumentId()+ "------" + document_new.getDocumentNo()+ "------" );

        return resultList.get(0);
    }
}
