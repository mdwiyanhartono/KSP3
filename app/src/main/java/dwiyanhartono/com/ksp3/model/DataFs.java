package dwiyanhartono.com.ksp3.model;

import com.google.gson.annotations.SerializedName;

public class DataFs {

    @SerializedName("f_proses_fs")
    private String pfs;
    @SerializedName("f_status_fs")
    private String statusfs;
    @SerializedName("f_actionplan_fs")
    private String apfs;
    @SerializedName("f_tgl_fs")
    private String tglfs;

    public String getPfs() {
        return pfs;
    }

    public void setPfs(String pfs) {
        this.pfs = pfs;
    }

    public String getStatusfs() {
        return statusfs;
    }

    public void setStatusfs(String statusfs) {
        this.statusfs = statusfs;
    }

    public String getApfs() {
        return apfs;
    }

    public void setApfs(String apfs) {
        this.apfs = apfs;
    }

    public String getTglfs() {
        return tglfs;
    }

    public void setTglfs(String tglfs) {
        this.tglfs = tglfs;
    }
}
