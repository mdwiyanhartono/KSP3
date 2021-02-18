package dwiyanhartono.com.ksp3.model;

public class DataTesimage_1 {


    private String f_id;
    private String f_keterangan;
    private String f_type;
    private String f_file_doc;


    public DataTesimage_1(String f_id, String f_keterangan, String f_type, String f_file_doc) {
        this.f_id = f_id;
        this.f_keterangan = f_keterangan;
        this.f_type = f_type;
        this.f_file_doc = f_file_doc;
    }

    public String getF_type() {
        return f_type;
    }

    public void setF_type(String f_type) {
        this.f_type = f_type;
    }

    public String getF_keterangan() {
        return f_keterangan;
    }

    public void setF_keterangan(String f_keterangan) {
        this.f_keterangan = f_keterangan;
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
