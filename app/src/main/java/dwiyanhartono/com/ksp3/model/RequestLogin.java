package dwiyanhartono.com.ksp3.model;

public class RequestLogin {
    private String f_username;
    private String f_password;
    private String f_imei;

    public RequestLogin(String f_username, String f_password,String f_imei) {
        this.f_username = f_username;
        this.f_password = f_password;
        this.f_imei = f_imei;
    }

    public String getF_imei() {
        return f_imei;
    }

    public void setF_imei(String f_imei) {
        this.f_imei = f_imei;
    }

    public String getF_username() {
        return f_username;
    }

    public void setF_username(String f_username) {
        this.f_username = f_username;
    }

    public String getF_password() {
        return f_password;
    }

    public void setF_password(String f_password) {
        this.f_password = f_password;
    }
}
