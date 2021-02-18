package dwiyanhartono.com.ksp3;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
import dwiyanhartono.com.ksp3.api.ApiRequestData;
import dwiyanhartono.com.ksp3.api.Retroserver;
import dwiyanhartono.com.ksp3.base.BaseActivity;
import dwiyanhartono.com.ksp3.database.DBAdapter2;
import dwiyanhartono.com.ksp3.model.DataHistorical;
import dwiyanhartono.com.ksp3.model.DataHistory;
import dwiyanhartono.com.ksp3.model.ReqBodySubHal1;
import dwiyanhartono.com.ksp3.model.Requestcifloanid;
import dwiyanhartono.com.ksp3.model.RespondModelHistorical;
import dwiyanhartono.com.ksp3.model.ResponsModelChangePswd;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityHistorycal extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterHistory.OnItemClickListener {

    RecyclerView rv, rcfs, rvrs, rcsp;
    AdapterHistory adapter;

    private List<DataHistorical> mItems = new ArrayList<>();
    private List<DataHistory> datahistory = new ArrayList<>();

    String cif, loanid;
    Conectiondetector conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historycal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ceklog();

        Intent i = getIntent();
        cif = i.getExtras().getString("cif");
        loanid = i.getExtras().getString("loanid");

        retrieve();

        rv = (RecyclerView) findViewById(R.id.myRecycler);
        //SET ITS PROPS
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        adapter = new AdapterHistory(this);
        rv.setAdapter(adapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void retrieve() {
//        mItems.clear();
        /*dataplanvisit.clear();*/
        conn = new Conectiondetector(ActivityHistorycal.this);
        if (conn.isConected()) {
            ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
            Call<RespondModelHistorical> getdata = api.sendHistory(new Requestcifloanid(cif, loanid));
            getdata.enqueue(new Callback<RespondModelHistorical>() {
                @Override
                public void onResponse(Call<RespondModelHistorical> call, Response<RespondModelHistorical> response) {
                    String kode = response.body().getKode();

//                    Toast.makeText(ActivityHistorycal.this, kode, Toast.LENGTH_SHORT).show();

                    if (kode.equals("1")) {
                        mItems = response.body().getResult();
//                        Toast.makeText(ActivityHistorycal.this, "asda", Toast.LENGTH_SHORT).show();
                        /*for (int i = 0; i < mItems.size(); i++) {
                            String kunjungan = mItems.get(i).getKunjungan();
                            String totalbayar = mItems.get(i).getTotalbayar();
                            String bertemu = mItems.get(i).getBertemu();
                            String lokasibertemu = mItems.get(i).getLokasibertemu();
                            String karakter = mItems.get(i).getKarakter();
                            String resumenasabah = mItems.get(i).getResumenasabah();
                            String actionplan = mItems.get(i).getActionplan();
                            String tglvisit = mItems.get(i).getTglvisit();

                            DataHistory data = new DataHistory(kunjungan, totalbayar, bertemu, lokasibertemu,
                                    karakter, resumenasabah, actionplan,tglvisit);

                            datahistory.add(data);
                        }*/
                        adapter.setData(mItems);
                    } else {
                        Toast.makeText(ActivityHistorycal.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RespondModelHistorical> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(ActivityHistorycal.this, "error code : hs "+t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            /*DBAdapter db = new DBAdapter(this);
            //OPEN
            db.openDB();
            Cursor q = db.getdailyplanvisit("0");
            datahistory.add((DataHistory) q);
            Toast.makeText(ActivityHistorycal.this, "", Toast.LENGTH_SHORT).show();
            db.close();*/
           /* DBAdapter db = new DBAdapter(this);
            //OPEN
            db.openDB();
            Cursor q = db.getdailyplanvisit("0");
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
                    String approved1 = "";
                    String dpd1 = q.getString(9);
                    String alamatusaha1 = q.getString(9);
                    String email1 = q.getString(11);
                    String loanid1 = q.getString(10);

                    Toast.makeText(ActivityHistorycal.this, acctno1, Toast.LENGTH_SHORT).show();

                    Dataplanvisit data = new Dataplanvisit(acctno1, agentid1, nama1, bucket1,
                            bucketeom1, cif1, os1, totaltunggakan1, alamat1, approved1, dpd1, alamatusaha1, email1, loanid1);

                    dataplanvisit.add(data);
               *//* Constants.norek,Constants.AGENTID,  Constants.nama, Constants.bucket, Constants.bucketeom, Constants.cif,
               Constants.Os, Constants.totaltunggakan,
                        Constants.almtrm, Constants.DPD, Constants.almtush,Constants.LOAN, Constants.email*//*
                }
                while (q.moveToNext());
            }

            db.close();
*/
        }

    }

    String statususer, id_user;

    private void ceklog() {
        DBAdapter2 db = new DBAdapter2(this);

        //OPEN
        db.openDB();


        //SELECT
        Cursor c = db.getUser();
        if (c.moveToFirst()) {
            statususer = c.getString(3);
            id_user = c.getString(4);
        }

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
        getMenuInflater().inflate(R.menu.activity_historycal, menu);
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
//            DBAdapter db=new DBAdapter(this);
//            db.openDB();
//            db.Deleteuser();
//            db.close();
//            Intent inten = new Intent(ActivityHistorycal.this, SplashScreen.class);
//            startActivity(inten);
//            finish();
//            return true;
//        }
        if (id == R.id.action_change) {
            Intent inten = new Intent(ActivityHistorycal.this, ChangePassword.class);
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
            /*Intent intent = new Intent(ActivityHistorycal.this,ActivityMain.class);
            startActivity(intent);*/
            finish();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(ActivityHistorycal.this, ActivityDailyPlanVisit.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(ActivityHistorycal.this, ActivityPTP.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {
            conn = new Conectiondetector(ActivityHistorycal.this);
            if (conn.isConected()) {
                ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
                Call<ResponsModelChangePswd> getdata = api.sendLogout(new ReqBodySubHal1(id_user));
                getdata.enqueue(new Callback<ResponsModelChangePswd>() {
                    @Override
                    public void onResponse(Call<ResponsModelChangePswd> call, Response<ResponsModelChangePswd> response) {
                        String kode = response.body() != null ? response.body().getKode() : "9";
                        if (kode.equals("1")) {
                            DBAdapter2 db = new DBAdapter2(ActivityHistorycal.this);
                            db.openDB();
                            db.Deleteuser();
                            db.deleteparameter();
                            db.deletekunjunganall("1");
                            db.deletekpall();
                            db.deleteselfcuredall();
                            db.close();
                            Intent inten = new Intent(ActivityHistorycal.this, SplashScreen.class);
                            startActivity(inten);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModelChangePswd> call, Throwable t) {
                        DBAdapter2 db = new DBAdapter2(ActivityHistorycal.this);
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
                        Intent inten = new Intent(ActivityHistorycal.this, SplashScreen.class);
                        startActivity(inten);
                        finish();
                    }
                });
            } else {
                DBAdapter2 db = new DBAdapter2(ActivityHistorycal.this);
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
                Intent inten = new Intent(ActivityHistorycal.this, SplashScreen.class);
                startActivity(inten);
                finish();
            }
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
