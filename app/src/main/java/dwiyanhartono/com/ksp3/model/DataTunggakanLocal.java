package dwiyanhartono.com.ksp3.model;

import com.google.gson.annotations.SerializedName;

public class DataTunggakanLocal {


    @SerializedName("f_loanid")
    private String ID;
    @SerializedName("f_pd_id")
    private String PD_ID;
    @SerializedName("f_total_due")
    private String TD;
    @SerializedName("f_instalment_date")
    private String INSTALMENT_DATE;
    @SerializedName("f_due_pr")
    private String DUE_PR;
    @SerializedName("f_due_in")
    private String DUE_IN;
    @SerializedName("f_due_ch")
    private String DUE_CH;
    @SerializedName("f_days_od")
    private String NO_DAYS_OD;

    public DataTunggakanLocal(String ID, String PD_ID, String TD, String INSTALMENT_DATE, String DUE_PR, String DUE_IN, String DUE_CH, String NO_DAYS_OD) {
        this.ID = ID;
        this.PD_ID = PD_ID;
        this.TD = TD;
        this.INSTALMENT_DATE = INSTALMENT_DATE;
        this.DUE_PR = DUE_PR;
        this.DUE_IN = DUE_IN;
        this.DUE_CH = DUE_CH;
        this.NO_DAYS_OD = NO_DAYS_OD;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPD_ID() {
        return PD_ID;
    }

    public void setPD_ID(String PD_ID) {
        this.PD_ID = PD_ID;
    }

    public String getTD() {
        return TD;
    }

    public void setTD(String TD) {
        this.TD = TD;
    }

    public String getINSTALMENT_DATE() {
        return INSTALMENT_DATE;
    }

    public void setINSTALMENT_DATE(String INSTALMENT_DATE) {
        this.INSTALMENT_DATE = INSTALMENT_DATE;
    }

    public String getDUE_PR() {
        return DUE_PR;
    }

    public void setDUE_PR(String DUE_PR) {
        this.DUE_PR = DUE_PR;
    }

    public String getDUE_IN() {
        return DUE_IN;
    }

    public void setDUE_IN(String DUE_IN) {
        this.DUE_IN = DUE_IN;
    }

    public String getDUE_CH() {
        return DUE_CH;
    }

    public void setDUE_CH(String DUE_CH) {
        this.DUE_CH = DUE_CH;
    }

    public String getNO_DAYS_OD() {
        return NO_DAYS_OD;
    }

    public void setNO_DAYS_OD(String NO_DAYS_OD) {
        this.NO_DAYS_OD = NO_DAYS_OD;
    }
}
