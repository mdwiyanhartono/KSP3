package dwiyanhartono.com.ksp3.model;

import com.google.gson.annotations.SerializedName;

public class DataLocation {

    @SerializedName("f_lat")
    private String lat;
    @SerializedName("f_long")
    private String lng;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
