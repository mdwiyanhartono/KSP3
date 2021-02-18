package dwiyanhartono.com.ksp3.model;

import com.google.gson.annotations.SerializedName;

public class DataSp {

    @SerializedName("f_sp_code")
    private String spke;
    @SerializedName("f_tgl_cetak")
    private String tglsp;
    @SerializedName("f_sp")
    private String speke;
    @SerializedName("f_tgl_terima")
    private String tglspeke;

    public String getSpke() {
        return spke;
    }

    public void setSpke(String spke) {
        this.spke = spke;
    }

    public String getTglsp() {
        return tglsp;
    }

    public void setTglsp(String tglsp) {
        this.tglsp = tglsp;
    }

    public String getSpeke() {
        return speke;
    }

    public void setSpeke(String speke) {
        this.speke = speke;
    }

    public String getTglspeke() {
        return tglspeke;
    }

    public void setTglspeke(String tglspeke) {
        this.tglspeke = tglspeke;
    }
}
