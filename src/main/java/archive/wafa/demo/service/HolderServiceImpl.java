package archive.wafa.demo.service;

import archive.wafa.demo.model.Holder;
import archive.wafa.demo.repository.HolderRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class HolderServiceImpl implements HolderService {

    private HolderRepository holderRepository ;
    private EntityManager em;


    public HolderServiceImpl(HolderRepository holderRepository, EntityManager em) {
        this.holderRepository = holderRepository;
        this.em = em;
    }

    @Override
    public List<Holder> listAll() {
        StoredProcedureQuery Query = em.createNamedStoredProcedureQuery("GET_HOLDER");
        Query.execute();
        List<Holder> resultList = Query.getResultList();
        return resultList;
    }

    @Override
    public Holder getById(Long id) {
        Optional<Holder> holderOptional =  holderRepository.findById(id);
        return holderOptional.get();
    }

    @Override
    public Holder saveOrUpdate(Holder domainObject) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void createHolderBarcodePDF(String holderBarcode) {
        Document document = new Document(PageSize.A4.rotate());
        document.setMargins(10f, 0, 2, 2);
        PdfWriter writer = null;
        try {
            String path ="src/main/resources/static/images/Doc/";
            writer = PdfWriter.getInstance(document, new FileOutputStream(path.concat(holderBarcode).concat(".pdf")));
            document.open();

            /*Font font = FontFactory.getFont("c:/windows/fonts/V100009.ttf",
                    BaseFont.WINANSI, BaseFont.NOT_EMBEDDED, 123f, Font.NORMAL, BaseColor.BLACK);*/

            Font font = FontFactory.getFont("c:/windows/fonts/LibreBarcode39ExtendedText-Regular.ttf",
                    BaseFont.WINANSI, BaseFont.EMBEDDED, 115f, Font.NORMAL, BaseColor.BLACK);
            String barcode = "*";
            Chunk chunk = new Chunk(barcode.concat(holderBarcode).concat("*"), font);

            document.add(new Paragraph("\n\n\n\n\n\n\n\n\n"));
            document.add(chunk);


            document.close();


        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Holder newHolder(Long batchTypId, Long HolderTypId, String shelfNo, Long usernameId, String lang) {
        StoredProcedureQuery Query = em.createNamedStoredProcedureQuery("CREATE_HOLDER");
        //System.out.println(holderNo+"--------------------------"+username);
        Query.setParameter("BATCH_TYPE_CODE_IN", batchTypId);
        Query.setParameter("HOLDER_TYPE_CODE_IN", HolderTypId);
        Query.setParameter("shelf_no_IN", shelfNo);
        Query.setParameter("USERNAME_ID_IN", usernameId);
        Query.setParameter("LANG_IN", lang);
        Query.execute();
        List<Holder> resultList = Query.getResultList();
        createHolderBarcodePDF(resultList.get(0).getHolderNo());

        return resultList.get(0);
    }
}
