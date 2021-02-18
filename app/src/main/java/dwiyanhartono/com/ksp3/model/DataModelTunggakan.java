package dwiyanhartono.com.ksp3.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class DataModelTunggakan {
    @SerializedName("cif")
    private String cif;
    @SerializedName("ID")
    private String id;
    @SerializedName("PD_ID")
    private String pdid;
    @SerializedName("TOTAL_DUE")
    private String totaldue;
    @SerializedName("INSTALLMENT_DATE")
    private String installmentdate;
    @SerializedName("DUE_PR")
    private String duepr;
    @SerializedName("DUE_IN")
    private String duein;
    @SerializedName("DUE_CH")
    private String duech;
    @SerializedName("NO_DAYS_OD")
    private String nodaysod;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPdid() {
        return pdid;
    }

    public void setPdid(String pdid) {
        this.pdid = pdid;
    }

    public String getTotaldue() {
        return totaldue;
    }

    public void setTotaldue(String totaldue) {
        this.totaldue = totaldue;
    }

    public String getInstallmentdate() {
        return installmentdate;
    }

    public void setInstallmentdate(String installmentdate) {
        this.installmentdate = installmentdate;
    }

    public String getDuepr() {
        return duepr;
    }

    public void setDuepr(String duepr) {
        this.duepr = duepr;
    }

    public String getDuein() {
        return duein;
    }

    public void setDuein(String duein) {
        this.duein = duein;
    }

    public String getDuech() {
        return duech;
    }

    public void setDuech(String duech) {
        this.duech = duech;
    }

    public String getNodaysod() {
        return nodaysod;
    }

    public void setNodaysod(String nodaysod) {
        this.nodaysod = nodaysod;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }
}
