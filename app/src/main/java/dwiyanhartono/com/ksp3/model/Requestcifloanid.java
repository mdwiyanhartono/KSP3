package dwiyanhartono.com.ksp3.model;

public class Requestcifloanid {
    private String f_cif;
    private String f_loanid;

    public Requestcifloanid(String f_cif, String f_loanid) {
        this.f_cif = f_cif;
        this.f_loanid = f_loanid;
    }

    public String getF_cif() {
        return f_cif;
    }

    public void setF_cif(String f_cif) {
        this.f_cif = f_cif;
    }

    public String getF_loanid() {
        return f_loanid;
    }

    public void setF_loanid(String f_loanid) {
        this.f_loanid = f_loanid;
    }
}
