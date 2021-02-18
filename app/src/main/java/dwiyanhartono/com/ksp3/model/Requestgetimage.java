package dwiyanhartono.com.ksp3.model;

public class Requestgetimage {
    private String f_cif;
    private String f_code;
    private String f_type;

    public Requestgetimage(String f_cif, String f_code, String f_type) {
        this.f_cif = f_cif;
        this.f_code = f_code;
        this.f_type = f_type;
    }

    public String getF_cif() {
        return f_cif;
    }

    public void setF_cif(String f_cif) {
        this.f_cif = f_cif;
    }

    public String getF_code() {
        return f_code;
    }

    public void setF_code(String f_code) {
        this.f_code = f_code;
    }

    public String getF_type() {
        return f_type;
    }

    public void setF_type(String f_type) {
        this.f_type = f_type;
    }
}
