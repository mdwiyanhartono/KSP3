package dwiyanhartono.com.ksp3.model;

import com.google.gson.annotations.SerializedName;

public class Datasub7 {

    @SerializedName("f_nama_anggota")
    private String namaanggota;
    @SerializedName("f_cif")
    private String cif;
    @SerializedName("f_loanid")
    private String loanid;
    @SerializedName("f_hasilkunjungan")
    private String hasilkunjungan;
    @SerializedName("f_bertemu")
    private String bertemu;
    @SerializedName("f_lokasibertemu")
    private String lokasibertemu;
    @SerializedName("f_actionplan")
    private String actionplan;
    @SerializedName("f_date_actionplan")
    private String tanggalaction;
    @SerializedName("f_nominal_pelunasan")
    private String nominal;

    public String getTanggalaction() {
        return tanggalaction;
    }

    public void setTanggalaction(String tanggalaction) {
        this.tanggalaction = tanggalaction;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getNamaanggota() {
        return namaanggota;
    }

    public void setNamaanggota(String namaanggota) {
        this.namaanggota = namaanggota;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getLoanid() {
        return loanid;
    }

    public void setLoanid(String loanid) {
        this.loanid = loanid;
    }

    public String getHasilkunjungan() {
        return hasilkunjungan;
    }

    public void setHasilkunjungan(String hasilkunjungan) {
        this.hasilkunjungan = hasilkunjungan;
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

    public String getActionplan() {
        return actionplan;
    }

    public void setActionplan(String actionplan) {
        this.actionplan = actionplan;
    }
}
