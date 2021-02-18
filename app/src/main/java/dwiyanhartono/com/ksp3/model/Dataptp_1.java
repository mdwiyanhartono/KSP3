package dwiyanhartono.com.ksp3.model;

public class Dataptp_1 {

    private String namanasabah;
    private String cif;
    private String loanid;
    private String hasilkunjungan;
    private String bertemu;
    private String lokasibertemu;
    private String actionplan;
    private String tanggalaction;
    private String nominal;

    private boolean isChecked;


    public Dataptp_1(String namanasabah, String cif, String loanid, String hasilkunjungan, String bertemu, String lokasibertemu, String actionplan, String tanggalaction, String nominal) {
        this.namanasabah = namanasabah;
        this.cif = cif;
        this.loanid = loanid;
        this.hasilkunjungan = hasilkunjungan;
        this.bertemu = bertemu;
        this.lokasibertemu = lokasibertemu;
        this.actionplan = actionplan;
        this.tanggalaction = tanggalaction;
        this.nominal = nominal;
    }

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

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
