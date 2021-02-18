package dwiyanhartono.com.ksp3.model;

public class Dataplanvisit {
    String acctno;
    String agentid;
    String nama;
    String bucket;
    String bucketeom;
    String cif;
    String os;
    String totaltunggakan;
    String alamat;
    String approved;
    String dpd;
    String alamatusaha;
    String email;
    String loanid;
    String angsuran;
    String notlp;

    public Dataplanvisit(String acctno, String agentid, String nama, String bucket, String bucketeom, String cif, String os, String totaltunggakan, String alamat, String approved, String dpd, String alamatusaha, String email, String loanid, String angsuran,String notlp) {
        this.acctno = acctno;
        this.agentid = agentid;
        this.nama = nama;
        this.bucket = bucket;
        this.bucketeom = bucketeom;
        this.cif = cif;
        this.os = os;
        this.totaltunggakan = totaltunggakan;
        this.alamat = alamat;
        this.approved = approved;
        this.dpd = dpd;
        this.alamatusaha = alamatusaha;
        this.email = email;
        this.loanid = loanid;
        this.angsuran = angsuran;
        this.notlp = notlp;
    }


    public String getAngsuran() {
        return angsuran;
    }

    public void setAngsuran(String angsuran) {
        this.angsuran= angsuran;
    }

    public String getAcctno() {
        return acctno;
    }

    public void setAcctno(String acctno) {
        this.acctno = acctno;
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getBucketeom() {
        return bucketeom;
    }

    public void setBucketeom(String bucketeom) {
        this.bucketeom = bucketeom;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getTotaltunggakan() {
        return totaltunggakan;
    }

    public void setTotaltunggakan(String totaltunggakan) {
        this.totaltunggakan = totaltunggakan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getDpd() {
        return dpd;
    }

    public void setDpd(String dpd) {
        this.dpd = dpd;
    }

    public String getAlamatusaha() {
        return alamatusaha;
    }

    public void setAlamatusaha(String alamatusaha) {
        this.alamatusaha = alamatusaha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoanid() {
        return loanid;
    }

    public void setLoanid(String loanid) {
        this.loanid = loanid;
    }

    public String getNotlp() {
        return notlp;
    }

    public void setNotlp(String notlp) {
        this.notlp = notlp;
    }

}
