package dwiyanhartono.com.ksp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import dwiyanhartono.com.ksp3.base.BaseActivity;
import dwiyanhartono.com.ksp3.model.DataModelPlan;
import dwiyanhartono.com.ksp3.model.Dataplanvisit;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class LayoutFasilitas extends BaseActivity {

    RecyclerView rv;
    AdapterPlanvisit adapter;
    SwipeRefreshLayout swip;

    Conectiondetector conn;
    private List<DataModelPlan> mItems = new ArrayList<>();
    private List<Dataplanvisit> dataplanvisit = new ArrayList<>();

    String a = "";

    String statususer, id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_fasilitas);
    }
}