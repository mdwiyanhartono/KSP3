package dwiyanhartono.com.ksp3;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import dwiyanhartono.com.ksp3.api.ApiRequestData;
import dwiyanhartono.com.ksp3.api.Retroserver;
import dwiyanhartono.com.ksp3.base.BaseActivity;
import dwiyanhartono.com.ksp3.database.DBAdapter2;
import dwiyanhartono.com.ksp3.model.DataAP;
import dwiyanhartono.com.ksp3.model.DataModel;
import dwiyanhartono.com.ksp3.model.DataModelDetailAccount;
import dwiyanhartono.com.ksp3.model.DataModelTunggakan;
import dwiyanhartono.com.ksp3.model.DataTesimage;
import dwiyanhartono.com.ksp3.model.ReqBodyPlanVisit;
import dwiyanhartono.com.ksp3.model.ReqBodySubHal1;
import dwiyanhartono.com.ksp3.model.RequestKunjungan;
import dwiyanhartono.com.ksp3.model.RequestLogin;
import dwiyanhartono.com.ksp3.model.Requestgetimage;
import dwiyanhartono.com.ksp3.model.Requestupcontact;
import dwiyanhartono.com.ksp3.model.ResponsModel;
import dwiyanhartono.com.ksp3.model.ResponsModelChangePswd;
import dwiyanhartono.com.ksp3.model.ResponsModelDetailAccount;
import dwiyanhartono.com.ksp3.model.ResponsModelTunggakanDownload;
import dwiyanhartono.com.ksp3.model.ResponsModelcedownload;
import dwiyanhartono.com.ksp3.model.ResponsModelcontactupdate;
import dwiyanhartono.com.ksp3.model.ResponsModelcountassignment;
import dwiyanhartono.com.ksp3.model.ResponsModelimage;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityMain extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {

    RelativeLayout coordinatorLayout;
    String id_user, nama, email, ip, port, sisid, str;
    public static final int CONNECTION_TIMEOUT = 5000;
    public static final int READ_TIMEOUT = 5000;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    String nPermission = Manifest.permission.READ_PHONE_STATE;
    String deviceUniqueIdentifier = null;
    String imei;
    TextView sc, bp, kp, ptp, nocontacted, pf, contacted, novisit, visit, idagent, namaagent, area, status, textassignmnet;
    public int num = 600000;
    public int AL = 7200000;
    ImageView imageprofil;
    private List<DataModel> mItems = new ArrayList<>();


    private Timer timer;
    private Toolbar toolbar;

    String ab;
    String Date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    String bulan = new SimpleDateFormat("MMMM", Locale.getDefault()).format(new Date());

    ProgressDialog pdLoading;
    SwipeRefreshLayout swip;

    Conectiondetector conn = new Conectiondetector(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorLayout = findViewById(R.id.rl);
        gettimer();
        gettimeral();
        setAlarm();

        ceklog();
        swip = findViewById(R.id.swap);
        imageprofil = findViewById(R.id.imageprofil);
        idagent = findViewById(R.id.idagent);
        pf = findViewById(R.id.pf);
        textassignmnet = findViewById(R.id.txtasignment);
        visit = findViewById(R.id.txtvisit);
        novisit = findViewById(R.id.txtnovisit);
        contacted = findViewById(R.id.txtcontact);
        nocontacted = findViewById(R.id.txtnocontact);
        ptp = findViewById(R.id.txtptp);
        bp = findViewById(R.id.txtbp);
        kp = findViewById(R.id.txtkp);
        sc = findViewById(R.id.txtselfcured);
        namaagent = findViewById(R.id.nama);
        area = findViewById(R.id.area);
        status = findViewById(R.id.status);

        pf.setText("Performance Bulan " + bulan);
        idagent.setText(id_user);
        namaagent.setText(nama);
        area.setText(email);

        if (conn.isConected()) {
            getdatabase();
            getcount();
//            cekdownload();
            cekpending();
            //cekpendingapproval();
        } else {
            Toast.makeText(ActivityMain.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (ActivityCompat.checkSelfPermission(this, nPermission)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{nPermission},
                        REQUEST_CODE_PERMISSION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        swip.post(new Runnable() {
            @Override
            public void run() {
                if (conn.isConected()) {
                    getdatabase();
                    getcount();
                    // cekdownload();
                    cekpending();
                    //cekpendingapproval();
                } else {
                    swip.setRefreshing(false);
                    Toast.makeText(ActivityMain.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        swip.setOnRefreshListener(this);
    }

    private void gettimeral() {
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        Cursor c = db.gettimeral("M", "AL", "1");
        AL = 7200000;
        if (c.moveToFirst()) {
            AL = c.getInt(0);
//            Toast.makeText(this, String.valueOf(AL)+"AL" , Toast.LENGTH_SHORT).show();
        }
//        Toast.makeText(this, String.valueOf(AL)+"AL", Toast.LENGTH_SHORT).show();
        db.close();
    }

    private void cekdownload() {
        ApiRequestData apicekdown = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModelcedownload> cekdown = apicekdown.cekDownload(new RequestLogin(id_user, "1", imei));
        cekdown.enqueue(new Callback<ResponsModelcedownload>() {
            @Override
            public void onResponse(Call<ResponsModelcedownload> call, Response<ResponsModelcedownload> response) {
                String kode = response.body().getKode();
                String count1 = response.body().getCount();
                int a = Integer.valueOf(count1);
                if (kode.equals("1") && a > 0) {

                    AlertDialog.Builder alta = new AlertDialog.Builder(ActivityMain.this);
                    alta.setTitle("Pesan");
                    alta.setIcon(R.drawable.checklist);
                    alta.setMessage("Ada " + count1 + " data yang sudah di approved");
                    alta.setCancelable(false);
                    alta.setPositiveButton("DOWNLOAD", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            pdLoading = new ProgressDialog(ActivityMain.this);
                            pdLoading.setMessage("\tDownloading...");
                            pdLoading.setCancelable(false);
                            pdLoading.show();
                            downloadAccount();
                        }
                    });

                    alta.create();
                    alta.show();
                } else {

                }

            }

            @Override
            public void onFailure(Call<ResponsModelcedownload> call, Throwable t) {
//                Toast.makeText(ActivityMain.this, "Respose code : am_CKDWerr_02", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void cekpending() {
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        Cursor z = db.getkunjunganpending();
        if (z.moveToFirst()) {
            int pending = z.getInt(0);
            if (pending > 0) {
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Ada " + String.valueOf(pending) + " input kunjungan yang belum di upload ke server", Snackbar.LENGTH_LONG)
                        .setAction("Upload", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pdLoading = new ProgressDialog(ActivityMain.this);
                                pdLoading.setMessage("\tUpload...");
                                pdLoading.setCancelable(false);
                                pdLoading.show();
                                uploadkunjungan();
                            }
                        })
                        .setDuration(5000)
                        .setActionTextColor(ContextCompat.getColor(ActivityMain.this, R.color.red));

                snackbar.show();
            }

        } else {

        }
        db.close();
    }

    private void cekpendingapproval() {
        ApiRequestData apicekdown = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModelcedownload> cekpendingapproval = apicekdown.cekPendingApproval(new RequestLogin(id_user, "1", imei));
        cekpendingapproval.enqueue(new Callback<ResponsModelcedownload>() {
            @Override
            public void onResponse(Call<ResponsModelcedownload> call, Response<ResponsModelcedownload> response) {
                String kode = response.body().getKode();
                String count1 = response.body().getCount();
                int a = Integer.valueOf(count1);
                if (kode.equals("1") && a > 0) {
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, count1 + " Data menunggu approval", Snackbar.LENGTH_LONG)
                            .setAction("View", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            })
                            .setDuration(30000)
                            .setActionTextColor(ContextCompat.getColor(ActivityMain.this, R.color.red));

                    snackbar.show();
                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponsModelcedownload> call, Throwable t) {

            }
        });


        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        Cursor z = db.getkunjunganpending();
        if (z.moveToFirst()) {
            int pending = z.getInt(0);
            if (pending > 0) {
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Ada " + String.valueOf(pending) + " input kunjungan yang belum di upload ke server", Snackbar.LENGTH_LONG)
                        .setAction("Upload", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pdLoading = new ProgressDialog(ActivityMain.this);
                                pdLoading.setMessage("\tUpload...");
                                pdLoading.setCancelable(false);
                                pdLoading.show();
                                uploadkunjungan();
                            }
                        })
                        .setDuration(30000)
                        .setActionTextColor(ContextCompat.getColor(ActivityMain.this, R.color.red));

                snackbar.show();
            }

        } else {

        }
        db.close();
    }

    int totalImage, totalUploadedImage;
    String cifimage, keterangan, type, file, tglvisit, loanid, code;

    private void uploadkunjungan() {

        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        Cursor img4 = db.getimageall();
        totalImage = img4.getCount();
        if (img4.getCount() > 0) {
            if (img4.moveToFirst()) {
                totalUploadedImage = 0;
                do {
                    final String id = img4.getString(0);
                    cifimage = img4.getString(1);
                    keterangan = img4.getString(5);
                    type = img4.getString(7);
                    final String file2 = img4.getString(4);
                    tglvisit = img4.getString(6);
                    loanid = img4.getString(2);
                    code = img4.getString(3);

                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), getimage(file2));
                    MultipartBody.Part image = MultipartBody.Part.createFormData("image", getimage(file2).getName(), requestFile);
                    RequestBody f_cif = RequestBody.create(MediaType.parse("text/plan"), cifimage);
                    RequestBody f_loanid = RequestBody.create(MediaType.parse("text/plan"), loanid);
                    RequestBody f_type = RequestBody.create(MediaType.parse("text/plan"), type);
                    RequestBody f_code = RequestBody.create(MediaType.parse("text/plan"), code);
                    RequestBody f_tgl = RequestBody.create(MediaType.parse("text/plan"), tglvisit);
                    RequestBody f_keterangan = RequestBody.create(MediaType.parse("text/plan"), keterangan);
                    ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
                    Call<ResponsModel> getdata = api.sendimageik(image, f_cif, f_loanid, f_type, f_code, f_tgl, f_keterangan);
                    getdata.enqueue(new Callback<ResponsModel>() {
                        @Override
                        public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                            String kode = response.body() != null ? response.body().getKode() : "9";
//                            totalUploadedImage++;
//                            Toast.makeText(ActivityMain.this, kode+String.valueOf(" jumlah Image" +totalImage+ " loop "+totalUploadedImage), Toast.LENGTH_SHORT).show();
                            if (kode.equals("1")) {
                                DBAdapter2 db2 = new DBAdapter2(ActivityMain.this);
                                //OPEN
                                db2.openDB();
                                db2.updatestsimage(id, "1");
                                db2.close();

                                totalUploadedImage++;
                                deleteImage(file2);
                                if (totalImage == totalUploadedImage) {
//                                    Toast.makeText(ActivityMain.this, "finish", Toast.LENGTH_SHORT).show();
                                    sencontact();
                                }
                            } else {
                                AlertDialog.Builder alta = new AlertDialog.Builder(ActivityMain.this);
                                pdLoading.dismiss();
                                alta = new AlertDialog.Builder(ActivityMain.this);
                                alta.setTitle("Pesan");
                                alta.setIcon(R.drawable.warning);
                                alta.setMessage("Data Image Gagal Terkirim !");
                                alta.setCancelable(false);
                                alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        pdLoading.dismiss();
                                        dialog.dismiss();
                                    }
                                });

                                alta.create();
                                alta.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponsModel> call, Throwable t) {
                            totalUploadedImage++;
                            if (totalImage == totalUploadedImage) {
                                AlertDialog.Builder alta = new AlertDialog.Builder(ActivityMain.this);
                                alta.setTitle("Pesan");
                                alta.setIcon(R.drawable.warning);
                                alta.setMessage("Data Image Gagal Terkirim ! \r\n Terjadi Masalah : \r\n" + t.getMessage());
                                alta.setCancelable(false);
                                alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        pdLoading.dismiss();
                                        dialog.dismiss();
                                    }
                                });

                                alta.create();
                                alta.show();

                            }
                        }
                    });

                } while (img4.moveToNext());

            } else {
                sencontact();
            }

        } else {
            sencontact();
        }
        db.close();
    }

    public void deleteImage(String filename) {
        File fdelete = new File(Environment.getExternalStoragePublicDirectory("Colsys").getAbsolutePath(), "AppsPhoto" + "/" + filename);
        if (fdelete.exists()) {
            if (fdelete.delete()) {
                Log.e("-->", "file Deleted :");
            } else {
                Log.e("-->", "file not Deleted :");
            }
        }
    }

    int totalContact, totalUploadedContact;

    public void sencontact() {
        DBAdapter2 db = new DBAdapter2(ActivityMain.this);
        //OPEN
        db.openDB();
        Cursor cntc = db.getcontactallpending();
        if (cntc.getCount() > 0) {
            if (cntc.moveToFirst()) {

                totalContact = cntc.getCount();
                totalUploadedContact = 0;

                do {
                    final String cif = cntc.getString(1);
                    String Contact1 = cntc.getString(2);
                    String Contact2 = cntc.getString(3);
                    String Contact3 = cntc.getString(4);
                    String Contact4 = cntc.getString(5);
                    String Contact5 = cntc.getString(6);
                    String Contact6 = cntc.getString(7);
                    String Contact7 = cntc.getString(8);

                    ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
                    Call<ResponsModelcontactupdate> getdata = api.Updatecontact(new Requestupcontact(cif, Contact1, Contact2, Contact3, Contact4, Contact5, Contact6, Contact7));
                    getdata.enqueue(new Callback<ResponsModelcontactupdate>() {
                        @Override
                        public void onResponse(Call<ResponsModelcontactupdate> call, Response<ResponsModelcontactupdate> response) {
                            String kode = response.body().getKode();
                            if (kode.equals("1")) {
                                DBAdapter2 db1 = new DBAdapter2(ActivityMain.this);
                                db1.openDB();
                                db1.updatestscontact(cif, "1");
                                db1.close();

                                totalUploadedContact++;
                                if (totalContact == totalUploadedContact) {
                                    sendata();
                                }
                            } else {
                                totalUploadedContact++;
                                if (totalContact == totalUploadedContact) {
//                                    Toast.makeText(ActivityMain.this, "Response code : am_SDCTCers_01", Toast.LENGTH_SHORT).show();
                                    sendata();
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponsModelcontactupdate> call, Throwable t) {
                            totalUploadedContact++;
//                            sendata(codeimage1, lat, lng);
//                            pdLoading.dismiss();
                            if (totalContact == totalUploadedContact) {
//                                Toast.makeText(ActivityMain.this, "Response code : am_SDCTCerr_02", Toast.LENGTH_SHORT).show();
                                sendata();
                            }
                        }
                    });


                } while (cntc.moveToNext());


            } else {
//                Toast.makeText(this, "Contact Null", Toast.LENGTH_SHORT).show();
                sendata();
            }

        } else {
//            Toast.makeText(this, "Contact Null", Toast.LENGTH_SHORT).show();
            sendata();
        }
        db.close();

    }

    int totalData, totalUploadData;

    private void sendata() {
        DBAdapter2 db = new DBAdapter2(ActivityMain.this);
        db.openDB();
        Cursor x = db.getkunjunganpendingall();
        if (x.getCount() > 0) {
            totalUploadData = 0;
            totalData = x.getCount();
            if (x.moveToFirst()) {
                do {
                    final String idpending = x.getString(0);
                    String iddata = x.getString(1);
                    String lat = x.getString(2);
                    String lng = x.getString(3);
                    final String cifpending = x.getString(4);
                    final String code_image = x.getString(5);
                    String namadebitur = x.getString(6);
                    String statusactionplan = x.getString(7);
                    String tujuan = x.getString(8);
                    String hasilkunjungan = x.getString(9);
                    String kethasilkunjungan = x.getString(10);
                    String bertemu = x.getString(11);
                    String ketbertemu = x.getString(12);
                    String lokasibertemu = x.getString(13);
                    String ketlokasi = x.getString(14);
                    String karakter = x.getString(15);
                    String ketkarakter = x.getString(16);
                    String negatifissue = x.getString(17);
                    String actionplan = x.getString(18);
                    String dateactionplan = x.getString(19);
                    String resume = x.getString(20);
                    String totaltunggakan = x.getString(21);
                    String totalbayar = x.getString(22);
                    String perkiraan = x.getString(23);
                    String tgvisit = x.getString(24);
                    String edit_email = x.getString(25);
                    String edit_alamat = x.getString(26);
                    String edit_alamatusaha = x.getString(27);
                    String pihakbank = x.getString(28);
                    String notif = x.getString(29);
                    String status_kirim = x.getString(30);
                    ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
                    Call<ResponsModelChangePswd> getdata1 = api.insertkunjungan2(new RequestKunjungan(code_image, lat, lng, iddata, tujuan, namadebitur, cifpending, "0", hasilkunjungan, kethasilkunjungan, "Hidden", bertemu, ketbertemu, lokasibertemu, ketlokasi, karakter, ketkarakter == null ? "" : ketkarakter, negatifissue, actionplan, "", dateactionplan, resume, totaltunggakan, perkiraan, tgvisit, totalbayar, edit_email, edit_alamatusaha, edit_alamat, pihakbank,notif));
                    getdata1.enqueue(new Callback<ResponsModelChangePswd>() {
                        @Override
                        public void onResponse(Call<ResponsModelChangePswd> call, Response<ResponsModelChangePswd> response) {
                            //assert response.body() != null;
                            String kode = response.body() != null ? response.body().getKode() : "9";
                            String message = response.body() != null ? response.body().getMessage() : "Null";
                            switch (kode) {
                                case "1":
                                    DBAdapter2 db22 = new DBAdapter2(ActivityMain.this);
                                    db22.openDB();
                                    Long result = db22.updatelistpending(idpending);
                                    if (result > 0) {
                                        totalUploadData++;
                                    }
                                    db22.close();
                                    if (totalData == totalUploadData) {


                                        pdLoading.dismiss();
                                        AlertDialog.Builder alta = new AlertDialog.Builder(ActivityMain.this);
                                        alta.setTitle("Pesan");
                                        alta.setIcon(R.drawable.checklist);
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
                                    break;
                                case "9":
                                    totalUploadData++;
                                    if (totalData == totalUploadData) {
                                        pdLoading.dismiss();
                                        AlertDialog.Builder alta = new AlertDialog.Builder(ActivityMain.this);
                                        alta.setTitle("Pesan");
                                        alta.setIcon(R.drawable.checklist);
                                        alta.setMessage("Response code : am_SDDTA_01");
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
                                    break;
                                default:
                                    totalUploadData++;
                                    if (totalData == totalUploadData) {
                                        pdLoading.dismiss();
                                        AlertDialog.Builder alta = new AlertDialog.Builder(ActivityMain.this);
                                        alta.setTitle("Pesan");
                                        alta.setIcon(R.drawable.checklist);
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
                                    break;
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponsModelChangePswd> call, Throwable t) {
                            pdLoading.dismiss();
//                            Toast.makeText(ActivityMain.this, "Response code : am_SDDTAerr_02", Toast.LENGTH_SHORT).show();
                            Log.i("testresponse2", t.getMessage());
                        }
                    });
                } while (x.moveToNext());
            }
        } else {
            pdLoading.dismiss();
//            Toast.makeText(ActivityMain.this, "Data Null", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }


    public File getimage(String filename) {
        return new File(Environment.getExternalStoragePublicDirectory("iColls").getAbsolutePath(), "AppsPhoto/" + filename);
    }


    private void getcount() {
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        Cursor c = db.getkunjunganvisit();
        if (c.moveToFirst()) {
            int countvisit = c.getInt(0);
            visit.setText(String.valueOf(countvisit));
        }
        Cursor d = db.getkunjungancontacted();
        if (d.moveToFirst()) {
            int countcontacted = d.getInt(0);
            contacted.setText(String.valueOf(countcontacted));
        }
        Cursor e = db.getkunjungannocontacted();
        if (e.moveToFirst()) {
            int countnocontacted = e.getInt(0);
            nocontacted.setText(String.valueOf(countnocontacted));
        }
        Cursor f = db.getptptoday();
        if (f.moveToFirst()) {
            int ptptoday = f.getInt(0);
            ptp.setText(String.valueOf(ptptoday));
            if (ptptoday > 0) {


                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "HARI INI ADA " + ptp.getText().toString() + " PTP", Snackbar.LENGTH_LONG)
                        .setAction("View", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(ActivityMain.this, SubHalaman5.class);
                                startActivity(i);
                            }
                        })
                        .setDuration(30000)
                        .setActionTextColor(ContextCompat.getColor(ActivityMain.this, R.color.red));

                snackbar.show();

            }
            Cursor g = db.getselfcuredcount();
            if (g.moveToFirst()) {
                int selftcured = g.getInt(0);
                sc.setText(String.valueOf(selftcured));
            }
            Cursor h = db.getkpcount();
            if (h.moveToFirst()) {
                int kpcount = h.getInt(0);
                kp.setText(String.valueOf(kpcount));
            }

            Cursor i = db.getbpcount();
            if (i.moveToFirst()) {
                int bpcount = i.getInt(0);
                bp.setText(String.valueOf(bpcount));
            }
        }


        db.close();

    }

    private List<DataModelDetailAccount> mItems2 = new ArrayList<>();

    private List<DataModelTunggakan> datatunggakan = new ArrayList<>();

    private void downloadAccount() {
        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModelDetailAccount> getdata = api.downloadacount(new RequestLogin(id_user, "", imei));
        getdata.enqueue(new Callback<ResponsModelDetailAccount>() {
            @Override
            public void onResponse(Call<ResponsModelDetailAccount> call, Response<ResponsModelDetailAccount> response) {
                String kode = response.body() != null ? response.body().getKode() : "9";
                switch (kode) {
                    case "1":
                        mItems2 = response.body().getResult();
                        int size = mItems2.size();
                        int n = 0;
                        for (int i = 0; i < mItems2.size(); i++) {
                            String acctno = mItems2.get(i).getAcctno();
                            String nama = mItems2.get(i).getNama();
                            String cif = mItems2.get(i).getCif();
                            String alamat = mItems2.get(i).getAlamat();
                            String alamatusaha = mItems2.get(i).getAlamatusaha();
                            String email = mItems2.get(i).getEmail();
                            String cabang = mItems2.get(i).getCabang();
                            String aoname = mItems2.get(i).getAoname();
                            String agentid2 = mItems2.get(i).getAgentid();
                            String bucket2 = mItems2.get(i).getBucket();
                            String bucketeom = mItems2.get(i).getBucketeom();
                            String os2 = mItems2.get(i).getOs();
                            String totaltunggakan2 = mItems2.get(i).getTotaltunggakan();
                            String approved = mItems2.get(i).getApproved();
                            String dpd2 = mItems2.get(i).getDpd();
                            String loanid2 = mItems2.get(i).getLoanid();
                            String angsuran = mItems2.get(i).getAngsuran();
                            String notlp = mItems2.get(i).getNotlp();

                            DBAdapter2 db2 = new DBAdapter2(ActivityMain.this);
                            db2.openDB();
                            String dateaccount = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                            db2.deleteaccount(dateaccount);
                            db2.addacount(acctno, cif, acctno, nama, alamat, alamatusaha, notlp, email, cabang, aoname, "null", agentid2, bucket2, bucketeom, os2, totaltunggakan2, approved, dpd2, loanid2, angsuran, 0, dateaccount);
                            db2.close();


                            n++;

                        }
                        if (size == n) {
                            downloadtunggakan();
                        } else {
                            downloadtunggakan();
                        }
                        break;
                    case "9":
                        pdLoading.dismiss();
//                        Toast.makeText(ActivityMain.this, "Response code : am_ACC_01", Toast.LENGTH_SHORT).show();

                        break;
                    default:
                        pdLoading.dismiss();
//                        Toast.makeText(ActivityMain.this, "Data Null", Toast.LENGTH_SHORT).show();
                        break;
                }
            }


            @Override
            public void onFailure(Call<ResponsModelDetailAccount> call, Throwable t) {
                t.printStackTrace();
                pdLoading.dismiss();
//                Toast.makeText(ActivityMain.this, "Response code : am_ACCerr_02", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void downloadtunggakan() {
        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModelTunggakanDownload> getdata = api.downloadtunggakan(new RequestLogin(id_user, "", imei));
        getdata.enqueue(new Callback<ResponsModelTunggakanDownload>() {
            @Override
            public void onResponse(Call<ResponsModelTunggakanDownload> call, Response<ResponsModelTunggakanDownload> response) {
                String kode = response.body() != null ? response.body().getKode() : "9";
                datatunggakan = response.body() != null ? response.body().getResult() : datatunggakan;
                switch (kode) {
                    case "1":
//                    DBAdapter2 db = new DBAdapter2(ActivityMain.this);
//                    db.openDB();
//                    db.deletetunggakan();
//                    db.close();
                        int size = datatunggakan.size();
                        int n = 0;
                        for (int i = 0; i < datatunggakan.size(); i++) {
                            String ciftgkn = datatunggakan.get(i).getCif();
                            String idtgkn = datatunggakan.get(i).getId();
                            String totaldue = datatunggakan.get(i).getTotaldue();
                            String installmentdate = datatunggakan.get(i).getInstallmentdate();
                            String duepr = datatunggakan.get(i).getDuepr();
                            String duech = datatunggakan.get(i).getDuech();
                            String duein = datatunggakan.get(i).getDuein();
                            String nodaysod = datatunggakan.get(i).getNodaysod();
                            DBAdapter2 db2 = new DBAdapter2(ActivityMain.this);
                            db2.openDB();
                            db2.addtunggakan(ciftgkn, idtgkn, totaldue, installmentdate, duepr, duein, duech, nodaysod);
                            db2.close();

                            n++;

                        }

                        if (size == n) {
                            updateacount();
//                        pdLoading.dismiss();
//                        Toast.makeText(ActivityMain.this, "1", Toast.LENGTH_SHORT).show();
                        } else {
                            updateacount();
//                        pdLoading.dismiss();
//                        Toast.makeText(ActivityMain.this, "2", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "9":
//                        Toast.makeText(ActivityMain.this, "Response code : am_TGKN_01", Toast.LENGTH_SHORT).show();
                        updateacount();
                        break;
                    default:
                        updateacount();
                        break;
                }

            }

            @Override
            public void onFailure(Call<ResponsModelTunggakanDownload> call, Throwable throwable) {
//                Toast.makeText(ActivityMain.this, "Response code : am_TGKNerr_02", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateacount() {
        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModelcedownload> update = api.updateacount(new RequestLogin(id_user, "", imei));
        update.enqueue(new Callback<ResponsModelcedownload>() {
            @Override
            public void onResponse(Call<ResponsModelcedownload> call, Response<ResponsModelcedownload> response) {
                String kode = response.body() != null ? response.body().getKode() : "9";
                String count = response.body() != null ? response.body().getCount() : "null";
                switch (kode) {
                    case "1": {
                        pdLoading.dismiss();
                        AlertDialog.Builder alta = new AlertDialog.Builder(ActivityMain.this);
                        alta.setTitle("Pesan");
                        alta.setIcon(R.drawable.checklist);
                        alta.setMessage(count);
                        alta.setCancelable(false);
                        alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                            pdLoading.dismiss();
                                dialog.dismiss();
                            }
                        });

                        alta.create();
                        alta.show();
//                    pdLoading.dismiss();

                        break;
                    }
                    case "9": {
                        pdLoading.dismiss();
                        AlertDialog.Builder alta = new AlertDialog.Builder(ActivityMain.this);
                        alta.setTitle("Pesan");
                        alta.setIcon(R.drawable.warning);
                        alta.setMessage("Response code : am_UPTA_01");
                        alta.setCancelable(false);
                        alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                            pdLoading.dismiss();
                                dialog.dismiss();
                            }
                        });

                        alta.create();
                        alta.show();
//                    pdLoading.dismiss();
                        break;
                    }
                    default: {
                        pdLoading.dismiss();
                        AlertDialog.Builder alta = new AlertDialog.Builder(ActivityMain.this);
                        alta.setTitle("Pesan");
                        alta.setIcon(R.drawable.warning);
                        alta.setMessage("Download Tidak Complete");
                        alta.setCancelable(false);
                        alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                            pdLoading.dismiss();
                                dialog.dismiss();
                            }
                        });

                        alta.create();
                        alta.show();
                        break;
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponsModelcedownload> call, Throwable t) {
//                Toast.makeText(ActivityMain.this, "Response code : am_UPTAerr_02", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<DataAP> list1 = new ArrayList<>();

    private List<DataTesimage> dataimage = new ArrayList<>();

    String count = "";

    private void getdatabase() {
        swip.setRefreshing(true);
        if (conn.isConected()) {
            ApiRequestData api12 = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
            Call<ResponsModelcountassignment> getdatacount = api12.viewcountassignment(new ReqBodyPlanVisit(id_user));
            getdatacount.enqueue(new Callback<ResponsModelcountassignment>() {
                @Override
                public void onResponse(Call<ResponsModelcountassignment> call, Response<ResponsModelcountassignment> response) {

                    count = response.body() != null ? response.body().getCount() : "0";
                    String count1 = response.body() != null ? response.body().getCount1() : "0";
                    String count2 = response.body() != null ? response.body().getCount2() : "0";
                    String count3 = response.body() != null ? response.body().getCount3() : "0";
                    String count4 = response.body() != null ? response.body().getCount4() : "0";
                    String count5 = response.body() != null ? response.body().getCount5() : "0";
                    String count6 = response.body() != null ? response.body().getCount6() : "0";
                    String count7 = response.body() != null ? response.body().getCount7() : "0";
                    String count8 = response.body() != null ? response.body().getCount8() : "0";
                    textassignmnet.setText(count);
//                    visit.setText(count1);
                    novisit.setText(count2);
                    swip.setRefreshing(false);
                }

                @Override
                public void onFailure(Call<ResponsModelcountassignment> call, Throwable t) {
//                    Toast.makeText(ActivityMain.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    textassignmnet.setText("0");
                    novisit.setText("0");
                    swip.setRefreshing(false);
                }
            });

            dataimage.clear();
            ApiRequestData api13 = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
            final Call<ResponsModelimage> getimage = api13.getiamge(new Requestgetimage(id_user, "0", "0"));
            getimage.enqueue(new Callback<ResponsModelimage>() {
                @Override
                public void onResponse(Call<ResponsModelimage> call, Response<ResponsModelimage> response) {
                    String kode = response.body() != null ? response.body().getKode() : "9";
                    dataimage = response.body().getResult();
                    if (kode.equals("1")) {
                        for (int i = 0; i < dataimage.size(); i++) {
                            byte[] decodedString1 = Base64.decode(dataimage.get(i).getF_file_doc(), Base64.DEFAULT);
                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString1, 0, decodedString1.length);
                            imageprofil.setImageBitmap(decodedByte);
                        }

                    } else if (kode.equals("9")) {
//                        Toast.makeText(ActivityMain.this, "Response code : am_PP_01", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<ResponsModelimage> call, Throwable t) {
                    //  Toast.makeText(ActivityMain.this, "Response code : am_PPerr_02" + t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        } else {
            Toast.makeText(ActivityMain.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void gettimer() {
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        Cursor c = db.gettimer("M", "TMR", "1");
        num = 600000;
        if (c.moveToFirst()) {
            num = c.getInt(0);
//            Toast.makeText(this, String.valueOf(num) , Toast.LENGTH_SHORT).show();
        }
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
            imei = c.getString(6);
        }
        db.close();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {

        }*/
        if (id == R.id.action_change) {
            Intent inten = new Intent(ActivityMain.this, ChangePassword.class);
            startActivity(inten);
//            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(ActivityMain.this, ActivityDailyPlanVisit.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(ActivityMain.this, ActivityPTP.class);
            startActivity(intent);
            /*Intent intent = new Intent(ActivityMain.this,ActivityDetailInventory.class);
            startActivity(intent);*/

        } else if (id == R.id.nav_manage) {
//            DBAdapter db = new DBAdapter(ActivityMain.this);
//            db.openDB();
//            db.Deleteuser();
//            db.close();
//            Intent inten = new Intent(ActivityMain.this, SplashScreen.class);
//            startActivity(inten);
//            finish();
            if (conn.isConected()) {
                ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
                Call<ResponsModelChangePswd> getdata = api.sendLogout(new ReqBodySubHal1(id_user));
                getdata.enqueue(new Callback<ResponsModelChangePswd>() {
                    @Override
                    public void onResponse(Call<ResponsModelChangePswd> call, Response<ResponsModelChangePswd> response) {
                        String kode = response.body().getKode();
                        if (kode.equals("1")) {
                            DBAdapter2 db = new DBAdapter2(ActivityMain.this);
                            db.openDB();
                            db.deleteparameter();
                            db.deleteparameter();
                            db.deletekunjunganall("1");
                            db.deletekpall();
                            db.deleteselfcuredall();
//                            db.deleteselfcuredall();
                            db.Deleteuser();
                            db.close();
                            Intent inten = new Intent(ActivityMain.this, SplashScreen.class);
                            startActivity(inten);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModelChangePswd> call, Throwable t) {
                        DBAdapter2 db = new DBAdapter2(ActivityMain.this);
                        db.openDB();
                        String date = new SimpleDateFormat("yyyy_MM_dd_HHmmss", Locale.getDefault()).format(new Date());
                        long result = db.addlogout(id_user, date, "1");
                        if (result > 0) {
                            db.deleteparameter();
                            db.deletekunjunganall("1");
                            db.deletekpall();
                            db.deleteselfcuredall();
                            db.Deleteuser();
                        }
                        db.close();
                        Intent inten = new Intent(ActivityMain.this, SplashScreen.class);
                        startActivity(inten);
                        finish();
                    }
                });
            } else {
                DBAdapter2 db = new DBAdapter2(ActivityMain.this);
                db.openDB();
                String date = new SimpleDateFormat("yyyy_MM_dd_HHmmss", Locale.getDefault()).format(new Date());
                long result = db.addlogout(id_user, date, "1");
                if (result > 0) {
                    db.deleteparameter();
                    db.deletekunjunganall("1");
                    db.deletekpall();
                    db.deleteselfcuredall();
                    db.Deleteuser();
                }
                db.close();
                Intent inten = new Intent(ActivityMain.this, SplashScreen.class);
                startActivity(inten);
                finish();
            }
//            DBAdapter2 db = new DBAdapter2(ActivityMain.this);
//            db.openDB();
//            db.Deleteuser();
//            db.deleteparameter();
//            db.deletekunjunganall("1");
//            db.deletekpall();
//            db.deleteselfcuredall();
////            db.Deleteuser();
//            db.close();
//            Intent inten = new Intent(ActivityMain.this, SplashScreen.class);
//            startActivity(inten);
//            finish();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void asignment(View view) {
        Intent intent = new Intent(ActivityMain.this, ActivityAssignment.class);
        startActivity(intent);
    }

    public void visit(View view) {
        Intent i = new Intent(ActivityMain.this, SubHalaman1.class);
        startActivity(i);
//        finish();
    }

    public void novisit(View view) {
        Intent i = new Intent(ActivityMain.this, SubHal2.class);
        startActivity(i);
//        finish();
    }

    public void contact(View view) {
        Intent i = new Intent(ActivityMain.this, SubHalaman3.class);
        startActivity(i);
//        finish();
    }

    public void nocantact(View view) {
        Intent i = new Intent(ActivityMain.this, SubHalaman4.class);
        startActivity(i);
//        finish();
    }

    public void selfcured(View view) {
        Intent i = new Intent(ActivityMain.this, SubHalaman8.class);
        startActivity(i);
//        finish();
    }

    public void ptp(View view) {
        Intent i = new Intent(ActivityMain.this, SubHalaman5.class);
        startActivity(i);
//        finish();
    }

    public void bp(View view) {
        Intent i = new Intent(ActivityMain.this, SubHalaman6.class);
        startActivity(i);
//        finish();
    }

    public void kp(View view) {
        Intent i = new Intent(ActivityMain.this, SubHalaman7.class);
        startActivity(i);
//        finish();
    }

    public void setAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, SaveLocation.class);
        // Toast.makeText(this, String.valueOf(num), Toast.LENGTH_SHORT).show();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), num, pendingIntent);
    }

    public void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //Toast.makeText(this, "alarm close", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SaveLocation.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.cancel(pendingIntent);
    }

    public void profil(View view) {
        Intent intent = new Intent(ActivityMain.this, MainActivityPhoto.class);
        intent.putExtra("agentid", id_user);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        timer = new Timer();
        Log.d("onDestroyMain", "START TIMER");
        LogOutTimerTask logoutTimeTask = new LogOutTimerTask();
        timer.schedule(logoutTimeTask, AL); //auto logout in 5 minutes 600000
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (conn.isConected()) {
            getdatabase();
            getcount();
            cekdownload();
            cekpending();
            //cekpendingapproval();
        } else {
            Toast.makeText(ActivityMain.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

        String Date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    }

    @Override
    public void onRefresh() {
        if (conn.isConected()) {
            getdatabase();
            getcount();
            cekdownload();
            cekpending();
            //cekpendingapproval();
        } else {
            swip.setRefreshing(false);
            Toast.makeText(ActivityMain.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    class LogOutTimerTask extends TimerTask {

        @Override
        public void run() {
            conn = new Conectiondetector(ActivityMain.this);
            if (conn.isConected()) {
                ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
                Call<ResponsModelChangePswd> getdata = api.sendLogout(new ReqBodySubHal1(id_user));
                getdata.enqueue(new Callback<ResponsModelChangePswd>() {
                    @Override
                    public void onResponse(Call<ResponsModelChangePswd> call, Response<ResponsModelChangePswd> response) {
                        String kode = response.body().getKode();
                        if (kode.equals("1")) {
                            DBAdapter2 db = new DBAdapter2(ActivityMain.this);
                            db.openDB();
                            db.Deleteuser();
                            db.close();
                            Intent inten = new Intent(ActivityMain.this, SplashScreen.class);
                            startActivity(inten);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModelChangePswd> call, Throwable t) {

                        DBAdapter2 db = new DBAdapter2(ActivityMain.this);
                        db.openDB();
                        db.Deleteuser();
                        db.close();
                        Intent inten = new Intent(ActivityMain.this, SplashScreen.class);
                        startActivity(inten);
                    }
                });
            }
            //redirect user to login screen


            if (timer != null) {
                timer.cancel();
                Log.i("AfterDelete", "cancel timer");
                timer = null;
            }

            finish();
        }
    }
}