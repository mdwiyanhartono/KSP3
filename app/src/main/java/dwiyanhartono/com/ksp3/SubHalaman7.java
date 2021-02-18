package dwiyanhartono.com.ksp3;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.api.ApiRequestData;
import dwiyanhartono.com.ksp3.api.Retroserver;
import dwiyanhartono.com.ksp3.base.BaseActivity;
import dwiyanhartono.com.ksp3.database.DBAdapter2;
import dwiyanhartono.com.ksp3.model.Datakp_1;
import dwiyanhartono.com.ksp3.model.Datasub7;
import dwiyanhartono.com.ksp3.model.ReqBodySubHal1;
import dwiyanhartono.com.ksp3.model.ResponsModelSub7;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubHalaman7 extends BaseActivity {

    RecyclerView rc1;

    Conectiondetector conn;
    String statususer, nama,id_user,email;

    AdapterSub7 adapter;
    private List<Datasub7> mItems = new ArrayList<>();
    private List<Datakp_1> datakp = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_halaman7);

        rc1 = (RecyclerView) findViewById(R.id.rc1);
        rc1.setLayoutManager(new LinearLayoutManager(this));
        rc1.setItemAnimator(new DefaultItemAnimator());

        ceklog();
        if(statususer == null){
            Intent i = new Intent(SubHalaman7.this, ActivityLogin.class);
            startActivity(i);
            finish();
        } else {
            adapter = new AdapterSub7(this);
            adapter.setData(datakp);
            rc1.setAdapter(adapter);
            reteview();
        }
    }

    private void reteview() {

        mItems.clear();
        datakp.clear();
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        Cursor a = db.getkp(id_user);
        if (a.moveToFirst()) {
//            Toast.makeText(this, "masuk 1", Toast.LENGTH_SHORT).show();
            do {
                String namanasabah = a.getString( 2);
                String cif = a.getString( 1 );
                String loanid = a.getString( 0 );
                String hasilkunjungan = a.getString( 4 );
                String bertemu = a.getString( 6 );
                String lokasibertemu = a.getString( 5 );
                String actionplan = a.getString( 3 );
                String tanggalactionplan = a.getString( 10 );
                String nominal = a.getString( 7 );
                String dateprocess = a.getString( 8 );
                Datakp_1 data = new Datakp_1(namanasabah,cif,loanid,hasilkunjungan,bertemu,lokasibertemu,actionplan,tanggalactionplan,nominal,dateprocess);
                datakp.add(data);
//                Toast.makeText(this, nama1+"/" + cif + "/" + datetime, Toast.LENGTH_SHORT).show();
            } while (a.moveToNext());
            adapter.setData(datakp);
//            Toast.makeText(this, nama +"/" + cif +"/" + datetime, Toast.LENGTH_SHORT).show();
        }
        db.close();

        conn = new Conectiondetector(SubHalaman7.this);
        if (conn.isConected()) {
//            Toast.makeText(this, id_user, Toast.LENGTH_SHORT).show();
            ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
            Call<ResponsModelSub7> getdata = api.sendsub7(new ReqBodySubHal1(id_user));
            getdata.enqueue(new Callback<ResponsModelSub7>() {
                @Override
                public void onResponse(Call<ResponsModelSub7> call, Response<ResponsModelSub7> response) {
                    String kode = response.body().getKode();
//                    Toast.makeText(SubHalaman7.this, kode, Toast.LENGTH_SHORT).show();

                    if (kode.equals("1")) {
                        mItems = response.body().getResult();
                        adapter.setData(datakp);
                    } else {
//                        getdblocal();
//                        Toast.makeText(SubHalaman7.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponsModelSub7> call, Throwable t) {
                    Toast.makeText(SubHalaman7.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
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
//        Intent i = new Intent(SubHalaman7.this, ActivityMain.class);
//        startActivity(i);
//        finish();
    }

    protected void onStop() {
        super.onStop();
//        timer = new Timer();
//        Log.i("Main", "Invoking logout timer");
//        SubHalaman7.LogOutTimerTask logoutTimeTask = new SubHalaman7.LogOutTimerTask();
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
//            conn = new Conectiondetector(SubHalaman7.this);
//            if (conn.isConected()) {
//                ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
//                Call<ResponsModelChangePswd> getdata = api.sendLogout(new ReqBodySubHal1(id_user));
//                getdata.enqueue(new Callback<ResponsModelChangePswd>() {
//                    @Override
//                    public void onResponse(Call<ResponsModelChangePswd> call, Response<ResponsModelChangePswd> response) {
//                        String kode = response.body().getKode();
//                        if (kode.equals("1")) {
//                            DBAdapter db = new DBAdapter(SubHalaman7.this);
//                            db.openDB();
//                            db.Deleteuser();
//                            db.close();
//                            Intent inten = new Intent(SubHalaman7.this, SplashScreen.class);
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
//                Toast.makeText(SubHalaman7.this, "timer cancel", Toast.LENGTH_SHORT).show();
//            }
////            //redirect user to login screen
////            DBAdapter db = new DBAdapter(SubHalaman7.this);
////            db.openDB();
////            db.Deleteuser();
////            db.close();
////            Intent inten = new Intent(SubHalaman7.this, SplashScreen.class);
////            startActivity(inten);
////            finish();
//        }
//    }
}
