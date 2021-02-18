package dwiyanhartono.com.ksp3.model;

import com.google.gson.annotations.SerializedName;

public class DataFasilitas {

    @SerializedName("ket_facility")
    private String fasilitas;
    @SerializedName("PlafondAmount")
    private String plafon;
    @SerializedName("BakiDebet")
    private String os;
    @SerializedName("col")
    private String booked;
    @SerializedName("Dateapproved")
    private String expprocess;
    @SerializedName("date_expired")
    private String expdate;
    @SerializedName("flag_probiz")
    private String flag;

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public String getPlafon() {
        return plafon;
    }

    public void setPlafon(String plafon) {
        this.plafon = plafon;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBooked() {
        return booked;
    }

    public void setBooked(String booked) {
        this.booked = booked;
    }

    public String getExpprocess() {
        return expprocess;
    }

    public void setExpprocess(String expprocess) {
        this.expprocess = expprocess;
    }

    public String getExpdate() {
        return expdate;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
