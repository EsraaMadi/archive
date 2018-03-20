package archive.wafa.demo.model;

import javax.persistence.*;

@Entity
//@Table(name = "XX_DMS_DOCUMENT_T")
public class Document {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "document_generator")
    //@SequenceGenerator(name="document_generator", sequenceName = "XX_DOCUMENT_SEQ", allocationSize=1)
    @Column(name="DOCUMENT_ID")
    private Long documentId;

    @Column(name="DOCUMENT_NO")
    private Long DocumentNo ;

    @Column(name="RESULT_MSG")
    private String rersultMsg;

    @Column(name="DOC_STATUS")
    private int archiveStatus;

    @Column(name="PAPER_SCAN_DATE")
    private String paperScanDate;

    @Column(name="CURRENT_HOLDER_NO")
    private String currDocumnetHolder ;

    @Column(name="OLD_HOLDER_NO")
    private String pervDocumnetHolder ;

    @Column(name="DOCUMENT_TYPE")
    private String documentType;

    @Column(name="PAPER_PROCESS_DATE")
    private String paperProcessDate;

    @Column(name="PAPER_ARCHIVE_DATE")
    private String paperArchiveDate;

    @Column(name="ARCHIVER")
    private String archiver;

    @Column(name="DOC_PASS_ONCE")
    private int docPassOnceSum ;

    @Column(name="DOC_PASS_TWICE")
    private int docPassTwiceSum ;

    @Column(name="DOC_FAILD_ONCE")
    private int docFaildOnceSum ;

    @Column(name="DOC_FAILD_TWICE")
    private int docFaildTwiceSum ;


    public Document() {
    }

    public Document(Long documentId, Long documentNo, String rersultMsg, int archiveStatus, String paperScanDate, String currDocumnetHolder, String pervDocumnetHolder, String documentType, String paperProcessDate, String paperArchiveDate, String archiver, int docPassOnceSum, int docPassTwiceSum, int docFaildOnceSum, int docFaildTwiceSum) {
        this.documentId = documentId;
        DocumentNo = documentNo;
        this.rersultMsg = rersultMsg;
        this.archiveStatus = archiveStatus;
        this.paperScanDate = paperScanDate;
        this.currDocumnetHolder = currDocumnetHolder;
        this.pervDocumnetHolder = pervDocumnetHolder;
        this.documentType = documentType;
        this.paperProcessDate = paperProcessDate;
        this.paperArchiveDate = paperArchiveDate;
        this.docPassOnceSum = docPassOnceSum;
        this.docPassTwiceSum = docPassTwiceSum;
        this.docFaildOnceSum = docFaildOnceSum;
        this.docFaildTwiceSum = docFaildTwiceSum;
        archiver = archiver;
    }


    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Long getDocumentNo() {
        return DocumentNo;
    }

    public void setDocumentNo(Long documentNo) {
        DocumentNo = documentNo;
    }

    public String getRersultMsg() {
        return rersultMsg;
    }

    public void setRersultMsg(String rersultMsg) {
        this.rersultMsg = rersultMsg;
    }

    public int getArchiveStatus() {
        return archiveStatus;
    }

    public void setArchiveStatus(int archiveStatus) {
        this.archiveStatus = archiveStatus;
    }

    public String getPaperScanDate() {
        return paperScanDate;
    }

    public void setPaperScanDate(String paperScanDate) {
        this.paperScanDate = paperScanDate;
    }

    public String getCurrDocumnetHolder() {
        return currDocumnetHolder;
    }

    public void setCurrDocumnetHolder(String currDocumnetHolder) {
        this.currDocumnetHolder = currDocumnetHolder;
    }

    public String getPervDocumnetHolder() {
        return pervDocumnetHolder;
    }

    public void setPervDocumnetHolder(String pervDocumnetHolder) {
        this.pervDocumnetHolder = pervDocumnetHolder;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getPaperProcessDate() {
        return paperProcessDate;
    }

    public void setPaperProcessDate(String paperProcessDate) {
        this.paperProcessDate = paperProcessDate;
    }

    public String getPaperArchiveDate() {
        return paperArchiveDate;
    }

    public void setPaperArchiveDate(String paperArchiveDate) {
        this.paperArchiveDate = paperArchiveDate;
    }

    public String getArchiver() {
        return archiver;
    }

    public void setArchiver(String archiver) {
        archiver = archiver;
    }

    public int getDocPassOnceSum() {
        return docPassOnceSum;
    }

    public void setDocPassOnceSum(int docPassOnceSum) {
        this.docPassOnceSum = docPassOnceSum;
    }

    public int getDocPassTwiceSum() {
        return docPassTwiceSum;
    }

    public void setDocPassTwiceSum(int docPassTwiceSum) {
        this.docPassTwiceSum = docPassTwiceSum;
    }

    public int getDocFaildOnceSum() {
        return docFaildOnceSum;
    }

    public void setDocFaildOnceSum(int docFaildOnceSum) {
        this.docFaildOnceSum = docFaildOnceSum;
    }

    public int getDocFaildTwiceSum() {
        return docFaildTwiceSum;
    }

    public void setDocFaildTwiceSum(int docFaildTwiceSum) {
        this.docFaildTwiceSum = docFaildTwiceSum;
    }
}
