package dwiyanhartono.com.ksp3.model;

import com.google.gson.annotations.SerializedName;

public class Datasub8 {

    @SerializedName("NamaDebitur")
    private String namaanggota;
    @SerializedName("Nomoranggota")
    private String cif;
    @SerializedName("ID")
    private String loanid;
    @SerializedName("FacilityType")
    private String jenisfasilitas;
    @SerializedName("PROCESSING_DATE")
    private String namaproduk;

    public Datasub8(String namaanggota, String cif, String loanid, String jenisfasilitas, String namaproduk) {
        this.namaanggota = namaanggota;
        this.cif = cif;
        this.loanid = loanid;
        this.jenisfasilitas = jenisfasilitas;
        this.namaproduk = namaproduk;
    }

    public String getNamaanggota() {
        return namaanggota;
    }

    public void setNamaanggota(String namaanggota) {
        this.namaanggota = namaanggota;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getLoanid() {
        return loanid;
    }

    public void setLoanid(String loanid) {
        this.loanid = loanid;
    }

    public String getJenisfasilitas() {
        return jenisfasilitas;
    }

    public void setJenisfasilitas(String jenisfasilitas) {
        this.jenisfasilitas = jenisfasilitas;
    }

    public String getNamaproduk() {
        return namaproduk;
    }

    public void setNamaproduk(String namaproduk) {
        this.namaproduk = namaproduk;
    }
}
