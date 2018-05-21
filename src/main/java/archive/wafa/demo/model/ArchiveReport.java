package archive.wafa.demo.model;

import javax.persistence.*;

// curser result
@Entity
public class ArchiveReport {

    @Id
    @Column(name ="DOC_PASS_ONCE")
    private Long passDocOnceSum;
    @Column(name ="DOC_PASS_TWICE")
    private Long passDocTwiceSum;
    @Column(name ="DOC_FAILD_ONCE")
    private Long faildDocOnceSum;
    @Column(name ="DOC_FAILD_TWICE")
    private Long faildDocTwiceSum;


    public ArchiveReport() {
    }

    public ArchiveReport(Long passDocOnceSum, Long passDocTwiceSum, Long faildDocOnceSum, Long faildDocTwiceSum) {
        this.passDocOnceSum = passDocOnceSum;
        this.passDocTwiceSum = passDocTwiceSum;
        this.faildDocOnceSum = faildDocOnceSum;
        this.faildDocTwiceSum = faildDocTwiceSum;
    }

    public Long getPassDocOnceSum() {
        return passDocOnceSum;
    }

    public void setPassDocOnceSum(Long passDocOnceSum) {
        this.passDocOnceSum = passDocOnceSum;
    }

    public Long getPassDocTwiceSum() {
        return passDocTwiceSum;
    }

    public void setPassDocTwiceSum(Long passDocTwiceSum) {
        this.passDocTwiceSum = passDocTwiceSum;
    }

    public Long getFaildDocOnceSum() {
        return faildDocOnceSum;
    }

    public void setFaildDocOnceSum(Long faildDocOnceSum) {
        this.faildDocOnceSum = faildDocOnceSum;
    }

    public Long getFaildDocTwiceSum() {
        return faildDocTwiceSum;
    }

    public void setFaildDocTwiceSum(Long faildDocTwiceSum) {
        this.faildDocTwiceSum = faildDocTwiceSum;
    }
}
