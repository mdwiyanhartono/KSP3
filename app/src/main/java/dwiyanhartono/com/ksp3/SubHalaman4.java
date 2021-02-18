package dwiyanhartono.com.ksp3;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.base.BaseActivity;
import dwiyanhartono.com.ksp3.database.DBAdapter2;
import dwiyanhartono.com.ksp3.model.Datasub4;
import dwiyanhartono.com.ksp3.model.Datasub4_1;

public class SubHalaman4 extends BaseActivity {

    RecyclerView rc1;

    Conectiondetector conn;
    String statususer, nama, id_user, email;

    AdapterSub4 adapter;
    private List<Datasub4> mItems = new ArrayList<>();
    private List<Datasub4_1> datanocantact = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_halaman4);

        rc1 = (RecyclerView) findViewById(R.id.rc1);
        rc1.setLayoutManager(new LinearLayoutManager(this));
        rc1.setItemAnimator(new DefaultItemAnimator());

        ceklog();
        if (statususer == null) {
            Intent i = new Intent(SubHalaman4.this, ActivityLogin.class);
            startActivity(i);
            finish();
        } else {
            adapter = new AdapterSub4(this);
            adapter.setData(datanocantact);
            rc1.setAdapter(adapter);
            reteview();
        }
    }

    private void reteview() {
        mItems.clear();
        datanocantact.clear();
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        String nocontacted = "No Contacted";
        Cursor a = db.getnocontactedall(nocontacted);
        if (a.moveToFirst()) {
//            Toast.makeText(this, "masuk 1", Toast.LENGTH_SHORT).show();
            do {
                String namanasabah = a.getString(6);
                String cif = a.getString(4);
                String loanid = a.getString(1);
                String hasilkunjungan = a.getString(9);
                String bertemu = a.getString(11);
                String lokasibertemu = a.getString(13);
                String actionplan = a.getString(18);
                Datasub4_1 data = new Datasub4_1(namanasabah, cif, loanid, hasilkunjungan, bertemu, lokasibertemu, actionplan);
                datanocantact.add(data);

//                Toast.makeText(this, nama1+"/" + cif + "/" + datetime, Toast.LENGTH_SHORT).show();
            } while (a.moveToNext());
            adapter.setData(datanocantact);
//            Toast.makeText(this, nama +"/" + cif +"/" + datetime, Toast.LENGTH_SHORT).show();
        }
        db.close();

//        conn = new Conectiondetector(SubHalaman4.this);
//        if (conn.isConected()) {
//            Toast.makeText(this, id_user, Toast.LENGTH_SHORT).show();
//            ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
//            Call<ResponsModelSub4> getdata = api.sendsub4(new ReqBodySubHal1(id_user));
//            getdata.enqueue(new Callback<ResponsModelSub4>() {
//                @Override
//                public void onResponse(Call<ResponsModelSub4> call, Response<ResponsModelSub4> response) {
//                    String kode = response.body().getKode();
//                    Toast.makeText(SubHalaman4.this, kode, Toast.LENGTH_SHORT).show();
//
//                    if (kode.equals("1")) {
//                        mItems = response.body().getResult();
//
//                        for(int i = 0; i < mItems.size(); i++) {
//                            String cif = mItems.get(i).getCif();
//                            String namanasabah = mItems.get(i).getNamanasabah();
//                            String actionplan = mItems.get(i).getActionplan();
//                            String hasilkunjungan = mItems.get(i).getHasilkunjungan();
//                            String bertemu = mItems.get(i).getBertemu();
//                            String lokasibertemu = mItems.get(i).getLokasibertemu();
//                            String loanid = mItems.get(i).getLoanid();
////                            String bertemu = mItems.get(i).getBertemu();
////                            String bertemu = mItems.get(i).getBertemu();
//                            Toast.makeText(SubHalaman4.this, cif, Toast.LENGTH_SHORT).show();
//
//                            Datasub4_1 data = new Datasub4_1(namanasabah, cif, loanid, hasilkunjungan, bertemu, lokasibertemu, actionplan);
//                            datanocantact.add(data);
//                        }
//                        adapter.setData(datanocantact);
//                    } else {
////                        getdblocal();
//                        Toast.makeText(SubHalaman4.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponsModelSub4> call, Throwable t) {
//                    Toast.makeText(SubHalaman4.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent i = new Intent(SubHalaman4.this, ActivityMain.class);
//        startActivity(i);
//        finish();
    }

    protected void onStop() {
        super.onStop();
//        timer = new Timer();
//        Log.i("Main", "Invoking logout timer");
//        SubHalaman4.LogOutTimerTask logoutTimeTask = new SubHalaman4.LogOutTimerTask();
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
//            conn = new Conectiondetector(SubHalaman4.this);
//            if (conn.isConected()) {
//                ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
//                Call<ResponsModelChangePswd> getdata = api.sendLogout(new ReqBodySubHal1(id_user));
//                getdata.enqueue(new Callback<ResponsModelChangePswd>() {
//                    @Override
//                    public void onResponse(Call<ResponsModelChangePswd> call, Response<ResponsModelChangePswd> response) {
//                        String kode = response.body().getKode();
//                        if (kode.equals("1")) {
//                            DBAdapter db = new DBAdapter(SubHalaman4.this);
//                            db.openDB();
//                            db.Deleteuser();
//                            db.close();
//                            Intent inten = new Intent(SubHalaman4.this, SplashScreen.class);
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
//                Toast.makeText(SubHalaman4.this, "timer cancel", Toast.LENGTH_SHORT).show();
//            }
////            //redirect user to login screen
////            DBAdapter db = new DBAdapter(SubHalaman4.this);
////            db.openDB();
////            db.Deleteuser();
////            db.close();
////            Intent inten = new Intent(SubHalaman4.this, SplashScreen.class);
////            startActivity(inten);
////            finish();
//        }
//    }
}
