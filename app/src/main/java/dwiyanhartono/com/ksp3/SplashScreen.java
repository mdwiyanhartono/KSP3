package dwiyanhartono.com.ksp3;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import dwiyanhartono.com.ksp3.api.ApiRequestData;
import dwiyanhartono.com.ksp3.api.Retroserver;
import dwiyanhartono.com.ksp3.database.DBAdapter2;
import dwiyanhartono.com.ksp3.model.ReqBodySubHal1;
import dwiyanhartono.com.ksp3.model.RequestLogin;
import dwiyanhartono.com.ksp3.model.ResponsModelChangePswd;
import dwiyanhartono.com.ksp3.model.ResponsModelceklog;
import dwiyanhartono.com.ksp3.permision.PermissionHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity implements CheckRootTask.OnCheckRootFinishedListener {
    PermissionHelper permissionHelper;
    Intent intent;


    String statususer, date1, date, nama, email, count, id_user, imei;
    int UNINSTALL_REQUEST_CODE = 1;
    private final int REQUEST_CODE = 1;

    Conectiondetector conn = new Conectiondetector(this);

    @SuppressLint("ObsoleteSdkInt")
    @SuppressWarnings({"ResultOfMethodCallIgnored"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
//        cekupdate();
        permissionHelper = new PermissionHelper(this);

        date1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        CheckRootTask checkRootTask = new CheckRootTask(SplashScreen.this, SplashScreen.this);
        checkRootTask.execute(true);
        cancelAlarm();
        File file = new File(Environment.getExternalStorageDirectory(), "Colsys");
        if (!file.exists()) {
            file.mkdirs();
        }
    }


    private void cekupdate() {
        AppUpdateManager updateManager = AppUpdateManagerFactory.create(SplashScreen.this);
        Task<AppUpdateInfo> appUpdateInfoTask = updateManager.getAppUpdateInfo();
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                try {
                    updateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.IMMEDIATE, SplashScreen.this, REQUEST_CODE);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
            } else {
//                cekupdate();
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                CheckRootTask checkRootTask = new CheckRootTask(SplashScreen.this, SplashScreen.this);
                checkRootTask.execute(true);
                cancelAlarm();
            }
        });

    }

    private void ceklogserver() {

        if (conn.isConected()) {
            ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
            Call<ResponsModelceklog> getdata1 = api.cekLogin(new RequestLogin(id_user, "1", imei));
            getdata1.enqueue(new Callback<ResponsModelceklog>() {
                @Override
                public void onResponse(Call<ResponsModelceklog> call, Response<ResponsModelceklog> response) {
                    String kode = response.body() != null ? response.body().getKode() : "9";
                    switch (kode) {
                        case "1":
                            ceklog();
//                        retrivew();
                            break;
                        case "9":
                            Toast.makeText(SplashScreen.this, "Tejadi masalah segera hubungi admin \n error code : CKLGASMG1", Toast.LENGTH_SHORT).show();
//                    retrivew();
                            break;
                        default:
                            ceklog();
                            logout();
                            break;
                    }
                }

                @Override
                public void onFailure(Call<ResponsModelceklog> call, Throwable t) {
                    Toast.makeText(SplashScreen.this, "Tejadi masalah segera hubungi admin \n error code : CKLGASMG2 \n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void logout() {
        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModelChangePswd> getdata = api.sendLogout(new ReqBodySubHal1(id_user));
        getdata.enqueue(new Callback<ResponsModelChangePswd>() {
            @Override
            public void onResponse(Call<ResponsModelChangePswd> call, Response<ResponsModelChangePswd> response) {
                String kode = response.body() != null ? response.body().getKode() : "9";
                if (kode.equals("1")) {
                    DBAdapter2 db = new DBAdapter2(SplashScreen.this);
                    db.openDB();
                    db.Deleteuser();
                    db.deleteparameter();
                    db.deletekunjunganall("1");
                    db.deletekpall();
                    db.deleteselfcuredall();
                    db.close();
                    Intent inten = new Intent(SplashScreen.this, SplashScreen.class);
                    startActivity(inten);
//                    finish();
                } else if (kode.equals("9")) {
                    Toast.makeText(SplashScreen.this, "Response code : sp_LGT_01", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsModelChangePswd> call, Throwable t) {
                Toast.makeText(SplashScreen.this, "Response code : sp_LGTerr_02"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ceklog() {
        DBAdapter2 db = new DBAdapter2(this);
        //OPEN
        db.openDB();
        //SELECT
        Cursor c = db.getUser();
        if (c.moveToFirst()) {
            statususer = c.getString(3);
            nama = c.getString(0);
            email = c.getString(1);
            id_user = c.getString(4);
            count = c.getString(5);
            imei = c.getString(6);
            date = c.getString(7);

            Log.d("ceklog", "datedbuser: " + date);


        }


//        Toast.makeText(activityMain, date, Toast.LENGTH_SHORT).show();

    }

    private boolean checkAndRequestPermissions() {
        permissionHelper.permissionListener(new PermissionHelper.PermissionListener() {
            @Override
            public void onPermissionCheckDone() {

                try {
                    ceklog();
                    if (Objects.equals(statususer, "1") && Objects.equals(count, "1") && Objects.equals(date, date1)) {
                        intent = new Intent(SplashScreen.this, ActivityMain.class);
                        startActivity(intent);
                        finish();
                    } else if (Objects.equals(statususer, "1") && Objects.equals(count, "0") && Objects.equals(date, date1)) {
                        startActivity(new Intent(SplashScreen.this, ChangePassword.class));
                        cancelAlarm();
                        finish();
                    } else if (statususer == null) {
                        startActivity(new Intent(SplashScreen.this, ActivityLogin.class));
                        cancelAlarm();
                        finish();
                    } else if (Objects.equals(statususer, "1") && !Objects.equals(date, date1)) {
//                        startActivity(new Intent(SplashScreen.this, ActivityLogin.class));
                        logout();
                        cancelAlarm();
//                        finish();
                    } else {
                        startActivity(new Intent(SplashScreen.this, ActivityLogin.class));
                        cancelAlarm();
                        finish();
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }


            }
        });

        permissionHelper.checkAndRequestPermissions();

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionHelper.onRequestCallBack(requestCode, permissions, grantResults);
    }

    public void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //Toast.makeText(this, "alarm close", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SaveLocation.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.cancel(pendingIntent);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onCheckRootFinished(boolean isRooted) {

        if (isRooted) {
            AlertDialog infoDialog = new AlertDialog.Builder(this)
                    .setTitle("Colsys")
                    .setMessage("Device Not Safe")
                    .setCancelable(true)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent intent = new Intent(Intent.ACTION_DELETE);
                            intent.setData(Uri.parse("package:" + SplashScreen.this.getPackageName()));
                            intent.putExtra(Intent.EXTRA_RETURN_RESULT, true);
                            startActivityForResult(intent, UNINSTALL_REQUEST_CODE);
                        }
                    })
                    .create();
            infoDialog.show();
        } else {
            final int welcomeScreenDisplay = 3000; // 3000 = 3 detik
            Thread welcomeThread = new Thread() {

                int wait = 0;

                @Override
                public void run() {
                    try {
//                        ceklog();
                        super.run();
                        while (wait < welcomeScreenDisplay) {
                            sleep(100);
                            wait += 100;
                        }
                    } catch (Exception e) {
                        System.out.println("EXc=" + e);

                    } finally {
                        checkAndRequestPermissions();
                    }
                }
            };

            welcomeThread.start();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            Toast.makeText(this, "Start Download", Toast.LENGTH_SHORT).show();
            if (resultCode != RESULT_OK) {
                //cekupdate();
                Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show();
                Log.d("Update", "onActivityResult: Update Failed" + resultCode);
            } else {
                Toast.makeText(this, "Finish", Toast.LENGTH_SHORT).show();
            }

            if (resultCode == RESULT_CANCELED) {
                cekupdate();
            }
        } else {
            cekupdate();
        }
        if (requestCode == UNINSTALL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Log.d("TAG", "onActivityResult: user accepted the (un)install");
            } else if (resultCode == RESULT_CANCELED) {
                CheckRootTask checkRootTask = new CheckRootTask(SplashScreen.this, SplashScreen.this);
                checkRootTask.execute(true);
                Log.d("TAG", "onActivityResult: user canceled the (un)install");
            } else if (resultCode == RESULT_FIRST_USER) {
                CheckRootTask checkRootTask = new CheckRootTask(SplashScreen.this, SplashScreen.this);
                checkRootTask.execute(true);
                Log.d("TAG", "onActivityResult: failed to (un)install");
            }
        }
    }
}
