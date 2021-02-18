package dwiyanhartono.com.ksp3.model;

import com.google.gson.annotations.SerializedName;

public class Dataimage {

    @SerializedName("f_id")
    private String f_id;

    @SerializedName("f_file_doc")
    private String f_file_doc;

    public Dataimage(String f_id, String f_file_doc) {
        this.f_id = f_id;
        this.f_file_doc = f_file_doc;
    }

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public String getF_file_doc() {
        return f_file_doc;
    }

    public void setF_file_doc(String f_file_doc) {
        this.f_file_doc = f_file_doc;
    }
}
