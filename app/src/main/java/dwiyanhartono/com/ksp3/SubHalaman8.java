package dwiyanhartono.com.ksp3;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.base.BaseActivity;
import dwiyanhartono.com.ksp3.database.DBAdapter2;
import dwiyanhartono.com.ksp3.model.Datasub8;
import dwiyanhartono.com.ksp3.model.Datasub8_1;

public class SubHalaman8 extends BaseActivity {

    RecyclerView rc1;

    Conectiondetector conn;
    String statususer,nama,id_user,email;

    AdapterSub8 adapter;
    private List<Datasub8> mItems = new ArrayList<>();
    private List<Datasub8_1> mItems2 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_halaman8);

        rc1 = (RecyclerView) findViewById(R.id.rc1);
        rc1.setLayoutManager(new LinearLayoutManager(this));
        rc1.setItemAnimator(new DefaultItemAnimator());

        ceklog();
        if(statususer == null){
            Intent i = new Intent(SubHalaman8.this, ActivityLogin.class);
            startActivity(i);
            finish();
        } else {
            adapter = new AdapterSub8(this);
            adapter.setData(mItems2);
            rc1.setAdapter(adapter);
            reteview();
        }
    }

    private void reteview() {

//        Toast.makeText(this, "masuk", Toast.LENGTH_SHORT).show();
        mItems2.clear();
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        String date = new SimpleDateFormat("yyyyMM", Locale.getDefault()).format(new Date());;
//        String date = "201907";
        Cursor a = db.getselfcuredall();
        if (a.moveToFirst()) {
//            Toast.makeText(this, "masuk 1", Toast.LENGTH_SHORT).show();
            do {
                String nama1 = a.getString( 1 );
                String cif = a.getString( 0 );
                String datetime = a.getString( 2 );
                Datasub8_1 data = new Datasub8_1(nama1,cif,datetime);
                mItems2.add(data);

//                Toast.makeText(this, nama1+"/" + cif + "/" + datetime, Toast.LENGTH_SHORT).show();
            } while (a.moveToNext());
            adapter.setData(mItems2);
//            Toast.makeText(this, nama +"/" + cif +"/" + datetime, Toast.LENGTH_SHORT).show();
        }
        db.close();
//        conn = new Conectiondetector(SubHalaman8.this);
//        if (conn.isConected()) {
////            Toast.makeText(this, id_user, Toast.LENGTH_SHORT).show();
//            ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
//            Call<ResponsModelSub8> getdata = api.selfcured(new ReqBodySubHal1(id_user));
//            getdata.enqueue(new Callback<ResponsModelSub8>() {
//                @Override
//                public void onResponse(Call<ResponsModelSub8> call, Response<ResponsModelSub8> response) {
//                    String kode = response.body().getKode();
//                    Toast.makeText(SubHalaman8.this, kode, Toast.LENGTH_SHORT).show();
//
//                    if (kode.equals("1")) {
//                        mItems = response.body().getResult();
//                        adapter.setData(mItems);
//                    } else {
////                        getdblocal();
//                        Toast.makeText(SubHalaman8.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponsModelSub8> call, Throwable t) {
//                    Toast.makeText(SubHalaman8.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
    }

    private void ceklog() {
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        Cursor c = db.getUser();
        if (c.moveToFirst()) {
            statususer = c.getString( 3);
            id_user = c.getString(4);
            nama = c.getString(0);
            email = c.getString(1);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent i = new Intent(SubHalaman8.this, ActivityMain.class);
//        startActivity(i);
//        finish();
    }

    protected void onStop() {
        super.onStop();
//        timer = new Timer();
//        Log.i("Main", "Invoking logout timer");
//        SubHalaman8.LogOutTimerTask logoutTimeTask = new SubHalaman8.LogOutTimerTask();
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
//            conn = new Conectiondetector(SubHalaman8.this);
//            if (conn.isConected()) {
//                ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
//                Call<ResponsModelChangePswd> getdata = api.sendLogout(new ReqBodySubHal1(id_user));
//                getdata.enqueue(new Callback<ResponsModelChangePswd>() {
//                    @Override
//                    public void onResponse(Call<ResponsModelChangePswd> call, Response<ResponsModelChangePswd> response) {
//                        String kode = response.body().getKode();
//                        if (kode.equals("1")) {
//                            DBAdapter db = new DBAdapter(SubHalaman8.this);
//                            db.openDB();
//                            db.Deleteuser();
//                            db.close();
//                            Intent inten = new Intent(SubHalaman8.this, SplashScreen.class);
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
//                Toast.makeText(SubHalaman8.this, "timer cancel", Toast.LENGTH_SHORT).show();
//            }
////            //redirect user to login screen
////            DBAdapter db = new DBAdapter(SubHalaman8.this);
////            db.openDB();
////            db.Deleteuser();
////            db.close();
////            Intent inten = new Intent(SubHalaman8.this, SplashScreen.class);
////            startActivity(inten);
////            finish();
//        }
//    }
}
