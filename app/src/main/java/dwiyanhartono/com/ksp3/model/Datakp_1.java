package dwiyanhartono.com.ksp3.model;

public class Datakp_1 {

    private String namaanggota;
    private String cif;
    private String loanid;
    private String hasilkunjungan;
    private String bertemu;
    private String lokasibertemu;
    private String actionplan;
    private String tanggalaction;
    private String nominal;
    private String dateprocess;

    public Datakp_1(String namaanggota, String cif, String loanid, String hasilkunjungan, String bertemu, String lokasibertemu, String actionplan, String tanggalaction, String nominal, String dateprocess) {
        this.namaanggota = namaanggota;
        this.cif = cif;
        this.loanid = loanid;
        this.hasilkunjungan = hasilkunjungan;
        this.bertemu = bertemu;
        this.lokasibertemu = lokasibertemu;
        this.actionplan = actionplan;
        this.tanggalaction = tanggalaction;
        this.nominal = nominal;
        this.dateprocess = dateprocess;
    }

    public String getDateprocess() {
        return dateprocess;
    }

    public void setDateprocess(String dateprocess) {
        this.dateprocess = dateprocess;
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
