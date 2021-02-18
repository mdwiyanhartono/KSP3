package dwiyanhartono.com.ksp3.model;

public class RequestLocation {
    private String f_username;
    private String lat;
    private String lng;
    private String datetime;


    public RequestLocation(String f_username, String lat, String lng, String datetime) {
        this.f_username = f_username;
        this.lat = lat;
        this.lng = lng;
        this.datetime = datetime;
    }

    public String getF_username() {
        return f_username;
    }

    public void setF_username(String f_username) {
        this.f_username = f_username;
    }
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

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

}
