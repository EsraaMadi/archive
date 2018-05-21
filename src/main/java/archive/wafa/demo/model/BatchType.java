package archive.wafa.demo.model;


import javax.persistence.*;

@Entity
@Table(name = "XX_DMS_BATCH_TYPE_T")
public class BatchType {

    @Id
    @Column(name="BATCH_TYPE_ID")
    private Long batchTypId;

    @Column(name="BATCH_TYPE_NAME")
    private String batchTypName;

    @Column(name="BATCH_TYP_CODE")
    private Long batchTypCode;

    public BatchType() {
    }

    public BatchType(Long batchTypId, String batchTypName, Long batchTypCode) {
        this.batchTypId = batchTypId;
        this.batchTypName = batchTypName;
        this.batchTypCode = batchTypCode;
    }

    public Long getBatchTypId() {
        return batchTypId;
    }

    public void setBatchTypId(Long batchTypId) {
        this.batchTypId = batchTypId;
    }

    public String getBatchTypName() {
        return batchTypName;
    }

    public void setBatchTypName(String batchTypName) {
        this.batchTypName = batchTypName;
    }

    public Long getBatchTypCode() {
        return batchTypCode;
    }

    public void setBatchTypCode(Long batchTypCode) {
        this.batchTypCode = batchTypCode;
    }
}
