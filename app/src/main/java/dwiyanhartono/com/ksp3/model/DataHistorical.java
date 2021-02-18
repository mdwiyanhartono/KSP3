package dwiyanhartono.com.ksp3.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class DataHistorical {


    @SerializedName("f_cif")
    private String cif;
    @SerializedName("f_agentid")
    private String agentid;
    @SerializedName("f_agentname")
    private String agentname;
    @SerializedName("f_loanid")
    private String loanid;
    @SerializedName("f_code_image")
    private String Code_image;
    @SerializedName("f_hasilkunjungan")
    private String kunjungan;
    @SerializedName("f_total_bayar")
    private String totalbayar;
    @SerializedName("f_bertemu")
    private String bertemu;
    @SerializedName("f_lokasibertemu")
    private String lokasibertemu;
    @SerializedName("f_karakter")
    private String karakter;
    @SerializedName("f_resumenasabah")
    private String resumenasabah;
    @SerializedName("f_actionplan")
    private String actionplan;
    @SerializedName("f_tgl_visit")
    private String tglvisit;
    @SerializedName("f_date_actionplan")
    private String dateplan;


    public String getDateplan() {
        return dateplan;
    }

    public void setDateplan(String dateplan) {
        this.dateplan = dateplan;
    }

    public String getAgentname() {
        return agentname;
    }

    public void setAgentname(String agentname) {
        this.agentname = agentname;
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public String getLoanid() {
        return loanid;
    }

    public void setLoanid(String loanid) {
        this.loanid = loanid;
    }

    public String getCode_image() {
        return Code_image;
    }

    public void setCode_image(String code_image) {
        Code_image = code_image;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getTglvisit() {
        return tglvisit;
    }

    public void setTglvisit(String tglvisit) {
        this.tglvisit = tglvisit;
    }



    public String getKunjungan() {
        return kunjungan;
    }

    public void setKunjungan(String kunjungan) {
        this.kunjungan = kunjungan;
    }

    public String getTotalbayar() {
        return totalbayar;
    }

    public void setTotalbayar(String totalbayar) {
        this.totalbayar = totalbayar;
    }

    public String getBertemu() {
        return bertemu;
    }

    public void setBertemu(String bertemu) {
        this.bertemu = bertemu;
    }

    public String getLokasibertemu() {
        return lokasibertemu;
    }

    public void setLokasibertemu(String lokasibertemu) {
        this.lokasibertemu = lokasibertemu;
    }

    public String getKarakter() {
        return karakter;
    }

    public void setKarakter(String karakter) {
        this.karakter = karakter;
    }

    public String getResumenasabah() {
        return resumenasabah;
    }

    public void setResumenasabah(String resumenasabah) {
        this.resumenasabah = resumenasabah;
    }

    public String getActionplan() {
        return actionplan;
    }

    public void setActionplan(String actionplan) {
        this.actionplan = actionplan;
    }
}
