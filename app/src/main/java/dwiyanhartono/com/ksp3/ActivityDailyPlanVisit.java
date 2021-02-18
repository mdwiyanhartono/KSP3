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

public class ActivityDailyPlanVisit extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterPlanvisit.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

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
        setContentView(R.layout.activity_daily_plan_visit);
        ceklog();
        if (statususer == null) {
            Intent i = new Intent(ActivityDailyPlanVisit.this, ActivityLogin.class);
            startActivity(i);
            finish();
        } else {
            swip = findViewById(R.id.swap);

            rv = findViewById(R.id.myRecycler);
            //SET ITS PROPS
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setItemAnimator(new DefaultItemAnimator());
            adapter = new AdapterPlanvisit(this);
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

//        conn = new Conectiondetector(ActivityDailyPlanVisit.this);
//        if (conn.isConected()) {
//            ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
//            Call<ResponsModelPlan> getdata = api.sendPlanvisit(new ReqBodyPlanVisit(id_user));
//            getdata.enqueue(new Callback<ResponsModelPlan>() {
//                @Override
//                public void onResponse(Call<ResponsModelPlan> call, Response<ResponsModelPlan> response) {
//                    String kode = response.body().getKode();
//
//                    if (kode.equals("1")) {
//                        mItems = response.body().getResult();
//
//                        for (int i = 0; i < mItems.size(); i++) {
//                            String acctno = mItems.get(i).getAcctno();
//                            String cif = mItems.get(i).getCif();
//                            String nama = mItems.get(i).getNama();
//                            String alamat = mItems.get(i).getAlamat();
//                            String alamatusaha = mItems.get(i).getAlamatusaha();
//                            String notlp = mItems.get(i).getNotlp();
//                            String email = mItems.get(i).getEmail();
//
//                            String agentid = mItems.get(i).getAgentid();
//                            String bucket = mItems.get(i).getBucket();
//                            String bucketeom = mItems.get(i).getBucketeom();
//                            String os = mItems.get(i).getOs();
//                            String totaltunggakan = mItems.get(i).getTotaltunggakan();
//                            String approved = mItems.get(i).getApproved();
//                            String dpd = mItems.get(i).getDpd();
//                            String loanid = mItems.get(i).getLoanid();
//                            String angsuran = mItems.get(i).getAngsuran();
//
//                            Dataplanvisit data = new Dataplanvisit(acctno, agentid, nama, bucket,
//                                    bucketeom, cif, os, totaltunggakan, alamat, approved, dpd, alamatusaha, email, loanid,angsuran,notlp);
//
//                            dataplanvisit.add(data);
//                        }
//
//                        adapter.setData(dataplanvisit);
//                    } else {
//                        getdblocal();
//                        Toast.makeText(ActivityDailyPlanVisit.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
//                    }
//                    swip.setRefreshing(false);
//                }
//
//                @Override
//                public void onFailure(Call<ResponsModelPlan> call, Throwable t) {
//                    t.printStackTrace();
//                    Toast.makeText(ActivityDailyPlanVisit.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                    getdblocal();
//                    swip.setRefreshing(false);
//                }
//            });
//        } else {
//            getdblocal();
//            swip.setRefreshing(false);
//        }

    }

    public void getdblocal() {
        DBAdapter2 db = new DBAdapter2(this);
        //OPEN
        db.openDB();
        Cursor q = db.getdailyplanvisit(id_user);
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

//                Toast.makeText(ActivityDailyPlanVisit.this, acctno1, Toast.LENGTH_SHORT).show();

                Dataplanvisit data = new Dataplanvisit(acctno1, agentid1, nama1, bucket1,
                        bucketeom1, cif1, os1, totaltunggakan1, alamat1, approved1, dpd1, alamatusaha1, email1, loanid1,angsuran,notlp);

                dataplanvisit.add(data);

               /* Constants.norek,Constants.AGENTID,  Constants.nama, Constants.bucket, Constants.bucketeom, Constants.cif,
               Constants.Os, Constants.totaltunggakan,
                        Constants.almtrm, Constants.DPD, Constants.almtush,Constants.LOAN, Constants.email*/
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

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            DBAdapter db = new DBAdapter(this);
//            db.openDB();
//            db.Deleteuser();
//            db.close();
//            Intent inten = new Intent(ActivityDailyPlanVisit.this, SplashScreen.class);
//            startActivity(inten);
//            finish();
//            return true;
//        }
        if (id == R.id.action_change) {
            Intent inten = new Intent(ActivityDailyPlanVisit.this, ChangePassword.class);
            startActivity(inten);
//            finish();
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
            Intent intent = new Intent(ActivityDailyPlanVisit.this, ActivityDailyPlanVisit.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(ActivityDailyPlanVisit.this, ActivityPTP.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {
            conn = new Conectiondetector(ActivityDailyPlanVisit.this);
            if (conn.isConected()) {
                ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
                Call<ResponsModelChangePswd> getdata = api.sendLogout(new ReqBodySubHal1(id_user));
                getdata.enqueue(new Callback<ResponsModelChangePswd>() {
                    @Override
                    public void onResponse(Call<ResponsModelChangePswd> call, Response<ResponsModelChangePswd> response) {
                        String kode = response.body() != null ? response.body().getKode() : "9";
                        if (kode.equals("1")) {
                            DBAdapter2 db = new DBAdapter2(ActivityDailyPlanVisit.this);
                            db.openDB();
                            db.Deleteuser();
                            db.deleteparameter();
//                            db.deleteaccount();
                            db.deletekunjunganall("1");
                            db.deletekpall();
                            db.deleteselfcuredall();
                            db.close();
                            Intent inten = new Intent(ActivityDailyPlanVisit.this, SplashScreen.class);
                            startActivity(inten);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModelChangePswd> call, Throwable t) {
                        DBAdapter2 db = new DBAdapter2(ActivityDailyPlanVisit.this);
                        db.openDB();
                        String date = new SimpleDateFormat("yyyy_MM_dd_HHmmss", Locale.getDefault()).format(new Date());
                        long result = db.addlogout(id_user, date, "1");
                        if (result > 0) {
                            db.Deleteuser();
                            db.deleteparameter();
                            db.deletekunjunganall("1");
                            db.deletekpall();
                            db.deleteselfcuredall();
                        }
                        db.close();
                        Intent inten = new Intent(ActivityDailyPlanVisit.this, SplashScreen.class);
                        startActivity(inten);
                        finish();
                    }
                });
            } else {
                DBAdapter2 db = new DBAdapter2(ActivityDailyPlanVisit.this);
                db.openDB();
                String date = new SimpleDateFormat("yyyy_MM_dd_HHmmss", Locale.getDefault()).format(new Date());
                long result = db.addlogout(id_user, date, "1");
                if (result > 0) {
                    db.Deleteuser();
                    db.deleteparameter();
                    db.deletekunjunganall("1");
                    db.deletekpall();
                    db.deleteselfcuredall();
                }
                db.close();
                Intent inten = new Intent(ActivityDailyPlanVisit.this, SplashScreen.class);
                startActivity(inten);
                finish();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(String id) {

    }

    @Override
    public void onButonDetailClick(String cif) {
        Intent inten = new Intent(this, ActivityDetailInventory.class);
        inten.putExtra("cif", cif);
        startActivity(inten);
    }

    @Override
    public void onButonVisitClick(String cif, String nama, String alamat, String alamatusaha, String email, String bucketeom, String dpd, String loanid, String totaldue,String angsuran,String notlp) {
        Intent inten = new Intent(this, ActivityInputKunjungan.class);
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
    public void onButonHistoryClick(String loan, String cif) {
        Intent inten = new Intent(this, ActivityHistorycal.class);
        inten.putExtra("cif", cif);
        inten.putExtra("loanid", loan);
        startActivity(inten);

    }

    @Override
    public void onButonMapsClick(String cif, String alamat) {
        Intent inten = new Intent(this, ActivityMapsTracking.class);
        inten.putExtra("cif", cif);
        inten.putExtra("alamat", alamat);
        startActivity(inten);

    }

    @Override
    public void onRefresh() {
        retrieve();
    }
}
