package dwiyanhartono.com.ksp3;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
import dwiyanhartono.com.ksp3.model.DataCekSaldo;
import dwiyanhartono.com.ksp3.model.DataUser;
import dwiyanhartono.com.ksp3.model.Dataplanvisit;
import dwiyanhartono.com.ksp3.model.Dataptp;
import dwiyanhartono.com.ksp3.model.Datasettlement;
import dwiyanhartono.com.ksp3.model.ReqBodyApplyAssignment;
import dwiyanhartono.com.ksp3.model.ReqBodyAssignment;
import dwiyanhartono.com.ksp3.model.ReqBodyPlanVisit;
import dwiyanhartono.com.ksp3.model.ReqBodySubHal1;
import dwiyanhartono.com.ksp3.model.ResponsModel;
import dwiyanhartono.com.ksp3.model.ResponsModelCekSaldo;
import dwiyanhartono.com.ksp3.model.ResponsModelChangePswd;
import dwiyanhartono.com.ksp3.model.ResponsModelPTP;
import dwiyanhartono.com.ksp3.model.ResponsModelSettlement;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityPTP extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterSettlement.OnItemClickListener {

    RecyclerView rv;
    ImageView apply;
    TextView textnameuser,textsaldo,textnorekuser;
    AdapterSettlement adapter;
    List<String> selectedDebitur = new ArrayList<>();

    Conectiondetector conn;
    private List<Datasettlement> mItems = new ArrayList<>();
    private DataCekSaldo saldo;
    private List<Dataplanvisit> dataplanvisit = new ArrayList<>();

    String a = "";

    String id_user;
    String Saldouser,NamaUserAccount,NorekUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptp);

        ceklog();

        rv = findViewById(R.id.myRecycler);
        apply = findViewById(R.id.apply);
        textnorekuser = findViewById(R.id.norek);
        textnameuser = findViewById(R.id.txtnama);
        textsaldo = findViewById(R.id.balance);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter = new AdapterSettlement(this);
        adapter.setData(mItems);
        rv.setAdapter(adapter);
        retrieve();
        requestsaldo();

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Applysettlement();
                Toast.makeText(ActivityPTP.this, "Test", Toast.LENGTH_SHORT).show();
            }
        });
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

    private void requestsaldo() {

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        conn = new Conectiondetector(ActivityPTP.this);
        if (conn.isConected()) {
            ApiRequestData api2 = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
            Call<ResponsModelCekSaldo> getdata = api2.ceksaldo(new ReqBodyAssignment(id_user));
            getdata.enqueue(new Callback<ResponsModelCekSaldo>() {
                @Override
                public void onResponse(Call<ResponsModelCekSaldo> call, Response<ResponsModelCekSaldo> response) {
                    String kode = response.body() != null ? response.body().getKode() : "9";
                    if (kode.equals("1")) {
                        saldo = response.body().getResult();
                        textnorekuser.setText(saldo.getAccountNumber());
                        textnameuser.setText(saldo.getAccountName());
                        textsaldo.setText(saldo.getBalance());
                    } else {
//                        getdblocal();
                        textnorekuser.setText("-");
                        textnameuser.setText("-");
                        textsaldo.setText("0");
//                        Toast.makeText(ActivityPTP.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponsModelCekSaldo> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(ActivityPTP.this, "Response code : ap_PTPerr_02", Toast.LENGTH_SHORT).show();
//                    getdblocal();
                }
            });
        } else {
//            getdblocal();
        }
    }

    public void Applysettlement() {
        final ProgressDialog pdLoading = new ProgressDialog(this);
        pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(false);
        pdLoading.show();
        if (selectedDebitur.isEmpty()) {
            pdLoading.dismiss();

            AlertDialog.Builder alta = new AlertDialog.Builder(ActivityPTP.this);
            alta.setTitle("Pesan");
            alta.setIcon(R.drawable.warning);
            alta.setMessage("Data gagal di kirim silahkan pilih terlebih dahulu data yang akan di Settlement");
            alta.setCancelable(false);
            alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    selectedDebitur = new ArrayList<>();
                    retrieve();
                    dialog.dismiss();
                }
            });

            alta.create();
            alta.show();
        } else {
            ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
            Call<ResponsModelSettlement> getdata = api.sendsettlement(new ReqBodyApplyAssignment(selectedDebitur, id_user,textsaldo.getText().toString()));
            getdata.enqueue(new Callback<ResponsModelSettlement>() {
                @Override
                public void onResponse(Call<ResponsModelSettlement> call, Response<ResponsModelSettlement> response) {
                    String kode2 = response.body() != null ? response.body().getKode() : "9";
                    String message2 = response.body().getMessage();
                    switch (kode2) {
                        case "1": {
                            pdLoading.dismiss();
                            AlertDialog.Builder alta = new AlertDialog.Builder(ActivityPTP.this);
                            alta.setTitle("Pesan");
                            alta.setIcon(R.drawable.checklist);
                            alta.setMessage(message2);
                            alta.setCancelable(false);
                            alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(ActivityPTP.this, ActivityPTP.class);
                                    startActivity(i);
                                    finish();
                                    dialog.dismiss();
                                }
                            });

                            alta.create();
                            alta.show();
//                    Toast.makeText(ActivityAssignment.this, "sucsses", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case "0": {
                            pdLoading.dismiss();

                            AlertDialog.Builder alta = new AlertDialog.Builder(ActivityPTP.this);
                            alta.setTitle("Pesan");
                            alta.setIcon(R.drawable.warning);
                            alta.setMessage(message2);
                            alta.setCancelable(false);
                            alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    selectedDebitur = new ArrayList<>();
                                    retrieve();
                                    dialog.dismiss();
                                }
                            });

                            alta.create();
                            alta.show();
                            break;
                        }
                        default: {
                            pdLoading.dismiss();

                            AlertDialog.Builder alta = new AlertDialog.Builder(ActivityPTP.this);
                            alta.setTitle("Pesan");
                            alta.setIcon(R.drawable.warning);
                            alta.setMessage(message2);
                            alta.setCancelable(false);
                            alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    selectedDebitur = new ArrayList<>();
                                }
                            });

                            alta.create();
                            alta.show();
                            break;
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponsModelSettlement> call, Throwable t) {
                    pdLoading.dismiss();
                    AlertDialog.Builder alta = new AlertDialog.Builder(ActivityPTP.this);
                    alta.setTitle("Pesan");
                    alta.setIcon(R.drawable.warning);
                    alta.setMessage("Ada fungsi yang tidak Normal \n Respose code : as_APASSerr_02" + t.getMessage());
                    alta.setCancelable(false);
                    alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alta.create();
                    alta.show();
//                Toast.makeText(ActivityAssignment.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("error", t.getMessage());
                }
            });

        }
        retrieve();
        requestsaldo();
    }

    @Override
    public void onItemClick(String id, boolean isChecked) {

        if (isChecked) {
            Log.d("Add Data", id);
            Toast.makeText(ActivityPTP.this, "Add Data: " + id, Toast.LENGTH_SHORT).show();
            selectedDebitur.add(id);
        } else {
            Log.d("Remove Data", id);
            Toast.makeText(ActivityPTP.this, "Remove Data: " + id, Toast.LENGTH_SHORT).show();
            selectedDebitur.remove(id);
        }
        String a = "";
        for (String s : selectedDebitur) {
            a += s + "#";
        }
        Log.d("Data Selected", a);
        Toast.makeText(ActivityPTP.this, "Data Selected: " + a, Toast.LENGTH_SHORT).show();
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
//            Toast.makeText(this, "Masuk Retrive", Toast.LENGTH_SHORT).show();
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
//                        Toast.makeText(ActivityPTP.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
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
    protected void onResume() {
        super.onResume();
        retrieve();
        requestsaldo();
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
                notifLogout(1);
            } else {
                notifLogout(0);
            }
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
