package dwiyanhartono.com.ksp3.model;

import java.util.List;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ResponsModelDetailAccount {

    String  kode;
    List<DataModelDetailAccount> result;

    public List<DataModelDetailAccount> getResult() {
        return result;
    }

    public void setResult(List<DataModelDetailAccount> result) {
        this.result = result;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

}
