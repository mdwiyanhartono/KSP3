package dwiyanhartono.com.ksp3;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
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
import dwiyanhartono.com.ksp3.model.DataFs;
import dwiyanhartono.com.ksp3.model.DataRs;
import dwiyanhartono.com.ksp3.model.DataSp;
import dwiyanhartono.com.ksp3.model.ReqBodySubHal1;
import dwiyanhartono.com.ksp3.model.Requestcontact;
import dwiyanhartono.com.ksp3.model.ResponsModelChangePswd;
import dwiyanhartono.com.ksp3.model.ResponsModelFs;
import dwiyanhartono.com.ksp3.model.ResponsModelRs;
import dwiyanhartono.com.ksp3.model.ResponsModelSp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityDetailHistorycal extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView agent, bertemutxt, lokasitxt, karaktertxt, resumetxt, ationplan;
    TextView dateplan, kunjungantxt, nominalbayartxt;
    private List<DataRs> mItems = new ArrayList<>();
    private List<DataSp> sp = new ArrayList<>();
    private List<DataFs> fs = new ArrayList<>();
    RecyclerView rcfs, rcrs, rcsp;
    Adapterrowtable_rs adapter;
    Adapterrowtable_sp adapter2;
    Adapterrowtable_fs adapter3;
    String code_image, cif ,tglvisit , agentname, loanid;

    Conectiondetector conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_historycal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ceklog();

        kunjungantxt = (TextView) findViewById(R.id.kunjungantxt);
        nominalbayartxt = (TextView) findViewById(R.id.nominalbayartxt);
        bertemutxt = (TextView) findViewById(R.id.bertemutxt);
        lokasitxt = (TextView) findViewById(R.id.lokasitxt);
        karaktertxt = (TextView) findViewById(R.id.karaktertxt);
        resumetxt = (TextView) findViewById(R.id.resumetxt);
        ationplan = (TextView) findViewById(R.id.ationplan);
        agent = (TextView) findViewById(R.id.collectiontxt);
        dateplan = (TextView) findViewById(R.id.ationplandate);


        //        intent.putExtra("loanid",loanid);
//        intent.putExtra("agentname",agentname);
//        intent.putExtra("agentid",agentid);
        Intent i = getIntent();
        kunjungantxt.setText(i.getExtras().getString("hasil"));
        tglvisit = i.getExtras().getString("tglvisit");
        nominalbayartxt.setText(i.getExtras().getString("bayar"));
        bertemutxt.setText(i.getExtras().getString("bertemu"));
        lokasitxt.setText(i.getExtras().getString("lokasi"));
        karaktertxt.setText(i.getExtras().getString("karakter"));
        resumetxt.setText(i.getExtras().getString("resume"));
        ationplan.setText(i.getExtras().getString("actionplan"));
        cif = i.getExtras().getString("cif");
        loanid = i.getExtras().getString("loanid");
        agentname = i.getExtras().getString("agentname");
        code_image = i.getExtras().getString("code_image");
        String dateplans = i.getExtras().getString("dateplan");
        dateplan.setText(dateplans);
        agent.setText(agentname);
//        Toast.makeText(this, cif, Toast.LENGTH_SHORT).show();



        rcfs = (RecyclerView) findViewById(R.id.myRecycler);
        //SET ITS PROPS
        rcfs.setLayoutManager(new LinearLayoutManager(this));
        rcfs.setItemAnimator(new DefaultItemAnimator());
        adapter = new Adapterrowtable_rs(this);
        rcfs.setAdapter(adapter);

        retrieve();
        retrievesp();
        retrievefs();


        rcsp = (RecyclerView) findViewById(R.id.myRecycler2);
        //SET ITS PROPS
        rcsp.setLayoutManager(new LinearLayoutManager(this));
        rcsp.setItemAnimator(new DefaultItemAnimator());
        adapter2 = new Adapterrowtable_sp(this);
        rcsp.setAdapter(adapter2);


        rcfs = (RecyclerView) findViewById(R.id.myRecycler3);
        //SET ITS PROPS
        rcfs.setLayoutManager(new LinearLayoutManager(this));
        rcfs.setItemAnimator(new DefaultItemAnimator());
        adapter3 = new Adapterrowtable_fs(this);
        rcfs.setAdapter(adapter3);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void retrievefs() {
        mItems.clear();
        /*dataplanvisit.clear();*/
        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModelFs> getdata = api.viewfs(new Requestcontact(cif));
        getdata.enqueue(new Callback<ResponsModelFs>() {
            @Override
            public void onResponse(Call<ResponsModelFs> call, Response<ResponsModelFs> response) {
                String kode = response.body().getKode();

//                Toast.makeText(ActivityDetailHistorycal.this, kode, Toast.LENGTH_SHORT).show();

                if (kode.equals("1")) {
                    fs = response.body().getResult();


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
                    adapter3.setData(fs);
                } else {
                    Toast.makeText(ActivityDetailHistorycal.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsModelFs> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(ActivityDetailHistorycal.this, "Error code : fs"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void retrievesp() {
        mItems.clear();
        /*dataplanvisit.clear();*/
        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModelSp> getdata = api.viewsp(new Requestcontact(cif));
        getdata.enqueue(new Callback<ResponsModelSp>() {
            @Override
            public void onResponse(Call<ResponsModelSp> call, Response<ResponsModelSp> response) {
                String kode = response.body() != null ? response.body().getKode() : "9";

//                Toast.makeText(ActivityDetailHistorycal.this, kode, Toast.LENGTH_SHORT).show();

                if (kode.equals("1")) {
                    sp = response.body().getResult();
                    adapter2.setData(sp);
                } else {
                    Toast.makeText(ActivityDetailHistorycal.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsModelSp> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(ActivityDetailHistorycal.this, "Resnpose code : df_02", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void retrieve() {
        mItems.clear();
        /*dataplanvisit.clear();*/
        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModelRs> getdata = api.viewrs(new Requestcontact(cif));
        getdata.enqueue(new Callback<ResponsModelRs>() {
            @Override
            public void onResponse(Call<ResponsModelRs> call, Response<ResponsModelRs> response) {
                String kode = response.body() != null ? response.body().getKode() : "9";
                if (kode.equals("1")) {
                    mItems = response.body().getResult();
                    adapter.setData(mItems);
                } else {
                    Toast.makeText(ActivityDetailHistorycal.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsModelRs> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(ActivityDetailHistorycal.this, "Resnponse code : ad_RSerr_02", Toast.LENGTH_LONG).show();
            }
        });
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
        getMenuInflater().inflate(R.menu.activity_detail_historycal, menu);
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
//            Intent inten = new Intent(ActivityDetailHistorycal.this, SplashScreen.class);
//            startActivity(inten);
//            finish();
//            return true;
//        }
        if(id == R.id.action_change){
            Intent inten = new Intent(ActivityDetailHistorycal.this, ChangePassword.class);
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
            /*Intent intent = new Intent(ActivityDailyPlanVisit.this,ActivityDetailHistorycal.class);
            startActivity(intent);*/
            finish();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(ActivityDetailHistorycal.this, ActivityDailyPlanVisit.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(ActivityDetailHistorycal.this, ActivityPTP.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            conn = new Conectiondetector(ActivityDetailHistorycal.this);
            if (conn.isConected()) {
                ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
                Call<ResponsModelChangePswd> getdata = api.sendLogout(new ReqBodySubHal1(id_user));
                getdata.enqueue(new Callback<ResponsModelChangePswd>() {
                    @Override
                    public void onResponse(Call<ResponsModelChangePswd> call, Response<ResponsModelChangePswd> response) {
                        String kode = response.body() != null ? response.body().getKode() : null;
                        if (kode.equals("1")) {
                            DBAdapter2 db = new DBAdapter2(ActivityDetailHistorycal.this);
                            db.openDB();
                            db.Deleteuser();
                            db.deleteparameter();
                            db.deletekunjunganall("1");
                            db.deletekpall();
                            db.deleteselfcuredall();
                            db.close();
                            Intent inten = new Intent(ActivityDetailHistorycal.this, SplashScreen.class);
                            startActivity(inten);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModelChangePswd> call, Throwable t) {
                        DBAdapter2 db = new DBAdapter2(ActivityDetailHistorycal.this);
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
                        Intent inten = new Intent(ActivityDetailHistorycal.this, SplashScreen.class);
                        startActivity(inten);
                        finish();
                    }
                });
            } else {
                DBAdapter2 db = new DBAdapter2(ActivityDetailHistorycal.this);
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
                Intent inten = new Intent(ActivityDetailHistorycal.this, SplashScreen.class);
                startActivity(inten);
                finish();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    String id_user,nama,email;
    private void ceklog() {
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        Cursor c = db.getUser();
        if (c.moveToFirst()) {
            id_user = c.getString(4);
            nama = c.getString(0);
            email = c.getString(1);
        }
    }

    public void viewimage(View view) {
        Intent intent = new Intent(ActivityDetailHistorycal.this, TesImageActivity.class);
        intent.putExtra("cif",cif);
        intent.putExtra("loanid",loanid);
        intent.putExtra("code_image",code_image);
        intent.putExtra("tglvisit",tglvisit);
        startActivity(intent);
    }
}
