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
import dwiyanhartono.com.ksp3.model.Datasub3;
import dwiyanhartono.com.ksp3.model.Datasub3_1;

public class SubHalaman3 extends BaseActivity {

    RecyclerView rc1;

    Conectiondetector conn;
    String statususer,nama,id_user,email;

    AdapterSub3 adapter;
    private List<Datasub3> mItems = new ArrayList<>();
    private List<Datasub3_1> datacontacted= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_halaman3);

        rc1 = (RecyclerView) findViewById(R.id.rc1);
        rc1.setLayoutManager(new LinearLayoutManager(this));
        rc1.setItemAnimator(new DefaultItemAnimator());

        ceklog();
        if(statususer == null){
            Intent i = new Intent(SubHalaman3.this, ActivityLogin.class);
            startActivity(i);
            finish();
        } else {
            adapter = new AdapterSub3(this);
            adapter.setData(datacontacted);
            rc1.setAdapter(adapter);
            reteview();
        }
    }

    private void reteview() {
        mItems.clear();
        datacontacted.clear();
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
//        String date = new SimpleDateFormat("yyyyMM", Locale.getDefault()).format(new Date());;
        String contacted = "Contacted";
        Cursor a = db.getcontactedall(contacted);
        if (a.moveToFirst()) {
//            Toast.makeText(this, "masuk 1", Toast.LENGTH_SHORT).show();
            do {
                String namanasabah = a.getString( 6 );
                String cif = a.getString( 4 );
                String loanid = a.getString( 1 );
                String hasilkunjungan = a.getString( 9 );
                String bertemu = a.getString( 11);
                String lokasibertemu = a.getString( 13 );
                String actionplan = a.getString( 18 );
                Datasub3_1 data = new Datasub3_1(namanasabah,cif,loanid,hasilkunjungan,bertemu,lokasibertemu,actionplan);
                datacontacted.add(data);

//                Toast.makeText(this, nama1+"/" + cif + "/" + datetime, Toast.LENGTH_SHORT).show();
            } while (a.moveToNext());
            adapter.setData(datacontacted);
//            Toast.makeText(this, nama +"/" + cif +"/" + datetime, Toast.LENGTH_SHORT).show();
        }
        db.close();

//        conn = new Conectiondetector(SubHalaman3.this);
//        if (conn.isConected()) {
//            Toast.makeText(this, id_user, Toast.LENGTH_SHORT).show();
//            ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
//            Call<ResponsModelSub3> getdata = api.sendsub3(new ReqBodySubHal1(id_user));
//            getdata.enqueue(new Callback<ResponsModelSub3>() {
//                @Override
//                public void onResponse(Call<ResponsModelSub3> call, Response<ResponsModelSub3> response) {
//                    String kode = response.body().getKode();
//
//                    if (kode.equals("1")) {
//                        mItems = response.body().getResult();
//                        for (int i = 0; i < mItems.size(); i++) {
//                            String namanasabah = mItems.get(i).getNamanasabah();
//                            String cif = mItems.get(i).getCif();
//                            String loanid = mItems.get(i).getLoanid();
//                            String hasilkunjungan = mItems.get(i).getHasilkunjungan();
//                            String bertemu = mItems.get(i).getBertemu();
//                            String lokasibertemu = mItems.get(i).getLokasibertemu();
//                            String actionplan = mItems.get(i).getActionplan();
//
//                            Datasub3_1 data = new Datasub3_1(namanasabah,cif,loanid,hasilkunjungan,bertemu,lokasibertemu,actionplan);
//                            datacontacted.add(data);
//
//                            Toast.makeText(SubHalaman3.this, namanasabah+"/"+cif , Toast.LENGTH_SHORT).show();
//                        }
//
//                            adapter.setData(datacontacted);
//                    } else {
////                        getdblocal();
//                        Toast.makeText(SubHalaman3.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponsModelSub3> call, Throwable t) {
//                    Toast.makeText(SubHalaman3.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
//        Intent i = new Intent(SubHalaman3.this, ActivityMain.class);
//        startActivity(i);
//        finish();
    }

    protected void onStop() {
        super.onStop();
//        timer = new Timer();
//        Log.i("Main", "Invoking logout timer");
//        SubHalaman3.LogOutTimerTask logoutTimeTask = new SubHalaman3.LogOutTimerTask();
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
//            conn = new Conectiondetector(SubHalaman3.this);
//            if (conn.isConected()) {
//                ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
//                Call<ResponsModelChangePswd> getdata = api.sendLogout(new ReqBodySubHal1(id_user));
//                getdata.enqueue(new Callback<ResponsModelChangePswd>() {
//                    @Override
//                    public void onResponse(Call<ResponsModelChangePswd> call, Response<ResponsModelChangePswd> response) {
//                        String kode = response.body().getKode();
//                        if (kode.equals("1")) {
//                            DBAdapter db = new DBAdapter(SubHalaman3.this);
//                            db.openDB();
//                            db.Deleteuser();
//                            db.close();
//                            Intent inten = new Intent(SubHalaman3.this, SplashScreen.class);
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
//                Toast.makeText(SubHalaman3.this, "timer cancel", Toast.LENGTH_SHORT).show();
//            }
////            //redirect user to login screen
////            DBAdapter db = new DBAdapter(SubHalaman3.this);
////            db.openDB();
////            db.Deleteuser();
////            db.close();
////            Intent inten = new Intent(SubHalaman3.this, SplashScreen.class);
////            startActivity(inten);
////            finish();
//        }
//    }
}
