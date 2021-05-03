package dwiyanhartono.com.ksp3;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import dwiyanhartono.com.ksp3.api.ApiRequestData;
import dwiyanhartono.com.ksp3.api.Retroserver;
import dwiyanhartono.com.ksp3.base.BaseActivity;
import dwiyanhartono.com.ksp3.database.DBAdapter2;
import dwiyanhartono.com.ksp3.model.DataModelPlan;
import dwiyanhartono.com.ksp3.model.Dataplanvisit;
import dwiyanhartono.com.ksp3.model.ReqBodySubHal1;
import dwiyanhartono.com.ksp3.model.ResponsModelChangePswd;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityDetailPlanVisit extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterDetailPlanvisit.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    RecyclerView rv;
    AdapterDetailPlanvisit adapter;
    SwipeRefreshLayout swip;

    Conectiondetector conn;
    private List<DataModelPlan> mItems = new ArrayList<>();
    private List<Dataplanvisit> dataplanvisit = new ArrayList<>();

    String a = "";

    String statususer, id_user, CIF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_plan_visit);
        ceklog();
        Intent i = getIntent();
        CIF = i.getStringExtra("cif");
        if (statususer == null) {
            Intent e = new Intent(ActivityDetailPlanVisit.this, ActivityLogin.class);
            startActivity(e);
            finish();
        } else {
            swip = findViewById(R.id.swap);

            rv = findViewById(R.id.myRecycler);



            //SET ITS PROPS
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setItemAnimator(new DefaultItemAnimator());
            adapter = new AdapterDetailPlanvisit(this);
            rv.setAdapter(adapter);

            swip.post(new Runnable() {
                @Override
                public void run() {
                    retrieve();
                }
            });

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            swip.setOnRefreshListener(this);

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    private void ceklog() {
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        Cursor c = db.getUser();
        if (c.moveToFirst()) {
            id_user = c.getString(4);
            statususer = c.getString(4);
            /*nama = c.getString(0);
            email = c.getString(1);*/
        }
    }


    private void retrieve() {
        swip.setRefreshing(true);
        mItems.clear();
        dataplanvisit.clear();
        getdblocal();
    }

    public void getdblocal() {
        DBAdapter2 db = new DBAdapter2(this);
        //OPEN
        db.openDB();
        Cursor q = db.getdetailplanvisit(CIF);
        if (q.moveToFirst()) {
            do {
                String acctno1 = q.getString(0);
                String agentid1 = q.getString(1);
                String nama1 = q.getString(2);
                String bucket1 = q.getString(3);
                String bucketeom1 = q.getString(4);
                String cif1 = q.getString(5);
                String os1 = q.getString(6);
                String totaltunggakan1 = q.getString(7);
                String alamat1 = q.getString(8);
                String approved1 = q.getString(13);
                String dpd1 = q.getString(9);
                String alamatusaha1 = q.getString(10);
                String email1 = q.getString(12);
                String loanid1 = q.getString(11);
                String angsuran = q.getString(14);
                String notlp = q.getString(15);

                Dataplanvisit data = new Dataplanvisit(acctno1, agentid1, nama1, bucket1,
                        bucketeom1, cif1, os1, totaltunggakan1, alamat1, approved1, dpd1, alamatusaha1, email1, loanid1, angsuran, notlp);
                dataplanvisit.add(data);
            }
            while (q.moveToNext());
            adapter.setData(dataplanvisit);
            swip.setRefreshing(false);
        } else {
            swip.setRefreshing(false);
        }

        db.close();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_daily_plan_visit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_change) {
            Intent inten = new Intent(ActivityDetailPlanVisit.this, ChangePassword.class);
            startActivity(inten);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            /*Intent intent = new Intent(ActivityDailyPlanVisit.this,ActivityMain.class);
            startActivity(intent);*/
            finish();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(ActivityDetailPlanVisit.this, ActivityDetailPlanVisit.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(ActivityDetailPlanVisit.this, ActivityPTP.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {
            conn = new Conectiondetector(ActivityDetailPlanVisit.this);
            if (conn.isConected()) {
                notifLogout(1);
            } else {
                notifLogout(0);
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onButonVisitClick(String norek, String cif, String nama, String alamat, String alamatusaha, String email, String bucketeom, String dpd, String loanid, String totaldue, String angsuran, String notlp) {
        Intent inten = new Intent(this, ActivityInputKunjungan.class);
        inten.putExtra("norek", norek);
        inten.putExtra("cif", cif);
        inten.putExtra("nama", nama);
        inten.putExtra("alamat", alamat);
        inten.putExtra("alamatusaha", alamatusaha);
        inten.putExtra("email", email);
        inten.putExtra("bucketeom", bucketeom);
        inten.putExtra("dpd", dpd);
        inten.putExtra("loanid", loanid);
        inten.putExtra("totaldue", totaldue);
        inten.putExtra("angsuran", angsuran);
        inten.putExtra("notlp", notlp);
        startActivity(inten);
        finish();
    }



    @Override
    public void onRefresh() {
        retrieve();
    }
}
