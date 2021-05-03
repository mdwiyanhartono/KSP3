package dwiyanhartono.com.ksp3;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.base.BaseActivity;
import dwiyanhartono.com.ksp3.database.DBAdapter2;
import dwiyanhartono.com.ksp3.model.Datasub1;
import dwiyanhartono.com.ksp3.model.Datasub1_1;

public class SubHalaman1 extends BaseActivity {
    RecyclerView rc1;

    Conectiondetector conn;
    String statususer, nama, id_user, email;
    AdapterSub1 adapter;
    private List<Datasub1> mItems = new ArrayList<>();
    private List<Datasub1_1> datavisit = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_halaman1);

        rc1 = (RecyclerView) findViewById(R.id.rc1);
        rc1.setLayoutManager(new LinearLayoutManager(this));
        rc1.setItemAnimator(new DefaultItemAnimator());

        ceklog();
        if (statususer == null) {
            Intent i = new Intent(SubHalaman1.this, ActivityLogin.class);
            startActivity(i);
            finish();
        } else {
            adapter = new AdapterSub1(this);
            adapter.setData(datavisit);
            rc1.setAdapter(adapter);
            reteview();
        }
    }

    private void ceklog() {
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        Cursor c = db.getUser();
        if (c.moveToFirst()) {
            statususer = c.getString(3);
            id_user = c.getString(4);
            nama = c.getString(0);
            email = c.getString(1);
        }
    }

    private void reteview() {
        mItems.clear();
        datavisit.clear();
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
//        String date = new SimpleDateFormat("yyyyMM", Locale.getDefault()).format(new Date());;
        String contacted = "Contacted";
        Cursor a = db.getvisitall();
        if (a.moveToFirst()) {
//            Toast.makeText(this, "masuk 1", Toast.LENGTH_SHORT).show();
            do {
                String namaanggota = a.getString(6);
                String cif = a.getString(4);
                String loanid = a.getString(1);
                String hasilkunjungan = a.getString(9);
                String bertemu = a.getString(11);
                String lokasibertemu = a.getString(13);
                String actionplan = a.getString(18);
                Datasub1_1 data = new Datasub1_1(namaanggota, cif, loanid, hasilkunjungan, bertemu, lokasibertemu, actionplan);
                Log.d("CHECK", String.valueOf(cif));
                datavisit.add(data);

//                Toast.makeText(this, nama1+"/" + cif + "/" + datetime, Toast.LENGTH_SHORT).show();
            } while (a.moveToNext());
            adapter.setData(datavisit);
//            Toast.makeText(this, nama +"/" + cif +"/" + datetime, Toast.LENGTH_SHORT).show();
        }
        db.close();

//        conn = new Conectiondetector(SubHalaman1.this);
//        if (conn.isConected()) {
//            Toast.makeText(this, id_user, Toast.LENGTH_SHORT).show();
//            ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
//            Call<ResponsModelSub1> getdata = api.sendsub1(new ReqBodySubHal1(id_user));
//            getdata.enqueue(new Callback<ResponsModelSub1>() {
//                @Override
//                public void onResponse(Call<ResponsModelSub1> call, Response<ResponsModelSub1> response) {
//                    String kode = response.body().getKode();
//
//                    if (kode.equals("1")) {
//                        mItems = response.body().getResult();
//                        for(int i = 0; i < mItems.size(); i++){
//                            String cif = mItems.get(i).getCif();
//                            String namaanggota = mItems.get(i).getNamaanggota();
//                            String actionplan = mItems.get(i).getActionplan();
//                            String hasilkunjungan = mItems.get(i).getHasilkunjungan();
//                            String bertemu = mItems.get(i).getBertemu();
//                            String lokasibertemu = mItems.get(i).getLokasibertemu();
//                            String loanid = mItems.get(i).getLoanid();
////                            String bertemu = mItems.get(i).getBertemu();
////                            String bertemu = mItems.get(i).getBertemu();
//                            Toast.makeText(SubHalaman1.this, cif, Toast.LENGTH_SHORT).show();
//
//                            Datasub1_1 data = new Datasub1_1(namaanggota, cif, loanid, hasilkunjungan, bertemu,  lokasibertemu, actionplan);
//                            datavisit.add(data);
//
//                            }
//
////                        Toast.makeText(SubHalaman1.this, response.body().getResult().size(), Toast.LENGTH_SHORT).show();
//                        adapter.setData(datavisit);
//                    } else {
////                        getdblocal();
//                        Toast.makeText(SubHalaman1.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponsModelSub1> call, Throwable t) {
//                    Toast.makeText(SubHalaman1.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent i = new Intent(SubHalaman1.this, ActivityMain.class);
//        startActivity(i);
//        finish();
    }

    protected void onStop() {
        super.onStop();
//        timer = new Timer();
//        Log.i("Main", "Invoking logout timer");
//        SubHalaman1.LogOutTimerTask logoutTimeTask = new SubHalaman1.LogOutTimerTask();
//        timer.schedule(logoutTimeTask,30000 ); //auto logout in 5 minutes 600000
    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//
//    }

    private Timer timer;

    @Override
    protected void onResume() {
        super.onResume();
//
//        if (timer != null) {
//            timer.cancel();
//            Log.i("Main", "cancel timer");
//            timer = null;
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Toast.makeText(this, "destroy2", Toast.LENGTH_SHORT).show();
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
//            conn = new Conectiondetector(SubHalaman1.this);
//            if (conn.isConected()) {
//                ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
//                Call<ResponsModelChangePswd> getdata = api.sendLogout(new ReqBodySubHal1(id_user));
//                getdata.enqueue(new Callback<ResponsModelChangePswd>() {
//                    @Override
//                    public void onResponse(Call<ResponsModelChangePswd> call, Response<ResponsModelChangePswd> response) {
//                        String kode = response.body().getKode();
//                        if (kode.equals("1")) {
//                            DBAdapter db = new DBAdapter(SubHalaman1.this);
//                            db.openDB();
//                            db.Deleteuser();
//                            db.close();
//                            Intent inten = new Intent(SubHalaman1.this, SplashScreen.class);
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
//                Toast.makeText(SubHalaman1.this, "timer cancel", Toast.LENGTH_SHORT).show();
//            }
////            //redirect user to login screen
////            DBAdapter db = new DBAdapter(SubHalaman1.this);
////            db.openDB();
////            db.Deleteuser();
////            db.close();
////            Intent inten = new Intent(SubHalaman1.this, SplashScreen.class);
////            startActivity(inten);
////            finish();
//        }
//    }
}
