package dwiyanhartono.com.ksp3.model;

public class RequestChangepsd {

    private String f_agentid;
    private String f_password;
    private String f_passwordnew;

    public RequestChangepsd(String f_password, String f_passwordnew, String f_agentid) {
        this.f_password = f_password;
        this.f_passwordnew = f_passwordnew;
        this.f_agentid= f_agentid;
    }

    public String getF_agentid() {
        return f_agentid;
    }

    public void setF_agentid(String f_agentid) {
        this.f_agentid = f_agentid;
    }

    public String getF_password() {
        return f_password;
    }

    public void setF_password(String f_password) {
        this.f_password = f_password;
    }

    public String getF_passwordnew() {
        return f_passwordnew;
    }

    public void setF_passwordnew(String f_passwordnew) {
        this.f_passwordnew = f_passwordnew;
    }

}
