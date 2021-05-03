package dwiyanhartono.com.ksp3;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import androidx.appcompat.app.AlertDialog;
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
import dwiyanhartono.com.ksp3.model.ResponsModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubHal2 extends BaseActivity implements AdapterAssignment.OnItemClickListener {
    RecyclerView rc1;

    Conectiondetector conn;
    String nama,id_user,email;
    AdapterAssignment adapterAssignment;
    private List<DataModel> Assignment = new ArrayList<>();
    List<String> selectedDebitur = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_hal2);

        rc1 = (RecyclerView) findViewById(R.id.rc1);
        rc1.setLayoutManager(new LinearLayoutManager(this));
        rc1.setItemAnimator(new DefaultItemAnimator());

        ceklog();
        reteview();
        adapterAssignment = new AdapterAssignment(this);
        adapterAssignment.setData(Assignment);
        rc1.setAdapter(adapterAssignment);
        reteview();
    }

    private void reteview() {
        Assignment.clear();
        final ProgressDialog pdLoading = new ProgressDialog(this);
        pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(false);
        pdLoading.show();
        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModel> getdata = api.getassignmentnotvisit(new ReqBodyAssignment(id_user));
        getdata.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                String kode = response.body().getKode();
                Log.d("kode", kode);
                if (kode.equals("1")) {
                    pdLoading.dismiss();
                    Assignment = response.body().getResult();
                    adapterAssignment.setData(Assignment);
                    Log.d("lengh", Assignment.size() + "");
                } else if(kode.equals("0")) {
                    pdLoading.dismiss();
                    Assignment = response.body().getResult();
                    adapterAssignment.setData(Assignment);
                }
            }

            @Override
            public void onFailure(Call<ResponsModel> call, Throwable t) {
                pdLoading.dismiss();
                Toast.makeText(SubHal2.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("error", t.getMessage());
            }
        });
    }

    private void ceklog() {
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        Cursor c = db.getUser();
        if (c.moveToFirst()) {
            id_user = c.getString(4);
          /*  nama = c.getString(0);
            email = c.getString(1);*/
        }
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
//        Toast.makeText(SubHal2.this, "Data Selected: " + a, Toast.LENGTH_SHORT).show();

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
        Intent inten = new Intent(SubHal2.this, ActivityMapsTracking.class);
        inten.putExtra("cif", cif);
        inten.putExtra("alamat", alamat);
        startActivity(inten);

    }

    public void apply(View view) {
        final ProgressDialog pdLoading = new ProgressDialog(this);
        pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(false);
        pdLoading.show();
        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModel> getdata = api.sendassignment(new ReqBodyApplyAssignment(selectedDebitur,id_user,""));
        getdata.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                String kode2 = response.body().getKode();
                if (kode2.equals("1")) {
                    pdLoading.dismiss();
                    AlertDialog.Builder alta = new AlertDialog.Builder(SubHal2.this);
                    alta.setTitle("Pesan");
                    alta.setIcon(R.drawable.checklist);
                    alta.setMessage("Data Berhasil Di Kirim Ke Server , Dan Menunggu Aproved !!!");
                    alta.setCancelable(false);
                    alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alta.create();
                    alta.show();
                    reteview();
//                    Toast.makeText(SubHal2.this, "sucsses", Toast.LENGTH_SHORT).show();
                } else {
                    pdLoading.dismiss();

                    AlertDialog.Builder alta = new AlertDialog.Builder(SubHal2.this);
                    alta.setTitle("Pesan");
                    alta.setIcon(R.drawable.warning);
                    alta.setMessage("Data gagal Di Kirim Ke Server , silahkan periksa Jaringan Internet Anda!!!");
                    alta.setCancelable(false);
                    alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alta.create();
                    alta.show();
                    reteview();
                }

            }

            @Override
            public void onFailure(Call<ResponsModel> call, Throwable t) {
                pdLoading.dismiss();
                AlertDialog.Builder alta = new AlertDialog.Builder(SubHal2.this);
                alta.setTitle("Pesan");
                alta.setIcon(R.drawable.warning);
                alta.setMessage("Data gagal Di Kirim Ke Server ,"+t.getMessage()+ "!!!");
                alta.setCancelable(false);
                alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alta.create();
                alta.show();
                Toast.makeText(SubHal2.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("error", t.getMessage());
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent i = new Intent(SubHal2.this,ActivityMain.class);
//        startActivity(i);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        timer = new Timer();
//        Log.i("Main", "Invoking logout timer");
//        SubHal2.LogOutTimerTask logoutTimeTask = new SubHal2.LogOutTimerTask();
//        timer.schedule(logoutTimeTask,30000 ); //auto logout in 5 minutes 600000

    }

    private Timer timer;
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Toast.makeText(this, "destroy", Toast.LENGTH_SHORT).show();
//        if (timer != null) {
//            timer.cancel();
//            Log.i("Main", "cancel timer");
//            timer = null;
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (timer != null) {
//            timer.cancel();
//            Log.i("Main", "cancel timer");
//            timer = null;
//        }
    }

//    class LogOutTimerTask extends TimerTask {
//
//        @Override
//        public void run() {
//            conn = new Conectiondetector(SubHal2.this);
//            if (conn.isConected()) {
//                ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
//                Call<ResponsModelChangePswd> getdata = api.sendLogout(new ReqBodySubHal1(id_user));
//                getdata.enqueue(new Callback<ResponsModelChangePswd>() {
//                    @Override
//                    public void onResponse(Call<ResponsModelChangePswd> call, Response<ResponsModelChangePswd> response) {
//                        String kode = response.body().getKode();
//                        if (kode.equals("1")) {
//                            DBAdapter db = new DBAdapter(SubHal2.this);
//                            db.openDB();
//                            db.Deleteuser();
//                            db.close();
//                            Intent inten = new Intent(SubHal2.this, SplashScreen.class);
//                            startActivity(inten);
//                            finish();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponsModelChangePswd> call, Throwable t) {
//
//                    }
//                });
//            } else {
//                timer.cancel();
//                Toast.makeText(SubHal2.this, "timer cancel", Toast.LENGTH_SHORT).show();
//            }
////            //redirect user to login screen
////            DBAdapter db = new DBAdapter(SubHal2.this);
////            db.openDB();
////            db.Deleteuser();
////            db.close();
////            Intent inten = new Intent(SubHal2.this, SplashScreen.class);
////            startActivity(inten);
////            finish();
//        }
//    }
}
