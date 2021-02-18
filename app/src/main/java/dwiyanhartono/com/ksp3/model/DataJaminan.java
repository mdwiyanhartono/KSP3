package dwiyanhartono.com.ksp3.model;

import com.google.gson.annotations.SerializedName;

public class DataJaminan {

    @SerializedName("f_jaminan")
    private String jaminan;
    @SerializedName("f_location")
    private String location;
    @SerializedName("f_value")
    private String value;

    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getJaminan() {
        return jaminan;
    }

    public void setJaminan(String jaminan) {
        this.jaminan = jaminan;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
