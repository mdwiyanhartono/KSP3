package dwiyanhartono.com.ksp3.model;

public class RequestActionplan {
    private String f_type;

    public RequestActionplan(String f_type) {
        this.f_type = f_type;
    }

    public String getType() {
        return f_type;
    }

    public void setType(String type) {
        this.f_type = type;
    }
}
