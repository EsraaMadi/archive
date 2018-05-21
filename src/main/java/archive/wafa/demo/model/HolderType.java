package archive.wafa.demo.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="XX_DMS_HOLDER_TYPE_T")
public class HolderType {

    @Id
    @Column(name="HOLDER_TYPE_ID")
    private Long holderTypId;

    @Column(name ="HOLDER_TYPE_NAME")
    private String holderTypeName;

    public HolderType() {
    }

    public HolderType(Long holderTypId, String holderTypeName) {
        this.holderTypId = holderTypId;
        this.holderTypeName = holderTypeName;
    }

    public Long getHolderTypId() {
        return holderTypId;
    }

    public void setHolderTypId(Long holderTypId) {
        this.holderTypId = holderTypId;
    }

    public String getHolderTypeName() {
        return holderTypeName;
    }

    public void setHolderTypeName(String holderTypeName) {
        this.holderTypeName = holderTypeName;
    }
}
