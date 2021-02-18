package dwiyanhartono.com.ksp3.base;

import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import dwiyanhartono.com.ksp3.Conectiondetector;
import dwiyanhartono.com.ksp3.SplashScreen;
import dwiyanhartono.com.ksp3.api.ApiRequestData;
import dwiyanhartono.com.ksp3.api.Retroserver;
import dwiyanhartono.com.ksp3.database.DBAdapter2;
import dwiyanhartono.com.ksp3.model.ReqBodySubHal1;
import dwiyanhartono.com.ksp3.model.ResponsModelChangePswd;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseActivity extends AppCompatActivity {
    String id_user, nama, email;
    private Timer timer;
    private Timer timer2;
    Conectiondetector conn;
    int AL = 7200000;
    int TO = 30;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ceklog();
        gettimeral();
    }


    private void gettimeral() {
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        Cursor c = db.gettimeral("M", "AL", "1");
        AL = 7200000 ;
        if (c.moveToFirst()) {
            AL = c.getInt(0);
//            Toast.makeText(this, String.valueOf(AL)+"AL" , Toast.LENGTH_SHORT).show();
        }
//        Toast.makeText(this, String.valueOf(AL)+"AL", Toast.LENGTH_SHORT).show();
        db.close();
    }


    private void ceklog() {
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        Cursor c = db.getUser();
        if (c.moveToFirst()) {
            id_user = c.getString(4);
            nama = c.getString(0);
            email = c.getString(1);
        }
        db.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (timer != null) {
            timer.cancel();
            Log.d("onResume", "cancel timer");
            timer = null;
        }
    }

    @Override
    protected void onDestroy() {
        Log.i("onDestroy", "BASE ACTIVITY");
        super.onDestroy();

    }

    @Override
    protected void onPause() {
        super.onPause();
        timer = new Timer();
        LogOutTimerTask logoutTimeTask = new LogOutTimerTask();
        timer.schedule(logoutTimeTask, AL); //auto logout in 5 minutes 600000
        Log.i("onPause", "START TIMER");
    }

    @Override
    protected void onStop() {
        super.onStop();
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        if (keyguardManager != null && !keyguardManager.isKeyguardLocked()) {
            if (timer != null) {
                timer.cancel();
                Log.d("onStop", "cancel timer");
                timer = null;
            }
        }
    }

    class LogOutTimerTask extends TimerTask {

        @Override
        public void run() {
            conn = new Conectiondetector(BaseActivity.this);
            if (conn.isConected()) {
                ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
                Call<ResponsModelChangePswd> getdata = api.sendLogout(new ReqBodySubHal1(id_user));
                getdata.enqueue(new Callback<ResponsModelChangePswd>() {
                    @Override
                    public void onResponse(Call<ResponsModelChangePswd> call, Response<ResponsModelChangePswd> response) {
                        String kode = response.body().getKode();
                        if (kode.equals("1")) {
                            DBAdapter2 db = new DBAdapter2(BaseActivity.this);
                            db.openDB();
                            db.Deleteuser();
                            db.close();
                            Intent inten = new Intent(BaseActivity.this, SplashScreen.class);
                            startActivity(inten);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModelChangePswd> call, Throwable t) {
                        DBAdapter2 db = new DBAdapter2(BaseActivity.this);
                        db.openDB();
                        String date = new SimpleDateFormat("yyyy_MM_dd_HHmmss", Locale.getDefault()).format(new Date());
                        long result = db.addlogout(id_user, date, "1");
                        if (result > 0) {
                            db.Deleteuser();
                        }

                        db.close();
                        Intent inten = new Intent(BaseActivity.this, SplashScreen.class);
                        startActivity(inten);
                        finish();
                    }
                });
            } else {
                DBAdapter2 db = new DBAdapter2(BaseActivity.this);
                db.openDB();
                String date = new SimpleDateFormat("yyyy_MM_dd_HHmmss", Locale.getDefault()).format(new Date());
                long result = db.addlogout(id_user, date, "1");
                if (result > 0) {
                    db.Deleteuser();
                }

                db.close();
                Intent inten = new Intent(BaseActivity.this, SplashScreen.class);
                startActivity(inten);
                finish();
//                timer.cancel();
//                Toast.makeText(BaseActivity.this, "timer cancel", Toast.LENGTH_SHORT).show();
            }
            //redirect user to login screen

        }
    }


    class ceklog1 extends Intent {

    }
    public void setAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(this, SaveLocation.class);
        ceklog1 ck;
        ck = new ceklog1();
        // Toast.makeText(this, String.valueOf(num), Toast.LENGTH_SHORT).show();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, ck, 0);
        alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), 1000, pendingIntent);
    }
}
