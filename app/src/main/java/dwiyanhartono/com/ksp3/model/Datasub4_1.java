package dwiyanhartono.com.ksp3.model;

public class Datasub4_1 {

    String namanasabah;
    String cif;
    String loanid;
    String hasilkunjungan;
    String bertemu;
    String lokasibertemu;
    String actionplan;

    public Datasub4_1(String namanasabah, String cif, String loanid, String hasilkunjungan, String bertemu, String lokasibertemu, String actionplan) {
        this.namanasabah = namanasabah;
        this.cif = cif;
        this.loanid = loanid;
        this.hasilkunjungan = hasilkunjungan;
        this.bertemu = bertemu;
        this.lokasibertemu = lokasibertemu;
        this.actionplan = actionplan;
    }

    public String getNamanasabah() {
        return namanasabah;
    }

    public void setNamanasabah(String namanasabah) {
        this.namanasabah = namanasabah;
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
