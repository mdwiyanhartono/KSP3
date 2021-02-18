package dwiyanhartono.com.ksp3.model;

public class ReqBodyLogoutoffline {
    private String f_agentid;
    private String f_datetime;


    public ReqBodyLogoutoffline(String f_agentid, String f_datetime) {
        this.f_agentid = f_agentid;
        this.f_datetime = f_datetime;
    }

    public String getF_datetime() {
        return f_datetime;
    }

    public void setF_datetime(String f_datetime) {
        this.f_datetime = f_datetime;
    }



    public String getF_agentid() {
        return f_agentid;
    }

    public void setF_agentid(String f_agentid) {
        this.f_agentid = f_agentid;
    }
}
