package dwiyanhartono.com.ksp3.model;

public class Requestimage {
    private String f_cif;
    private String f_code_image;
    private String f_loanid;


    public Requestimage(String f_cif, String f_code_image, String f_loanid) {
        this.f_cif = f_cif;
        this.f_code_image = f_code_image;
        this.f_loanid = f_loanid;
    }

    public String getF_cif() {
        return f_cif;
    }

    public void setF_cif(String f_cif) {
        this.f_cif = f_cif;
    }



    public String getF_code_image() {
        return f_code_image;
    }

    public void setF_code_image(String f_code_image) {
        this.f_code_image = f_code_image;
    }

    public String getF_loanid() {
        return f_loanid;
    }

    public void setF_loanid(String f_loanid) {
        this.f_loanid = f_loanid;
    }
}
