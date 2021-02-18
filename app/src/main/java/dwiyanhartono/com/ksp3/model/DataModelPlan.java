package dwiyanhartono.com.ksp3.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class DataModelPlan {
    //            "f_id": "1781",
//            "f_id_debitur": "1781",
//            "f_cif": "100020996.0",
//            "f_loanid": "1021314141",
//            "f_agent": "DAV888",
//            "f_id_tf": "",
//            "f_status": "1",
//            "f_aproved": "",
//            "f_active": "",
//            "f_user_create": "1117",
//            "f_date_cerate": "2019-03-29",
//            "f_user_update": "",
//            "f_update_date": "",
//            "f_custname": "ADE PUTRA SIHOTANG",
//            "f_jenisfasilitas": "1061",
//            "f_plafound": "20000000.00",
//            "f_nama_produk": "PRK SME Online Business",
//            "f_startdate": "2017-08-21 00:00:00.000",
//            "f_duedate": "2018-08-21 00:00:00.000",
//            "f_cycle": "21.0",
//            "f_tenor": "12.0",
//            "f_branch_id": "ID0010015",
//            "f_homepostcode": "15148.0",
//            "f_dpd_eom": "NULL",
//            "f_dpd": "137.0",
//            "f_bucket": "4.0",
//            "f_flag": "Flag",
//            "f_angsuran": "NULL",
//            "f_buki_debit": "21338311.65",
//            "f_tunggakan_pokok": "21338311.65",
//            "f_bunga": "2382550.51",
//            "f_denda": "NULL",
//            "f_total_tagihan": "NULL",
//            "f_saldo_tabungan": "",
//            "f_tanggal_restruktur": "",
//            "f_restruktur_ke": "2.0",
//            "f_tanggal_update_data_t24": "2019-02-20 00:00:00.000",
//            "f_tanggal_update_DWH": "2019-02-20 00:00:00.000",
//            "f_alamat_tagih": "PORIS INDAH BLOK D/294",
//            "f_no_tlp_debitur": "87880005604",
//            "f_acctno": "100020996.0",
//            "f_email": "m.dwiyan.hartono@gmail.com",
//            "f_assign_id": "19"
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
    @SerializedName("f_dpd")
    private String dpd;
    @SerializedName("f_buzaddress")
    private String alamatusaha;
    @SerializedName("f_email")
    private String email;
    @SerializedName("f_loanid")
    private String loanid;
    @SerializedName("angsuran")
    private String angsuran;
    @SerializedName("notlp")
    private String notlp;

    public String getNotlp() {
        return notlp;
    }

    public void setNotlp(String notlp) {
        this.notlp = notlp;
    }
    public String getAngsuran() {
        return angsuran;
    }

    public void setAngsuran(String angsuran) {
        this.angsuran = angsuran;
    }


    private boolean isChecked;

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

}
