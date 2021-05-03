package dwiyanhartono.com.ksp3.model;

import java.util.List;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ResponsModelSettlement {

    String  kode;
    String  message;
    List<Datasettlement> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datasettlement> getResult() {
        return result;
    }

    public void setResult(List<Datasettlement> result) {
        this.result = result;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

}
