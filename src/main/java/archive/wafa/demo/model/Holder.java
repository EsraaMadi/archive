package archive.wafa.demo.model;


import javax.persistence.*;

@Entity
// CURSOR result not a class
//@Table(name = "XX_DMS_HOLDER_T")
public class Holder {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "holder_generator")
    //@SequenceGenerator(name="holder_generator", sequenceName = "XX_HOLDER_SEQ", allocationSize=1)
    @Column(name="HOLDER_ID")
    private Long holderId;

    @Column(name="HOLDER_NO")
    private String holderNo;

    @Column(name ="HOLDER_TYPE_ID")
    private Long holderTypId;

    @Column(name="SHELF_ID")
    private Long shelfId;

    @Column(name ="HOLDER_TYPE")
    private String holderTyp;

    @Column(name="SHELF")
    private String shelf;

    @Column(name="STATUS_MSG")
    private String statusMsg;

    @Column(name="STATUS")
    private Long statusN;

    @Column(name="HOLDER_DATE")
    private String holderDate;


    public Holder(Long holderId, String holderNo, Long holderTypId, String holderTyp, Long shelfId, String shelf, String statusMsg, Long status, String holderDate) {
        this.holderId = holderId;
        this.holderNo = holderNo;
        this.holderTypId = holderTypId;
        this.holderTyp = holderTyp;
        this.shelfId = shelfId;
        this.shelf = shelf;
        this.statusMsg = statusMsg;
        this.statusN = status;
        this.holderDate = holderDate;
    }

    public Holder() {
    }

    public Long getHolderTypId() {
        return holderTypId;
    }

    public void setHolderTypId(Long holderTypId) {
        this.holderTypId = holderTypId;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public String getHolderDate() {
        return holderDate;
    }

    public void setHolderDate(String holderDate) {
        this.holderDate = holderDate;
    }

    public Long getHolderId() {
        return holderId;
    }

    public void setHolderId(Long holderId) {
        this.holderId = holderId;
    }

    public String getHolderNo() {
        return holderNo;
    }

    public void setHolderNo(String holderNo) {
        this.holderNo = holderNo;
    }

    public String getHolderTyp() {
        return holderTyp;
    }

    public void setHolderTyp(String holderTyp) {
        this.holderTyp = holderTyp;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public Long getStatusN() {
        return statusN;
    }

    public void setStatusN(Long statusN) {
        this.statusN = statusN;
    }
}
