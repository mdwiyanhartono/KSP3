package dwiyanhartono.com.ksp3.model;

import com.google.gson.annotations.SerializedName;

public class DataContact {

    @SerializedName("f_contact_1")
    private String contact1;
    @SerializedName("f_contact_2")
    private String contact2;
    @SerializedName("f_contact_3")
    private String contact3;
    @SerializedName("f_contact_4")
    private String contact4;
    @SerializedName("f_contact_5")
    private String contact5;
    @SerializedName("f_contact_6")
    private String contact6;
    @SerializedName("f_contact_7")
    private String contact7;

    public String getContact1() {
        return contact1;
    }

    public void setContact1(String contact1) {
        this.contact1 = contact1;
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }


    public String getContact4() {
        return contact4;
    }

    public void setContact4(String contact4) {
        this.contact4 = contact4;
    }

    public String getContact5() {
        return contact5;
    }

    public void setContact5(String contact5) {
        this.contact5 = contact5;
    }

    public String getContact3() {
        return contact3;
    }

    public void setContact3(String contact3) {
        this.contact3 = contact3;
    }

    public String getContact6() {
        return contact6;
    }

    public void setContact6(String contact6) {
        this.contact6 = contact6;
    }

    public String getContact7() {
        return contact7;
    }

    public void setContact7(String contact7) {
        this.contact7 = contact7;
    }
}
