package dwiyanhartono.com.ksp3.model;

import com.google.gson.annotations.SerializedName;

public class DataKunjungan {

    @SerializedName("f_id")
    private String iddata;
    @SerializedName("f_lat")
    private String lat;
    @SerializedName("f_lng")
    private String lng;
    @SerializedName("f_code_image")
    private String codeimage;
    @SerializedName("f_actionplan_status")
    private String statusactionplan;
    @SerializedName("f_tujuan")
    private String tujuan;
    @SerializedName("f_nama_nasabah")
    private String namadebitur;
    @SerializedName("f_cif")
    private String cif;
    @SerializedName("f_hasilkunjungan")
    private String hasilkunjungan;
    @SerializedName("f_keterangan_hasilkunjungan")
    private String kethasilkunjungan;
    @SerializedName("f_bertemu")
    private String bertemu;
    @SerializedName("f_keterangan_bertemu")
    private String ketbertemu;
    @SerializedName("f_lokasibertemu")
    private String lokasibertemu;
    @SerializedName("f_keterangan_lokasi")
    private String ketlokasi;
    @SerializedName("f_karakter")
    private String karakter;
    @SerializedName("f_keterangan_karakter")
    private String ketkarakter;
    @SerializedName("f_negatif_issue")
    private String negatifissue;
    @SerializedName("f_actionplan")
    private String actionplan;
    @SerializedName("f_date_actionplan")
    private String dateactionplan;
    @SerializedName("f_resumenasabah")
    private String resume;
    @SerializedName("f_total_tunggakan")
    private String totaltunggakan;
    @SerializedName("f_total_bayar")
    private String totalbayar;
    @SerializedName("f_perkiraan")
    private String perkiraan;
    @SerializedName("f_tgl_visit")
    private String tgvisit;
    @SerializedName("f_notif")
    private String notif;

    public String getIddata() {
        return iddata;
    }

    public void setIddata(String iddata) {
        this.iddata = iddata;
    }

    public String getCodeimage() {
        return codeimage;
    }

    public void setCodeimage(String codeimage) {
        this.codeimage = codeimage;
    }

    public String getStatusactionplan() {
        return statusactionplan;
    }

    public void setStatusactionplan(String statusactionplan) {
        this.statusactionplan = statusactionplan;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getNamadebitur() {
        return namadebitur;
    }

    public void setNamadebitur(String namadebitur) {
        this.namadebitur = namadebitur;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getHasilkunjungan() {
        return hasilkunjungan;
    }

    public void setHasilkunjungan(String hasilkunjungan) {
        this.hasilkunjungan = hasilkunjungan;
    }

    public String getKethasilkunjungan() {
        return kethasilkunjungan;
    }

    public void setKethasilkunjungan(String kethasilkunjungan) {
        this.kethasilkunjungan = kethasilkunjungan;
    }

    public String getBertemu() {
        return bertemu;
    }

    public void setBertemu(String bertemu) {
        this.bertemu = bertemu;
    }

    public String getKetbertemu() {
        return ketbertemu;
    }

    public void setKetbertemu(String ketbertemu) {
        this.ketbertemu = ketbertemu;
    }

    public String getLokasibertemu() {
        return lokasibertemu;
    }

    public void setLokasibertemu(String lokasibertemu) {
        this.lokasibertemu = lokasibertemu;
    }

    public String getKetlokasi() {
        return ketlokasi;
    }

    public void setKetlokasi(String ketlokasi) {
        this.ketlokasi = ketlokasi;
    }

    public String getKarakter() {
        return karakter;
    }

    public void setKarakter(String karakter) {
        this.karakter = karakter;
    }

    public String getKetkarakter() {
        return ketkarakter;
    }

    public void setKetkarakter(String ketkarakter) {
        this.ketkarakter = ketkarakter;
    }

    public String getNegatifissue() {
        return negatifissue;
    }

    public void setNegatifissue(String negatifissue) {
        this.negatifissue = negatifissue;
    }

    public String getActionplan() {
        return actionplan;
    }

    public void setActionplan(String actionplan) {
        this.actionplan = actionplan;
    }

    public String getDateactionplan() {
        return dateactionplan;
    }

    public void setDateactionplan(String dateactionplan) {
        this.dateactionplan = dateactionplan;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getTotaltunggakan() {
        return totaltunggakan;
    }

    public void setTotaltunggakan(String totaltunggakan) {
        this.totaltunggakan = totaltunggakan;
    }

    public String getTotalbayar() {
        return totalbayar;
    }

    public void setTotalbayar(String totalbayar) {
        this.totalbayar = totalbayar;
    }

    public String getPerkiraan() {
        return perkiraan;
    }

    public void setPerkiraan(String perkiraan) {
        this.perkiraan = perkiraan;
    }

    public String getTgvisit() {
        return tgvisit;
    }

    public void setTgvisit(String tgvisit) {
        this.tgvisit = tgvisit;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getNotif() {
        return notif;
    }

    public void setNotif(String notif) {
        this.notif = notif;
    }

}
