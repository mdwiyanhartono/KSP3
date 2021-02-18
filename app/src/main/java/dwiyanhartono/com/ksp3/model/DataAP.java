package dwiyanhartono.com.ksp3.model;

import com.google.gson.annotations.SerializedName;

public class DataAP {

    @SerializedName("f_desc")
    private String desc;
    @SerializedName("f_value")
    private String value;
    @SerializedName("f_type")
    private String type;
    @SerializedName("f_status")
    private String status;
    @SerializedName("f_code")
    private String code;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
