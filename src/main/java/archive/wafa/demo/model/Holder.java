package archive.wafa.demo.model;


import javax.persistence.*;

@Entity
@Table(name = "XX_DMS_HOLDER_T")
public class Holder {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "holder_generator")
    @SequenceGenerator(name="holder_generator", sequenceName = "XX_HOLDER_SEQ", allocationSize=1)
    @Column(name="HOLDER_ID")
    private Long holderId;

    @Column(name="HOLDER_NO")
    private String holderNo;


    public Holder(String holderNo) {
        this.holderNo = holderNo;
    }

    public Holder() {
    }

    public Long getHolderId() {
        return holderId;
    }

    public String getHolderNo() {
        return holderNo;
    }

    public void setHolderId(Long holderId) {
        this.holderId = holderId;
    }

    public void setHolderNo(String holderNo) {
        this.holderNo = holderNo;
    }
}
