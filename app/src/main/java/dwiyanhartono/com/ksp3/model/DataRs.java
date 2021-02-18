package dwiyanhartono.com.ksp3.model;

import com.google.gson.annotations.SerializedName;

public class DataRs {

    @SerializedName("f_restrukturisasike")
    private String restrukturisasike;
    @SerializedName("f_graceperiode")
    private String graceperiode;
    @SerializedName("f_jangkawaktugp")
    private String jangkawaktugp;

    public String getRestrukturisasike() {
        return restrukturisasike;
    }

    public void setRestrukturisasike(String restrukturisasike) {
        this.restrukturisasike = restrukturisasike;
    }

    public String getGraceperiode() {
        return graceperiode;
    }

    public void setGraceperiode(String graceperiode) {
        this.graceperiode = graceperiode;
    }

    public String getJangkawaktugp() {
        return jangkawaktugp;
    }

    public void setJangkawaktugp(String jangkawaktugp) {
        this.jangkawaktugp = jangkawaktugp;
    }
}
