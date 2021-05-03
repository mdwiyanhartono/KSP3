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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
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
import dwiyanhartono.com.ksp3.model.DataModel;
import dwiyanhartono.com.ksp3.model.ReqBodyApplyAssignment;
import dwiyanhartono.com.ksp3.model.ReqBodyAssignment;
import dwiyanhartono.com.ksp3.model.ReqBodyAssignmentcari;
import dwiyanhartono.com.ksp3.model.ReqBodySubHal1;
import dwiyanhartono.com.ksp3.model.RequestLogin;
import dwiyanhartono.com.ksp3.model.ResponsModel;
import dwiyanhartono.com.ksp3.model.ResponsModelChangePswd;
import dwiyanhartono.com.ksp3.model.ResponsModelceklog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityAssignment extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterAssignment.OnItemClickListener {

    RecyclerView rv1;
    ImageView apply, cari, close;
    String statususer, id_user, imei;
    EditText textcari;
    AdapterAssignment adapterAssignment;
    private List<DataModel> Assignment = new ArrayList<>();
    List<String> selectedDebitur = new ArrayList<>();
    Conectiondetector conn = new Conectiondetector(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        ceklog();
        if (conn.isConected()) {
            ceklogserver();
        } else {
            Toast.makeText(ActivityAssignment.this, "No internet connection", Toast.LENGTH_SHORT).show();
        }


        rv1 = findViewById(R.id.myRecycler);
        textcari = findViewById(R.id.textcari);
        apply = findViewById(R.id.apply);
        cari = findViewById(R.id.cari);
        close = findViewById(R.id.close);
        rv1.setLayoutManager(new LinearLayoutManager(this));
        rv1.setItemAnimator(new DefaultItemAnimator());
        adapterAssignment = new AdapterAssignment(this);
        adapterAssignment.setData(Assignment);
        rv1.setAdapter(adapterAssignment);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    private void ceklogserver() {
        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModelceklog> getdata1 = api.cekLogin(new RequestLogin(id_user, "1", imei));
        getdata1.enqueue(new Callback<ResponsModelceklog>() {
            @Override
            public void onResponse(Call<ResponsModelceklog> call, Response<ResponsModelceklog> response) {
                String kode = response.body() != null ? response.body().getKode() : "9";
                switch (kode) {
                    case "1":
                        retrivew();
                        break;
                    case "9":
//                        Toast.makeText(ActivityAssignment.this, "Respose code : as_CKLGASMG_01", Toast.LENGTH_SHORT).show();
                        retrivew();

                        break;
                    default:
                        logout();
                        break;
                }
            }

            @Override
            public void onFailure(Call<ResponsModelceklog> call, Throwable t) {
                retrivew();
//                Toast.makeText(ActivityAssignment.this,"Respose code : as_CKLGASMG_err_02 ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ceklog() {
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        Cursor c = db.getUser();
        if (c.moveToFirst()) {
            id_user = c.getString(4);
            statususer = c.getString(3);
            imei = c.getString(6);
            if (statususer == null) {
                Intent i = new Intent(ActivityAssignment.this, ActivityLogin.class);
                startActivity(i);
                finish();
            }
        }
    }

    private void retrivew() {
        Assignment.clear();
        final ProgressDialog pdLoading = new ProgressDialog(this);
        pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(false);
        pdLoading.show();
        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModel> getdata = api.getassignment(new ReqBodyAssignment(id_user));
        getdata.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                String kode = response.body() != null ? response.body().getKode() : "9";
                Log.d("kode", kode);
                switch (kode) {
                    case "1":
                        pdLoading.dismiss();
                        Assignment = response.body().getResult();
                        adapterAssignment.setData(Assignment);
                        Log.d("lengh", Assignment.size() + "");
                        break;
                    case "9":
                        pdLoading.dismiss();
                        Assignment = response.body().getResult();
                        adapterAssignment.setData(Assignment);
                        break;
                    default:
                        pdLoading.dismiss();
                        Assignment = response.body().getResult();
                        adapterAssignment.setData(Assignment);
                        break;
                }
            }

            @Override
            public void onFailure(Call<ResponsModel> call, Throwable t) {
                pdLoading.dismiss();
//                Toast.makeText(ActivityAssignment.this,"Respose code : as_RSLTASMGerr_02", Toast.LENGTH_SHORT).show();
                Log.d("error", t.getMessage());
            }
        });
    }

    private void retrivewcari() {
        Assignment.clear();
        final ProgressDialog pdLoading = new ProgressDialog(this);
        pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(false);
        pdLoading.show();
        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModel> getdata = api.getassignmentcari(new ReqBodyAssignmentcari(id_user, textcari.getText().toString()));
        getdata.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                String kode = response.body() != null ? response.body().getKode() : "9";
                Log.d("kode", kode);
                switch (kode) {
                    case "1":
                        pdLoading.dismiss();
                        Assignment = response.body().getResult();
                        adapterAssignment.setData(Assignment);

//                    Toast.makeText(ActivityAssignment.this, imei, Toast.LENGTH_SHORT).show();
                        Log.d("lengh", Assignment.size() + "");
                        break;
                    case "9":
                        pdLoading.dismiss();
                        adapterAssignment.setData(Assignment);
//                        Toast.makeText(ActivityAssignment.this, "Respose code : as_RSLTASMG_01", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        pdLoading.dismiss();
                        Assignment = response.body().getResult();
                        adapterAssignment.setData(Assignment);
                        break;
                }
            }

            @Override
            public void onFailure(Call<ResponsModel> call, Throwable t) {
                pdLoading.dismiss();
//                Toast.makeText(ActivityAssignment.this,"Respose code : as_RSLTASMGerr_02", Toast.LENGTH_SHORT).show();
                Log.d("error", t.getMessage());
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_assignment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_change) {
            Intent inten = new Intent(ActivityAssignment.this, ChangePassword.class);
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
            finish();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(ActivityAssignment.this, ActivityDailyPlanVisit.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(ActivityAssignment.this, ActivityPTP.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {
            conn = new Conectiondetector(ActivityAssignment.this);
            if (conn.isConected()) {
                notifLogout(1);
            } else {
                notifLogout(0);
            }
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(String id, boolean isChecked) {

        if (isChecked) {
            Log.d("Add Data", id);
//            Toast.makeText(ActivityAssignment.this, "Add Data: " + id, Toast.LENGTH_SHORT).show();
            selectedDebitur.add(id);
        } else {
            Log.d("Remove Data", id);
//            Toast.makeText(ActivityAssignment.this, "Remove Data: " + id, Toast.LENGTH_SHORT).show();
            selectedDebitur.remove(id);
        }
        String a = "";
        for (String s : selectedDebitur) {
            a += s + "#";
        }
        Log.d("Data Selected", a);
//        Toast.makeText(ActivityAssignment.this, "Data Selected: " + a, Toast.LENGTH_SHORT).show();

    }



    public void apply(View view) {
        final ProgressDialog pdLoading = new ProgressDialog(this);
        pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(false);
        pdLoading.show();
        if (selectedDebitur.isEmpty()) {
            pdLoading.dismiss();

            AlertDialog.Builder alta = new AlertDialog.Builder(ActivityAssignment.this);
            alta.setTitle("Pesan");
            alta.setIcon(R.drawable.warning);
            alta.setMessage("Data gagal dikirim silahkan pilih terlebih dahulu data data yang akan dikunjungi");
            alta.setCancelable(false);
            alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    selectedDebitur = new ArrayList<>();
                    retrivew();
                    dialog.dismiss();
                }
            });

            alta.create();
            alta.show();
            //retrivew();
        } else {
            ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
            Call<ResponsModel> getdata = api.sendassignment(new ReqBodyApplyAssignment(selectedDebitur, id_user,""));
            getdata.enqueue(new Callback<ResponsModel>() {
                @Override
                public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                    String kode2 = response.body() != null ? response.body().getKode() : "9";
                    String message = response.body() != null ? response.body().getMessage() : "Blank Message";
                    switch (kode2) {
                        case "1": {
                            pdLoading.dismiss();
                            AlertDialog.Builder alta = new AlertDialog.Builder(ActivityAssignment.this);
                            alta.setTitle("Pesan");
                            alta.setIcon(R.drawable.checklist);
                            alta.setMessage(message);
                            alta.setCancelable(false);
                            alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    selectedDebitur = new ArrayList<>();
                                    retrivew();
                                    dialog.dismiss();
                                }
                            });

                            alta.create();
                            alta.show();

//                    Toast.makeText(ActivityAssignment.this, "sucsses", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case "9": {
                            pdLoading.dismiss();

                            AlertDialog.Builder alta = new AlertDialog.Builder(ActivityAssignment.this);
                            alta.setTitle("Pesan");
                            alta.setIcon(R.drawable.warning);
                            alta.setMessage(message);
                            alta.setCancelable(false);
                            alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    selectedDebitur = new ArrayList<>();
                                    retrivew();
                                    dialog.dismiss();
                                }
                            });

                            alta.create();
                            alta.show();

                            break;
                        }
                        default: {
                            pdLoading.dismiss();

                            AlertDialog.Builder alta = new AlertDialog.Builder(ActivityAssignment.this);
                            alta.setTitle("Pesan");
                            alta.setIcon(R.drawable.warning);
                            alta.setMessage("Data gagal dikirim silahkan pilih terlebih dahulu data data yang akan dikunjungi!");
                            alta.setCancelable(false);
                            alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    selectedDebitur = new ArrayList<>();
                                    retrivew();
                                }
                            });

                            alta.create();
                            alta.show();

                            break;
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponsModel> call, Throwable t) {
                    pdLoading.dismiss();
                    AlertDialog.Builder alta = new AlertDialog.Builder(ActivityAssignment.this);
                    alta.setTitle("Pesan");
                    alta.setIcon(R.drawable.warning);
                    alta.setMessage("Data Mungkin Berhasil Di Kirim Ke Server \n Kemungkinan Ada Fungsi Yang Tidak Normal \n Respose code : as_APASSerr_02" + t.getMessage());
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
    public void onButonVisitClick(String cif, String nama, String alamat, String alamatusaha, String email, String bucketeom, String dpd, String loanid) {
        Intent inten = new Intent(this, ActivityInputKunjungan.class);
        inten.putExtra("cif", cif);
        inten.putExtra("nama", nama);
        inten.putExtra("alamat", alamat);
        inten.putExtra("alamatusaha", alamatusaha);
        inten.putExtra("email", email);
        inten.putExtra("bucketeom", bucketeom);
        inten.putExtra("dpd", dpd);
        inten.putExtra("loanid", loanid);
        startActivity(inten);
    }

    @Override
    public void onButonHistoryClick(String loanid, String cif) {
//        Toast.makeText(this, loanid + "/" + cif, Toast.LENGTH_SHORT).show();
        Intent inten = new Intent(this, ActivityHistorycal.class);
        inten.putExtra("loanid", loanid);
        inten.putExtra("cif", cif);
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
    protected void onResume() {
        super.onResume();
        if (conn.isConected()) {
            ceklogserver();
        } else {
            Toast.makeText(ActivityAssignment.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
//        ceklogserver();
    }

    public void close(View view) {
        close.setVisibility(View.GONE);
        cari.setVisibility(View.VISIBLE);
        retrivew();
    }

    public void cari(View view) {
        close.setVisibility(View.VISIBLE);
        cari.setVisibility(View.GONE);
        retrivewcari();
    }
}
