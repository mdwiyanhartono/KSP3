package dwiyanhartono.com.ksp3.model;

public class Requestimagelistupdate {
    private String f_id;
    private String f_textedit;


    public Requestimagelistupdate(String f_id, String f_textedit) {
        this.f_id = f_id;
        this.f_textedit = f_textedit;
    }

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public String getF_textedit() {
        return f_textedit;
    }

    public void setF_textedit(String f_textedit) {
        this.f_textedit = f_textedit;
    }
}
