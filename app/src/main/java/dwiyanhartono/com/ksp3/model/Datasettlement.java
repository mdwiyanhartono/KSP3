package dwiyanhartono.com.ksp3.model;

import com.google.gson.annotations.SerializedName;

public class Datasettlement {

    @SerializedName("f_nama_nasabah")
    private String namanasabah;
    @SerializedName("f_cif")
    private String cif;
    @SerializedName("f_norefrence")
    private String norefrence;
    @SerializedName("f_tunggakan")
    private String tunggakan;
    @SerializedName("f_nominal_pelunasan")
    private String nominal;
    @SerializedName("statussettlement")
    private String statussettlement;


    public String getStatussettlement() {
        return statussettlement;
    }

    public void setStatussettlement(String statussettlement) {
        this.statussettlement = statussettlement;
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

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getNamanasabah() {
        return namanasabah;
    }

    public void setNamanasabah(String namanasabah) {
        this.namanasabah = namanasabah;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getTunggakan() {
        return tunggakan;
    }

    public void setTunggakan(String tunggakan) {
        this.tunggakan = tunggakan;
    }
}
