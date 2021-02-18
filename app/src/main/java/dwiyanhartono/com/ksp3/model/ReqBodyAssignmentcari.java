package dwiyanhartono.com.ksp3.model;

public class ReqBodyAssignmentcari {
    private String f_agentid ;
    private String f_string;

    public ReqBodyAssignmentcari(String f_agentid, String f_string) {
        this.f_agentid = f_agentid;
        this.f_string = f_string;
    }

    public String getF_string() {
        return f_string;
    }

    public void setF_string(String f_string) {
        this.f_string = f_string;
    }
    public String getF_agentid() {
        return f_agentid;
    }

    public void setF_agentid(String f_agentid) {
        this.f_agentid = f_agentid;
    }
}
