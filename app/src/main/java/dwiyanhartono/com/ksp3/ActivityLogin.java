package dwiyanhartono.com.ksp3;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import dwiyanhartono.com.ksp3.api.ApiRequestData;
import dwiyanhartono.com.ksp3.api.Retroserver;
import dwiyanhartono.com.ksp3.database.DBAdapter2;
import dwiyanhartono.com.ksp3.model.DataAP;
import dwiyanhartono.com.ksp3.model.DataKunjungan;
import dwiyanhartono.com.ksp3.model.DataUser;
import dwiyanhartono.com.ksp3.model.Datakp;
import dwiyanhartono.com.ksp3.model.Datasub8;
import dwiyanhartono.com.ksp3.model.ReqBodyKunjungan;
import dwiyanhartono.com.ksp3.model.ReqBodyLogoutoffline;
import dwiyanhartono.com.ksp3.model.ReqBodySubHal1;
import dwiyanhartono.com.ksp3.model.RequestActionplan;
import dwiyanhartono.com.ksp3.model.RequestLogin;
import dwiyanhartono.com.ksp3.model.RequestLupapassword;
import dwiyanhartono.com.ksp3.model.ResponsModelAP;
import dwiyanhartono.com.ksp3.model.ResponsModelChangePswd;
import dwiyanhartono.com.ksp3.model.ResponsModelDatauser;
import dwiyanhartono.com.ksp3.model.ResponsModelKunjungan;
import dwiyanhartono.com.ksp3.model.ResponsModelLupapassword;
import dwiyanhartono.com.ksp3.model.ResponsModelSub6;
import dwiyanhartono.com.ksp3.model.ResponsModelSub8;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLogin extends AppCompatActivity {

    EditText username, password;
    private DataUser datauser;
    String nama = "";
    String email = "";
    String iduser = "";
    String idbranch = "";
    String datetime = "";
    String imei = "";
    String datenow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
    String deviceUniqueIdentifier = "";
    Conectiondetector conect;

    private List<DataAP> list1 = new ArrayList<>();
    private List<DataKunjungan> list2 = new ArrayList<>();
    private List<Datasub8> list3 = new ArrayList<>();
    private List<Datakp> list4 = new ArrayList<>();
    private int REQUEST_CODE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        getDeviceIMEI();
        cekupdate();

        cancelAlarm();
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        kirimlogout();

    }

    private void cekupdate() {
//        AppUpdateManager updateManager = AppUpdateManagerFactory.create(ActivityLogin.this);
//        Task<AppUpdateInfo> appUpdateInfoTask = updateManager.getAppUpdateInfo();
//        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
//            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
//                try {
//                    updateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.IMMEDIATE, ActivityLogin.this, REQUEST_CODE);
//                } catch (IntentSender.SendIntentException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            Toast.makeText(this, "Start Download", Toast.LENGTH_SHORT).show();
            if (resultCode != RESULT_OK) {
                Toast.makeText(this, "gagal", Toast.LENGTH_SHORT).show();
                Log.d("Update", "onActivityResult: Update Failed" + resultCode);
            } else {
                Toast.makeText(this, "Finish", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    public void getDeviceIMEI() {
        TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm != null) {
            deviceUniqueIdentifier = tm.getDeviceId();

        }

        if (null == deviceUniqueIdentifier || 0 == deviceUniqueIdentifier.length()) {
            deviceUniqueIdentifier = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        }
    }

    public void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //Toast.makeText(this, "alarm close", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SaveLocation.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.cancel(pendingIntent);
    }


    public void kirimlogout() {
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        Cursor c = db.getLogout();

        conect = new Conectiondetector(ActivityLogin.this);
        if (c.moveToFirst()) {
            do {
                String nik = c.getString(1);
                String datetime = c.getString(2);
                if (conect.isConected()) {
                    ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
                    Call<ResponsModelChangePswd> getdata = api.sendLogoutoffline(new ReqBodyLogoutoffline(nik, datetime));
                    getdata.enqueue(new Callback<ResponsModelChangePswd>() {
                        @Override
                        public void onResponse(Call<ResponsModelChangePswd> call, Response<ResponsModelChangePswd> response) {
                            String kode = response.body().getKode();
                            if (kode.equals("1")) {
//                                Toast.makeText(ActivityLogin.this, "sukses", Toast.LENGTH_SHORT).show();
                            } else {
//                                Toast.makeText(ActivityLogin.this, "gagal", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponsModelChangePswd> call, Throwable t) {
//                            Toast.makeText(ActivityLogin.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(this, "Gangguan Pada Jaringan Anda", Toast.LENGTH_SHORT).show();
                }
            } while (c.moveToNext());
        }

        db.close();
    }

    String count;
    ProgressDialog pdLoading;
    String message;

    public void Login(final View view) {
        if (username.getText().toString().length() == 0) {
            username.setError("Username tidak boleh kosong");
        } else if (password.getText().toString().length() == 0) {
            password.setError("Password tidak boleh kosong");
        } else {
            pdLoading = new ProgressDialog(ActivityLogin.this);
            ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
            Call<ResponsModelDatauser> getdata = api.sendLogin(new RequestLogin(username.getText().toString(), password.getText().toString(), deviceUniqueIdentifier));
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
            getdata.enqueue(new Callback<ResponsModelDatauser>() {
                @Override
                public void onResponse(Call<ResponsModelDatauser> call, Response<ResponsModelDatauser> response) {
                    String kode = response.body() != null ? response.body().getKode() : "0";
                    if (kode.equals("1")) {
                        DBAdapter2 dbAdapter = new DBAdapter2(ActivityLogin.this);
                        dbAdapter.openDB();
                        datauser = response.body().getResult();
                        nama = datauser.getNama();
                        email = datauser.getEmail();
                        iduser = datauser.getId_user();
                        idbranch = datauser.getId_brnch();
                        count = datauser.getCountlogin();
                        imei = datauser.getImei();
                        datetime = datenow;
                        final String jabatan = datauser.getJabatan();
                        message = response.body().getMessage();

                        long result = dbAdapter.adduser(nama, email, jabatan, "1", iduser, idbranch, count, imei, datetime);
                        if (result > 0) {
                            downloadparameter();
                            downloadbp();
                        } else {
                            pdLoading.dismiss();
                        }
                        dbAdapter.close();
                    } else {
                        message = response.body().getMessage();
                        pdLoading.dismiss();
                        final AlertDialog.Builder alta = new AlertDialog.Builder(ActivityLogin.this);
                        alta.setTitle("Pesan");
                        alta.setIcon(R.drawable.warning);
                        alta.setMessage(message);
                        alta.setCancelable(false);
                        alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        final AlertDialog alertDialog = alta.create();
                        if (!ActivityLogin.this.isFinishing()) {
                            alertDialog.show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponsModelDatauser> call, Throwable t) {
                    pdLoading.dismiss();
                    AlertDialog.Builder alta = new AlertDialog.Builder(ActivityLogin.this);
                    alta.setTitle("Error");
                    alta.setIcon(R.drawable.warning);
                    alta.setMessage("Terjadi Masalah Pada Jaringan Anda :" + t.getMessage());
                    alta.setCancelable(false);
                    alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    final AlertDialog alertDialog = alta.create();
                    if (!ActivityLogin.this.isFinishing()) {
                        alertDialog.show();
                    }

                }
            });
        }
    }

    private void downloadkunjungan() {
        ApiRequestData api2 = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModelKunjungan> getkunjungan1 = api2.getkunjungan(new ReqBodyKunjungan(username.getText().toString()));
        getkunjungan1.enqueue(new Callback<ResponsModelKunjungan>() {
            @Override
            public void onResponse(Call<ResponsModelKunjungan> call, Response<ResponsModelKunjungan> response) {
                DBAdapter2 db = new DBAdapter2(ActivityLogin.this);
                db.openDB();
                db.deletekunjunganall("");
                String kode = response.body().getKode();
                int z = 0;
                int x = 1;
                if (kode.equals("1")) {
                    list2 = response.body().getResult();
                    z = list2.size();
                    for (int i = 0; i < list2.size(); i++) {
                        String iddata = list2.get(i).getIddata();
                        String codeimage = list2.get(i).getCodeimage();
                        String tujuan = list2.get(i).getTujuan();
                        String hasilkunjungan = list2.get(i).getHasilkunjungan();
                        String kethasilkunjungan = list2.get(i).getKethasilkunjungan();
                        String namadebitur = list2.get(i).getNamadebitur();
                        String cif = list2.get(i).getCif();
                        String ld = list2.get(i).getLoanID();
                        String statusactionplan = list2.get(i).getStatusactionplan();
                        String bertemu = list2.get(i).getBertemu();
                        String ketbertemu = list2.get(i).getKetbertemu();
                        String lokasibertemu = list2.get(i).getLokasibertemu();
                        String ketlokasi = list2.get(i).getKetlokasi();
                        String karakter = list2.get(i).getKarakter();
                        String negatifissue = list2.get(i).getNegatifissue();
                        String actionplan = list2.get(i).getActionplan();
                        String dateactionplan = list2.get(i).getDateactionplan();
                        String resume = list2.get(i).getResume();
                        String totaltunggakan = list2.get(i).getTotaltunggakan();
                        String totalbayar = list2.get(i).getTotalbayar();
                        String perkiraan = list2.get(i).getPerkiraan();
                        String tgvisit = list2.get(i).getTgvisit();
                        String lat = list2.get(i).getLat();
                        String lng = list2.get(i).getLng();
                        String ketkarakter = list2.get(i).getKetkarakter();
                        String notif = list2.get(i).getNotif();

                        db.addkunjungan(iddata, cif, codeimage, tujuan, hasilkunjungan, kethasilkunjungan, namadebitur, statusactionplan, bertemu, ketbertemu, lokasibertemu, ketlokasi, karakter, negatifissue, actionplan, dateactionplan, resume, totaltunggakan, totalbayar, perkiraan, tgvisit, lat, lng, "1", "", "", "", "", ketkarakter, notif, "", "", ld);

                        x++;
                    }
                    if (z == x) {
                        downloadselfcured();
                    } else {
                        downloadselfcured();
                    }
                }
                db.close();
            }

            @Override
            public void onFailure(Call<ResponsModelKunjungan> call, Throwable t) {
                downloadselfcured();
//                Toast.makeText(ActivityLogin.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void downloadparameter() {
        ApiRequestData api1 = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModelAP> getdataparam = api1.getparam(new RequestActionplan("M"));
        getdataparam.enqueue(new Callback<ResponsModelAP>() {
            @Override
            public void onResponse(Call<ResponsModelAP> call, Response<ResponsModelAP> response) {
                String kode = response.body().getKode();

                if (kode.equals("1")) {
                    list1 = response.body().getResult();
                    DBAdapter2 db = new DBAdapter2(ActivityLogin.this);
                    db.openDB();
                    db.deleteparameter();
                    int x = 0;
                    int z = 1;
                    x = list1.size();
//                    z = 1;
                    for (int i = 0; i < list1.size(); i++) {
                        String code = list1.get(i).getCode();
                        String type = list1.get(i).getType();
                        String status = list1.get(i).getStatus();
                        String desc = list1.get(i).getDesc();
                        String value = list1.get(i).getValue();

                        Cursor a = db.getlist(code, type);
                        a.moveToFirst();
                        if (a.getInt(0) > 0) {
                            db.updatelist(desc, code, status);
                        } else {
                            db.addlist(desc, value, type, code, status);
                        }

                        z++;
                    }
                    if (x == z) {

                        downloadkunjungan();
                    } else {
                        downloadkunjungan();
                    }
                    db.close();
                }

            }

            @Override
            public void onFailure(Call<ResponsModelAP> call, Throwable t) {
                downloadkunjungan();
//                Toast.makeText(ActivityLogin.this, "data tidak ada1 : " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void downloadselfcured() {
        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModelSub8> getdata = api.selfcured(new ReqBodySubHal1(username.getText().toString()));
        getdata.enqueue(new Callback<ResponsModelSub8>() {
            @Override
            public void onResponse(Call<ResponsModelSub8> call, Response<ResponsModelSub8> response) {
                String kode = response.body() != null ? response.body().getKode() : "9";
                if (kode.equals("1")) {
                    DBAdapter2 db = new DBAdapter2(ActivityLogin.this);
                    db.openDB();
                    list3 = response.body().getResult();

                    int x = 1;
                    int z = list3.size();
                    for (int i = 0; i < list3.size(); i++) {
                        String nama = list3.get(i).getNamaanggota();
                        String cif = list3.get(i).getCif();
                        String date = list3.get(i).getNamaproduk();

                        db.deleteselfcured(date);
                        Cursor a = db.getselfcured(cif);
                        a.moveToFirst();
                        if (a.getInt(0) > 0) {
                            db.updateselfcured(cif, nama, date);
                        } else {
                            db.addselfcured(cif, nama, date);
                        }
                        z++;
                    }
                    if (z == x) {

                        downloadbp();
                    } else {
                        downloadbp();
                    }


                    db.close();
                }
            }

            @Override
            public void onFailure(Call<ResponsModelSub8> call, Throwable t) {
//                Toast.makeText(ActivityLogin.this, "gagal Download Self Cured : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                downloadbp();
            }
        });

    }


    private void downloadkp() {
        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModelSub6> getdata = api.getkp(new ReqBodySubHal1(username.getText().toString()));
        getdata.enqueue(new Callback<ResponsModelSub6>() {
            @Override
            public void onResponse(Call<ResponsModelSub6> call, Response<ResponsModelSub6> response) {
                String kode = response.body() != null ? response.body().getKode() : "9";
                if (kode.equals("1")) {
                    DBAdapter2 db = new DBAdapter2(ActivityLogin.this);
                    db.openDB();
                    list4 = response.body().getResult();

                    int x = 1;
                    int z = list4.size();
                    if (list4.size() > 0) {
                        for (int i = 0; i < list4.size(); i++) {
                            String nama = list4.get(i).getNamaanggota();
                            String cif = list4.get(i).getCif();
                            String actionplan = list4.get(i).getActionplan();
                            String hasilkunjungan = list4.get(i).getHasilkunjungan();
                            String dateactionplan = list4.get(i).getTanggalaction();
                            String nominal = list4.get(i).getNominal();
                            String lokasibertemu = list4.get(i).getLokasibertemu();
                            String bertemu = list4.get(i).getBertemu();
                            String dateprocess = list4.get(i).getDateprocess();
                            //db.deletekp(dateprocess);
                            Cursor a = db.getkpcount();
                            a.moveToFirst();
                            if (a.getInt(0) > 0) {
                                db.updatekp(cif, nama, dateactionplan, bertemu, nominal, dateprocess, lokasibertemu, actionplan, hasilkunjungan);
//                                db.updateselfcured(cif, nama, dateprocess);
                            } else {
                                db.addkp(cif, nama, dateactionplan, bertemu, nominal, dateprocess, lokasibertemu, actionplan, hasilkunjungan);

                            }
                            x++;
                        }
                        if (x == z) {
                            pdLoading.dismiss();
                            AlertDialog.Builder alta = new AlertDialog.Builder(ActivityLogin.this);
                            alta.setTitle("Info");
                            alta.setIcon(R.drawable.checklist);
                            alta.setMessage(message);
                            alta.setCancelable(false);
                            alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (count.equals("0")) {
                                        Intent intent = new Intent(ActivityLogin.this, ChangePassword.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                }
                            });

                            final AlertDialog alertDialog = alta.create();
                            if (!ActivityLogin.this.isFinishing()) {
                                alertDialog.show();
                            }
                        } else {
                            pdLoading.dismiss();

                            AlertDialog.Builder alta = new AlertDialog.Builder(ActivityLogin.this);
                            alta.setTitle("Info");
                            alta.setIcon(R.drawable.checklist);
                            alta.setMessage(message);
                            alta.setCancelable(false);
                            alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (count.equals("0")) {
                                        Intent intent = new Intent(ActivityLogin.this, ChangePassword.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                }
                            });
                            final AlertDialog alertDialog = alta.create();
                            if (!ActivityLogin.this.isFinishing()) {
                                alertDialog.show();
                            }
                        }

                    } else {
                        pdLoading.dismiss();

                        AlertDialog.Builder alta = new AlertDialog.Builder(ActivityLogin.this);
                        alta.setTitle("Info");
                        alta.setIcon(R.drawable.checklist);
                        alta.setMessage(message);
                        alta.setCancelable(false);
                        alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (count.equals("0")) {
                                    Intent intent = new Intent(ActivityLogin.this, ChangePassword.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
                                    startActivity(intent);
                                    finish();
                                }
                                dialog.dismiss();
                            }
                        });
                        final AlertDialog alertDialog = alta.create();
                        alertDialog.show();
                        if (!ActivityLogin.this.isFinishing()) {
//                            alertDialog.show();
                        }

                    }
                    db.close();
                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponsModelSub6> call, Throwable t) {
                pdLoading.dismiss();
                AlertDialog.Builder alta = new AlertDialog.Builder(ActivityLogin.this);
                alta.setTitle("Info");
                alta.setIcon(R.drawable.checklist);
                alta.setMessage(message);
                alta.setCancelable(false);
                alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (count.equals("0")) {
                            Intent intent = new Intent(ActivityLogin.this, ChangePassword.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                });

                final AlertDialog alertDialog = alta.create();
                alertDialog.show();

                Toast.makeText(ActivityLogin.this, "gagal download Keep Promise : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                pdLoading.dismiss();

            }
        });

    }


    private void downloadbp() {
        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModelSub6> getdata = api.getbp(new ReqBodySubHal1(username.getText().toString()));
        getdata.enqueue(new Callback<ResponsModelSub6>() {
            @Override
            public void onResponse(Call<ResponsModelSub6> call, Response<ResponsModelSub6> response) {
                String kode = response.body() != null ? response.body().getKode() : "9";
                if (kode.equals("1")) {
                    DBAdapter2 db = new DBAdapter2(ActivityLogin.this);
                    db.openDB();
                    list4 = response.body().getResult();

                    int x = 1;
                    int z = list4.size();
                    for (int i = 0; i < list4.size(); i++) {
                        String nama = list4.get(i).getNamaanggota();
                        String cif = list4.get(i).getCif();
                        String actionplan = list4.get(i).getActionplan();
                        String hasilkunjungan = list4.get(i).getHasilkunjungan();
                        String dateactionplan = list4.get(i).getTanggalaction();
                        String nominal = list4.get(i).getNominal();
                        String lokasibertemu = list4.get(i).getLokasibertemu();
                        String bertemu = list4.get(i).getBertemu();
                        String dateprocess = list4.get(i).getDateprocess();
                        // db.deletekp(dateprocess);
                        Cursor a = db.getbp(cif);
                        a.moveToFirst();
                        if (a.getInt(0) > 0) {
                            db.updatebp(cif, nama, dateactionplan, bertemu, nominal, dateprocess, lokasibertemu, actionplan, hasilkunjungan);
//                                db.updateselfcured(cif, nama, dateprocess);
                        } else {
                            db.addbp(cif, nama, dateactionplan, bertemu, nominal, dateprocess, lokasibertemu, actionplan, hasilkunjungan);

                        }

                        x++;
                    }

                    if (z == x) {
                        downloadkp();
                    } else {
                        downloadkp();
                    }


                    db.close();
                } else {
                    downloadkp();
                }
            }

            @Override
            public void onFailure(Call<ResponsModelSub6> call, Throwable t) {
//                Toast.makeText(ActivityLogin.this, "gagal download Broken Promise : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                downloadkp();
            }
        });

    }

    public void lupapassword(View view) {

        showDialog();
    }

    private void showDialog() {
        final Dialog d = new Dialog(this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.lupa_password);

        final EditText username = (EditText) d.findViewById(R.id.txtusername);
        final EditText email = (EditText) d.findViewById(R.id.txtemail);
        Button savebtn = (Button) d.findViewById(R.id.btnkirim);
        Button cancel = (Button) d.findViewById(R.id.btncancel);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().length() == 0) {
                    username.setError("Username tidak boleh kosong");
                } else if (email.getText().toString().length() == 0) {
                    email.setError("Email tidak boleh kosong");
                } else {
                    final ProgressDialog pdLoading = new ProgressDialog(ActivityLogin.this);
                    ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
                    Call<ResponsModelLupapassword> getdata = api.sendlupapassword(new RequestLupapassword(username.getText().toString(), email.getText().toString()));
                    pdLoading.setMessage("\tLoading...");
                    pdLoading.setCancelable(false);
                    pdLoading.show();
                    getdata.enqueue(new Callback<ResponsModelLupapassword>() {

                        @Override
                        public void onResponse(Call<ResponsModelLupapassword> call, Response<ResponsModelLupapassword> response) {
                            String kode = response.body().getKode();
                            String message = response.body().getMessage();
                            if (kode.equals("1")) {

                                pdLoading.dismiss();
                                AlertDialog.Builder alta = new AlertDialog.Builder(ActivityLogin.this);
                                alta.setTitle("Info");
                                alta.setIcon(R.drawable.checklist);
                                alta.setMessage(message);
                                alta.setCancelable(false);
                                alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        d.dismiss();
                                    }
                                });

                                alta.create();
                                alta.show();
                            } else {
                                pdLoading.dismiss();
                                final AlertDialog.Builder alta = new AlertDialog.Builder(ActivityLogin.this);
                                alta.setTitle("Pesan");
                                alta.setIcon(R.drawable.warning);
                                alta.setMessage(message);

                                alta.setCancelable(false);
                                alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }

                                });

                                alta.create();
                                alta.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponsModelLupapassword> call, Throwable t) {
                            pdLoading.dismiss();
                            AlertDialog.Builder alta = new AlertDialog.Builder(ActivityLogin.this);
                            alta.setTitle("Error");
                            alta.setIcon(R.drawable.warning);
                            alta.setMessage("Terjadi Masalah Pada Jaringan Anda : " + t.getMessage());
                            alta.setCancelable(false);
                            alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            alta.create();
                            alta.show();
                        }
                    });
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();
    }


}