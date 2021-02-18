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
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.api.ApiRequestData;
import dwiyanhartono.com.ksp3.api.Retroserver;
import dwiyanhartono.com.ksp3.database.DBAdapter2;
import dwiyanhartono.com.ksp3.model.Dataplanvisit;
import dwiyanhartono.com.ksp3.model.Dataptp;
import dwiyanhartono.com.ksp3.model.Datasettlement;
import dwiyanhartono.com.ksp3.model.ReqBodyPlanVisit;
import dwiyanhartono.com.ksp3.model.ReqBodySubHal1;
import dwiyanhartono.com.ksp3.model.ResponsModelChangePswd;
import dwiyanhartono.com.ksp3.model.ResponsModelPTP;
import dwiyanhartono.com.ksp3.model.ResponsModelSettlement;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityPTP extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView rv;

    AdapterSettlement adapter;

    Conectiondetector conn;
    private List<Datasettlement> mItems = new ArrayList<>();
    private List<Dataplanvisit> dataplanvisit = new ArrayList<>();

    String a = "";

    String id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptp);

        ceklog();

        rv = (RecyclerView) findViewById(R.id.myRecycler);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter = new AdapterSettlement(this);
        rv.setAdapter(adapter);
        adapter.setData(mItems);
        retrieve();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void ceklog() {
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        Cursor c = db.getUser();
        if (c.moveToFirst()) {
            id_user = c.getString(4);

        }
    }

    private void retrieve() {
        mItems.clear();
        conn = new Conectiondetector(ActivityPTP.this);
        if (conn.isConected()) {
            ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
            Call<ResponsModelSettlement> getdata = api.settlement(new ReqBodyPlanVisit(id_user));
            getdata.enqueue(new Callback<ResponsModelSettlement>() {
                @Override
                public void onResponse(Call<ResponsModelSettlement> call, Response<ResponsModelSettlement> response) {
                    String kode = response.body() != null ? response.body().getKode() : "9";
                    if (kode.equals("1")) {
                        mItems = response.body().getResult();

                        adapter.setData(mItems);
                    } else {
//                        getdblocal();
                        Toast.makeText(ActivityPTP.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponsModelSettlement> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(ActivityPTP.this, "Response code : ap_PTPerr_02", Toast.LENGTH_SHORT).show();
//                    getdblocal();
                }
            });
        } else {
//            getdblocal();
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
        getMenuInflater().inflate(R.menu.activity_pt, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_change) {
            Intent inten = new Intent(ActivityPTP.this, ChangePassword.class);
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
            Intent intent = new Intent(ActivityPTP.this, ActivityMain.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(ActivityPTP.this, ActivityDailyPlanVisit.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
//            Intent intent = new Intent(ActivityPTP.this, ActivityPTP.class);
//            startActivity(intent);
            /*Intent intent = new Intent(ActivityMain.this,ActivityDetailInventory.class);
            startActivity(intent);*/

        } else if (id == R.id.nav_manage) {

            conn = new Conectiondetector(ActivityPTP.this);
            if (conn.isConected()) {
                ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
                Call<ResponsModelChangePswd> getdata = api.sendLogout(new ReqBodySubHal1(id_user));
                getdata.enqueue(new Callback<ResponsModelChangePswd>() {
                    @Override
                    public void onResponse(Call<ResponsModelChangePswd> call, Response<ResponsModelChangePswd> response) {
                        String kode = response.body().getKode();
                        if (kode.equals("1")) {
                            DBAdapter2 db = new DBAdapter2(ActivityPTP.this);
                            db.openDB();
                            db.Deleteuser();
                            db.deleteparameter();
                            db.deletekunjunganall("1");
                            db.deletekpall();
                            db.deleteselfcuredall();
                            db.close();
                            Intent inten = new Intent(ActivityPTP.this, SplashScreen.class);
                            startActivity(inten);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModelChangePswd> call, Throwable t) {
                        DBAdapter2 db = new DBAdapter2(ActivityPTP.this);
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
                        Intent inten = new Intent(ActivityPTP.this, SplashScreen.class);
                        startActivity(inten);
                        finish();
                    }
                });
            } else {
                DBAdapter2 db = new DBAdapter2(ActivityPTP.this);
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
                Intent inten = new Intent(ActivityPTP.this, SplashScreen.class);
                startActivity(inten);
                finish();
            }
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
