package archive.wafa.demo.service;

import archive.wafa.demo.model.ArchiveReport;
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
    private EntityManager em;

    public DocumentServiceImpl(DocumentRepository documentRepository, EntityManager em) {
        this.documentRepository = documentRepository;
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
    public ArchiveReport DocumentReport(String username) {
        StoredProcedureQuery Query = em.createNamedStoredProcedureQuery("DATS_REPORT_BY_USER");
        Query.setParameter("P_USERNAME_IN", username);
        Query.execute();
        List<ArchiveReport> resultList = Query.getResultList();

        return resultList.get(0);
    }

    @Override
    public Document archiveDocument(Long docId , String holderNo , String username , String lang) {
        //int i = sortedProcedureRepository.archiveDoc(docId);

        StoredProcedureQuery Query = em.createNamedStoredProcedureQuery("Archive_Doc");
        System.out.println(holderNo+"--------------------------"+username);
        Query.setParameter("HOLDER_BARCODE_IN", holderNo);
        Query.setParameter("DOC_BARCODE_IN", docId);
        Query.setParameter("USERNAME_IN", username);
        Query.setParameter("LANG_IN", lang);
        Query.execute();
        List<Document> resultList = Query.getResultList();


         //sortedProcedureRepository.archiveDoc_new(docId);
        //Document document_new = sortedProcedureRepository.archiveDoc_new(docId);
       // System.out.println( "000000000000000----------------  "+ document_new.getDocumentId()+ "------" + document_new.getDocumentNo()+ "------" );

        return resultList.get(0);
    }
}
