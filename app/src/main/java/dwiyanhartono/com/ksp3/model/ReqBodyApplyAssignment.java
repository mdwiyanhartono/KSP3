package dwiyanhartono.com.ksp3.model;

import java.util.List;

public class ReqBodyApplyAssignment {
    private List<String> selectedDebitur;
    private String id_user;

    public ReqBodyApplyAssignment(List<String> selectedDebitur, String id_user) {
        this.selectedDebitur = selectedDebitur;
        this.id_user = id_user;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public List<String> getSelectedDebitur() {
        return selectedDebitur;
    }

    public void setSelectedDebitur(List<String> selectedDebitur) {
        this.selectedDebitur = selectedDebitur;
    }
}
