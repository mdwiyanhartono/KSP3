package dwiyanhartono.com.ksp3.model;

import com.google.gson.annotations.SerializedName;

public class Datasettlement {

    @SerializedName("f_nama_anggota")
    private String namaanggota;
    @SerializedName("f_id")
    private String id;
    @SerializedName("f_cif")
    private String cif;
    @SerializedName("f_ld")
    private String ld;
    @SerializedName("f_norefrence")
    private String norefrence;
    @SerializedName("f_tunggakan")
    private String tunggakan;
    @SerializedName("f_nominal_pelunasan")
    private String nominal;
    @SerializedName("norek")
    private String norek;
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getNorek() {
        return norek;
    }

    public void setNorek(String norek) {
        this.norek = norek;
    }

    public String getNorefrence() {
        return norefrence;
    }

    public void setNorefrence(String norefrence) {
        this.norefrence = norefrence;
    }

    public String getNominal() {
        return nominal;
    }

    public String getLd() {
        return ld;
    }

    public void setLd(String ld) {
        this.ld = ld;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTunggakan() {
        return tunggakan;
    }

    public void setTunggakan(String tunggakan) {
        this.tunggakan = tunggakan;
    }
}
