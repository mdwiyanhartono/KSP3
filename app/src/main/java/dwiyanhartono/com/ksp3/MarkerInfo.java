package dwiyanhartono.com.ksp3;

public class MarkerInfo {
    private String cif;
    private String acctNo;

    public MarkerInfo(String cif, String acctNo) {
        this.cif = cif;
        this.acctNo = acctNo;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }
}
