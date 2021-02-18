package dwiyanhartono.com.ksp3.model;

public class RequestLupapassword {
    private String f_username;
    private String f_email;

    public RequestLupapassword(String f_username, String f_email) {
        this.f_username = f_username;
        this.f_email = f_email;
    }

    public String getF_username() {
        return f_username;
    }

    public void setF_username(String f_username) {
        this.f_username = f_username;
    }

    public String getF_email() {
        return f_email;
    }

    public void setF_email(String f_email) {
        this.f_email = f_email;
    }
}
