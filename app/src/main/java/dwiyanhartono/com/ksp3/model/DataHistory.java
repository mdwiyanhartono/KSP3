package dwiyanhartono.com.ksp3.model;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class DataHistory {

    String cif;
    String kunjungan;
    String totalbayar;
    String bertemu;
    String lokasibertemu;
    String karakter;
    String resumenasabah;
    String actionplan;
    String tglvisit;

    public DataHistory(String cif,String kunjungan, String totalbayar, String bertemu, String lokasibertemu, String karakter, String resumenasabah, String actionplan,String tglvisit) {
        this.kunjungan = kunjungan;
        this.totalbayar = totalbayar;
        this.bertemu = bertemu;
        this.lokasibertemu = lokasibertemu;
        this.karakter = karakter;
        this.resumenasabah = resumenasabah;
        this.actionplan = actionplan;
        this.tglvisit = tglvisit;
        this.cif = cif;
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
