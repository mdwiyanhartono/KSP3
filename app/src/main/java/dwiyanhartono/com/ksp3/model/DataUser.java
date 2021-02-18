package dwiyanhartono.com.ksp3.model;

import com.google.gson.annotations.SerializedName;

public class DataUser {
    @SerializedName("nama")
    private String nama;
    @SerializedName("email")
    private String email;
    @SerializedName("id_user")
    private String id_user;
    @SerializedName("id_branch")
    private String id_brnch;
    @SerializedName("countlogin")
    private String countlogin;
    @SerializedName("jabatan")
    private String jabatan;
    @SerializedName("imei")
    private String imei;




    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getCountlogin() {
        return countlogin;
    }

    public void setCountlogin(String countlogin) {
        this.countlogin = countlogin;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId_brnch() {
        return id_brnch;
    }

    public void setId_brnch(String id_brnch) {
        this.id_brnch = id_brnch;
    }
}
