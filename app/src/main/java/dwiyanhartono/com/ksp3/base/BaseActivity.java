package dwiyanhartono.com.ksp3.base;

import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import dwiyanhartono.com.ksp3.ActivityAssignment;
import dwiyanhartono.com.ksp3.ActivityMain;
import dwiyanhartono.com.ksp3.Conectiondetector;
import dwiyanhartono.com.ksp3.R;
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
    int AL = 900000;
    int TO = 30;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ceklog();
        gettimeral();
//        timer = new Timer();
//        LogOutTimerTask logoutTimeTask = new LogOutTimerTask();
//        Toast.makeText(this, "Start Timer", Toast.LENGTH_SHORT).show();
//        timer.schedule(logoutTimeTask, AL); //auto logout in 5 minutes 600000
        Log.i("onStart", "Start Base Activity");
        if (timer != null) {
                timer.cancel();
                Log.d("onStart1", "cancel timer");
                timer = null;
            }
    }


    private void gettimeral() {
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        Cursor c = db.gettimeral("M", "AL", "1");
//        AL = 900;
        if (c.moveToFirst()) {
            AL = c.getInt(0);
            Toast.makeText(this, String.valueOf(AL)+"AL" , Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, String.valueOf(AL)+"AL", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(this, "Start Timer", Toast.LENGTH_SHORT).show();
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
//                            notifLogout1();
                            Intent inten = new Intent(BaseActivity.this, SplashScreen.class);
                            startActivity(inten);
                            finish();
                        } else {
                            DBAdapter2 db = new DBAdapter2(BaseActivity.this);
                            db.openDB();
                            db.Deleteuser();
                            db.close();
//                            notifLogout1();
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
//                        notifLogout1();
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
//                notifLogout1();
                Intent inten = new Intent(BaseActivity.this, SplashScreen.class);
                startActivity(inten);
                finish();
                timer.cancel();
//                Toast.makeText(BaseActivity.this, "timer cancel", Toast.LENGTH_SHORT).show();
            }
            //redirect user to login screen

        }
    }

    public void notifLogout(int code) {
        AlertDialog.Builder alta = new AlertDialog.Builder(BaseActivity.this);
        alta.setTitle("Pesan");
        alta.setIcon(R.drawable.checklist);
        alta.setMessage("Anda yakin ingin keluar aplikasi ?");
        alta.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface diaAdapterDetailPlanvisit, int which) {
                if (code == 1) {
                    logout();
                } else {
                    logoutofline();
                }

            }
        });

        alta.create();
        alta.show();
    }

    public void notifLogout1() {
        AlertDialog.Builder alta = new AlertDialog.Builder(BaseActivity.this);
        alta.setTitle("Pesan");
        alta.setIcon(R.drawable.checklist);
        alta.setMessage("Waktu anda habis");
        alta.setCancelable(false);
        alta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent inten = new Intent(BaseActivity.this, SplashScreen.class);
                startActivity(inten);
                finish();
                dialog.dismiss();
            }
        });

        alta.create();
        alta.show();
    }


    public void logout() {
        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModelChangePswd> getdata = api.sendLogout(new ReqBodySubHal1(id_user));
        getdata.enqueue(new Callback<ResponsModelChangePswd>() {
            @Override
            public void onResponse(Call<ResponsModelChangePswd> call, Response<ResponsModelChangePswd> response) {
                String kode = response.body() != null ? response.body().getKode() : "9";
                if (kode.equals("1")) {
                    DBAdapter2 db = new DBAdapter2(BaseActivity.this);
                    db.openDB();
                    db.Deleteuser();
                    db.deleteparameter();
                    db.deletekunjunganall("1");
                    db.deletekpall();
                    db.deleteselfcuredall();
                    db.close();
                    Intent inten = new Intent(BaseActivity.this, SplashScreen.class);
                    startActivity(inten);
                    finish();
                } else if (kode.equals("9")) {

//                    Toast.makeText(ActivityAssignment.this, "Response code : as_LGT_01", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsModelChangePswd> call, Throwable t) {
//                Toast.makeText(ActivityAssignment.this, "Response code : as_LGTerr_01", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void logoutofline() {
        DBAdapter2 db = new DBAdapter2(BaseActivity.this);
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
        Intent inten = new Intent(BaseActivity.this, SplashScreen.class);
        startActivity(inten);
        finish();
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
