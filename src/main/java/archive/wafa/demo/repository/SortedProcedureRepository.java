package archive.wafa.demo.repository;

import archive.wafa.demo.model.Document;
import archive.wafa.demo.model.SortedProcedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SortedProcedureRepository extends JpaRepository<SortedProcedure ,Long> {

    @Procedure(name = "Archive_Doc")
    Integer archiveDoc(@Param("DOC_BARCODE_IN") Long DOC_BARCODE_IN);

    @Procedure(name = "Archive_Doc_NEW")
    Document archiveDoc_new(@Param("DOC_BARCODE_IN") Long DOC_BARCODE_IN );

    /*@Procedure(name = "CREATE_USER")
    Long createUser(@Param("P_EMAIL_IN") String P_EMAIL_IN , @Param("P_PASSWORD_IN") String P_PASSWORD_IN);*/


}
