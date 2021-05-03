package dwiyanhartono.com.ksp3.model;

import com.google.gson.annotations.SerializedName;

public class DataCekSaldo {

    @SerializedName("balance")
    private String balance;
    @SerializedName("responseDescription")
    private String responseDescription;
    @SerializedName("accountName")
    private String accountName;
    @SerializedName("accountNumber")
    private String accountNumber;
    @SerializedName("responseCode")
    private String code;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
