package archive.wafa.demo.model;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DATS_WEB_TABLE")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "Archive_Doc", procedureName = "XX_DMS_DATS_WEB_PKG.DATS_ARCHIVE_DOCUMENT",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "DOC_BARCODE_IN", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "MESSAGE_NO_OUT", type = Integer.class)
                }
        ),
        @NamedStoredProcedureQuery(name = "Archive_Doc_NEW", procedureName = "XX_DMS_DATS_WEB_PKG.DATS_ARCHIVE_DOCUMENT_TWO",
                resultClasses = {Document.class},
                parameters = {
                        @StoredProcedureParameter( mode = ParameterMode.IN ,name = "HOLDER_BARCODE_IN",  type = String.class),
                        @StoredProcedureParameter( mode = ParameterMode.IN ,name = "USERNAME_IN",  type = String.class),
                        @StoredProcedureParameter( mode = ParameterMode.REF_CURSOR ,name = "P_STATUS",  type = void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "DOC_BARCODE_IN", type = Long.class)
                }
        ),
        @NamedStoredProcedureQuery(name = "CREATE_USER", procedureName = "XX_DMS_DATS_WEB_PKG.DATS_CREATE_USER",
                parameters = {
                        @StoredProcedureParameter( mode = ParameterMode.IN ,name = "P_EMAIL_IN",  type = String.class),
                        @StoredProcedureParameter( mode = ParameterMode.IN ,name = "P_PASSWORD_IN",  type = String.class),
                        @StoredProcedureParameter( mode = ParameterMode.OUT ,name = "P_USER_ID_OUT",  type = Long.class),
                        @StoredProcedureParameter( mode = ParameterMode.OUT ,name = "P_ACTION_OUT",  type = Long.class)
                }
        )
}
)


@Setter
@Getter
@NoArgsConstructor
public class SortedProcedure implements Serializable {
    @Id
    @Column(name="ID")
    private Long sId;

    public SortedProcedure(Long sId) {
        this.sId = sId;
    }



}
