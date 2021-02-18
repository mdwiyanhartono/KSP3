package dwiyanhartono.com.ksp3.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class DataModel {
    @SerializedName("f_acctno")
    private String acctno;
    @SerializedName("f_agent")
    private String agentid;
    @SerializedName("f_custname")
    private String nama;
    @SerializedName("f_bucket")
    private String bucket;
    @SerializedName("f_bucketeom")
    private String bucketeom;
    @SerializedName("f_cif")
    private String cif;
    @SerializedName("f_outstanding")
    private String os;
    @SerializedName("f_total_tunggakan")
    private String totaltunggakan;
    @SerializedName("f_alamat_tagih")
    private String alamat;
    @SerializedName("f_aproved")
    private String approved;


    @SerializedName("f_action")
    private String action;
    @SerializedName("f_dpd")
    private String dpd;
    @SerializedName("f_buzaddress")
    private String alamatusaha;
    @SerializedName("f_email")
    private String email;
    @SerializedName("f_loanid")
    private String loanid;
    @SerializedName("f_tanggal")
    private String tglrequest;

    private boolean isChecked;


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAcctno() {
        return acctno;
    }

    public void setAcctno(String acctno) {
        this.acctno = acctno;
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

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamatusaha() {
        return alamatusaha;
    }

    public void setAlamatusaha(String alamatusaha) {
        this.alamatusaha = alamatusaha;
    }

    public String getDpd() {
        return dpd;
    }

    public void setDpd(String dpd) {
        this.dpd = dpd;
    }

    public String getLoanid() {
        return loanid;
    }

    public void setLoanid(String loanid) {
        this.loanid = loanid;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getTglrequest() {
        return tglrequest;
    }

    public void setTglrequest(String tglrequest) {
        this.tglrequest = tglrequest;
    }
}
