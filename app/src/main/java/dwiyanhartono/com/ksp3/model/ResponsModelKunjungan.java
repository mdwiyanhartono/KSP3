package dwiyanhartono.com.ksp3.model;

import java.util.List;

/**
 * Created by hakiki95 on 4/16/2017.
 */

public class ResponsModelKunjungan {

    String  kode;
    List<DataKunjungan> result;

    public List<DataKunjungan> getResult() {
        return result;
    }

    public void setResult(List<DataKunjungan> result) {
        this.result = result;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

}
