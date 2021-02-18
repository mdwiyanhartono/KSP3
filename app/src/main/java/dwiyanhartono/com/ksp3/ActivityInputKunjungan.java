package dwiyanhartono.com.ksp3;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.zj.btsdk.BluetoothService;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.Print.Bluetooth;
import dwiyanhartono.com.ksp3.Print.DeviceListActivity;
import dwiyanhartono.com.ksp3.Print.PrinterCommand;
import dwiyanhartono.com.ksp3.api.ApiRequestData;
import dwiyanhartono.com.ksp3.api.Retroserver;
import dwiyanhartono.com.ksp3.base.BaseActivity;
import dwiyanhartono.com.ksp3.database.DBAdapter;
import dwiyanhartono.com.ksp3.database.DBAdapter2;
import dwiyanhartono.com.ksp3.model.DataAP;
import dwiyanhartono.com.ksp3.model.DataContact;
import dwiyanhartono.com.ksp3.model.DataTunggakan;
import dwiyanhartono.com.ksp3.model.DataTunggakanLocal;
import dwiyanhartono.com.ksp3.model.ReqBodySubHal1;
import dwiyanhartono.com.ksp3.model.RequestKunjungan;
import dwiyanhartono.com.ksp3.model.Requestupcontact;
import dwiyanhartono.com.ksp3.model.ResponsModel;
import dwiyanhartono.com.ksp3.model.ResponsModelChangePswd;
import dwiyanhartono.com.ksp3.model.ResponsModelcontactupdate;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityInputKunjungan extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {


    private static final int REQUEST_ENABLE_BT = 4;
    private static final int REQUEST_CONNECT_DEVICE = 6;

    //    Conectiondetector conn;
    ImageView image_view, image_viewimage;
    Button btn_doc, btn_photo, btnbatal, btnsimpan, btncontact;
    LinearLayout ly1, ly2;
    EditText pihakbank, editalamatrm, edtemail2, getAlamatusaha1, alamatusaha1, totalap, tanggalap, keterangan4, actionplan, tujuan, namanasabah, alamatrumah, alamatusaha, cif, tlpon, email, hasilkunjungan, tanggalptp, keterangan1, keterangan2, keterangan3,
            bertemudengan, notif, lokasibertemu, resumenasabah, karakternasabah, dpd, bucketeom, totaltunggakan, totalbayar, perkiraan;
    RadioGroup pilih1, pilih2;
    RadioButton ya, ya2, tidak, tidak2;
    LinearLayout lyptp;
    LinearLayout lybtnimage1, lybtnimage2, lylokasibertemu;
    Spinner spinner1, spinner2, spinner3, spinner4, spinner5, spinner6, spinner7;

    TextView totaldue, notlp;
    List<String> IstSource1 = new ArrayList<>();
    List<String> IstSource2 = new ArrayList<>();
    List<String> IstSource3 = new ArrayList<>();
    List<String> IstSource4 = new ArrayList<>();
    List<String> IstSource5 = new ArrayList<>();
    List<String> IstSource6 = new ArrayList<>();
    List<String> IstSource7 = new ArrayList<>();

    Intent intent;

    String sendimage = "";
    String coderefrence = "00000";
    String contactsend = "";
    String datasend = "";

    String negatifissue = "";
    String printing = "";
    public final int REQUEST_CAMERA = 0;
    public final int REQUEST_CAMERA2 = 1;
    public final int SELECT_FILE = 1;
    int max_resolution_image = 800;
    String imageNameByTime = "";
    String imageNameByTime2 = "";
    int id;
    Bitmap bitmap = null, decoded;
    String date = "";
    String datevisit = "";
    String datecode = "";
    String codeimage1 = "";
    String a = "";
    String angsuran = "";
    String notelp = "";
    String dateparse = "";
    String lat = "";
    String lng = "";

    RecyclerView rc;
    private List<DataContact> Contacts = new ArrayList<>();
    String contact1, contact2, contact3, contact4, contact5, contact6, contact7, f_cif;
    String statususer, id_user, loan, nama_user;

    private List<DataTunggakan> tunggakan = new ArrayList<>();
    private List<DataTunggakanLocal> tunggakanlocal = new ArrayList<>();

    MapLocation mapLocation = new MapLocation();

    Adapterrowtabletunggakan adaptertunggakan;

    ProgressDialog pdLoading;

    //Check Upload
    int totalImage = 0;
    int totalUploadedImage = 0;

    int totalContact = 0;
    int totalUploadedContact = 0;

    Conectiondetector conn = new Conectiondetector(this);

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String tanggalAP = "[a-zA-Z0-9]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_kunjungan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);
        final DecimalFormat df = (DecimalFormat) nf;
        {
            df.applyPattern("#,###,###,###,###.##");
        }
//                new DecimalFormat("#,###,###,###,###.##");

        dateparse = new SimpleDateFormat("yyyy-MM", Locale.getDefault()).format(new Date());

        ceklog();
        btnbatal = (Button) findViewById(R.id.btnbtl);
        btnsimpan = (Button) findViewById(R.id.btnsimpan);
        btncontact = (Button) findViewById(R.id.btncontact);
        btn_photo = (Button) findViewById(R.id.btn_photo);
        btn_doc = (Button) findViewById(R.id.btn_doc);
        image_view = (ImageView) findViewById(R.id.image_view);
        image_viewimage = (ImageView) findViewById(R.id.image_viewimage);
        ly1 = (LinearLayout) findViewById(R.id.lyimage);
        ly2 = (LinearLayout) findViewById(R.id.lyimage2);
        lybtnimage1 = (LinearLayout) findViewById(R.id.lybtnimage1);
        lybtnimage2 = (LinearLayout) findViewById(R.id.lybtnimage2);
        lylokasibertemu = (LinearLayout) findViewById(R.id.lylokasibertemu);
        notlp = (TextView) findViewById(R.id.notlp);
//        lyptp = (LinearLayout) findViewById(R.id.lyptp);

        tujuan = (EditText) findViewById(R.id.edttujuan);
//        totalap = (EditText) findViewById(R.id.edttotalpp);
        tanggalap = (EditText) findViewById(R.id.edttanggalap);
        alamatrumah = (EditText) findViewById(R.id.edtalamatrm);
        pihakbank = (EditText) findViewById(R.id.pihakbank);
        alamatusaha = (EditText) findViewById(R.id.edtalmtush);
        alamatusaha1 = (EditText) findViewById(R.id.edtalmtush1);
        namanasabah = (EditText) findViewById(R.id.edtnama);
        email = (EditText) findViewById(R.id.edtemail);
        edtemail2 = (EditText) findViewById(R.id.edtemail2);
        cif = (EditText) findViewById(R.id.edtcif);
        /*tlpon = (EditText) findViewById(R.id.edttlp);*/
        hasilkunjungan = (EditText) findViewById(R.id.edthasil);
        keterangan1 = (EditText) findViewById(R.id.edtketerangan);
//        tanggalptp = (EditText) findViewById(R.id.edttanggalptp);
        keterangan2 = (EditText) findViewById(R.id.edtketerangan2);
        keterangan3 = (EditText) findViewById(R.id.edtketerangan3);
        keterangan4 = (EditText) findViewById(R.id.edtketerangan4);
        actionplan = (EditText) findViewById(R.id.edtactionplan);
        karakternasabah = (EditText) findViewById(R.id.edtkarakternasabah);
        notif = (EditText) findViewById(R.id.edtnotif);
        resumenasabah = (EditText) findViewById(R.id.edtresumenasabah);
        bertemudengan = (EditText) findViewById(R.id.edtbertemu);
        lokasibertemu = (EditText) findViewById(R.id.edtlokasibertemu);
        totaltunggakan = (EditText) findViewById(R.id.edttotaltunggakan);
        totalbayar = (EditText) findViewById(R.id.edttotalbayar);
        perkiraan = (EditText) findViewById(R.id.edtperkiraan);
        bucketeom = (EditText) findViewById(R.id.edtbucketeom);
        editalamatrm = (EditText) findViewById(R.id.editalamatrm);
        dpd = (EditText) findViewById(R.id.edtdpd);
        totaldue = (TextView) findViewById(R.id.totaldue);
        dpd.setEnabled(false);
        bucketeom.setEnabled(false);

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner4 = (Spinner) findViewById(R.id.spinner4);
        spinner5 = (Spinner) findViewById(R.id.spinner5);
        spinner6 = (Spinner) findViewById(R.id.spinner6);
        spinner7 = (Spinner) findViewById(R.id.spinner7);


        /// GET EXTRAS
        Intent i = getIntent();
        cif.setText(i.getExtras().getString("cif"));
        namanasabah.setText(i.getExtras().getString("nama"));
        alamatrumah.setText(i.getExtras().getString("alamat"));
        alamatusaha.setText(i.getExtras().getString("alamatusaha"));
        //Toast.makeText(this, i.getExtras().getString("alamatusaha"), Toast.LENGTH_SHORT).show();
        email.setText(i.getExtras().getString("email"));
        bucketeom.setText(i.getExtras().getString("bucketeom"));
        dpd.setText(i.getExtras().getString("dpd"));
        loan = i.getExtras().getString("loanid");
        totaldue.setText(i.getExtras().getString("totaldue"));
        totaltunggakan.setText(i.getExtras().getString("totaldue"));
        angsuran = i.getExtras().getString("angsuran");
        if (angsuran.equals("")) {
            angsuran = "0.00";
        }
        notlp.setText(i.getExtras().getString("notlp"));
        /////////////////////////////////////////
        btncontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog();
            }
        });
        datevisit = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        datecode = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());

        codeimage1 = cif.getText().toString() + datecode;
        coderefrence = cif.getText().toString() + datecode;


        totalbayar.addTextChangedListener(new NumberTextWatcher(totaltunggakan.getText().toString().trim(), totalbayar, "#,###,###,###,###.00", perkiraan, angsuran));
//        Toast.makeText(ActivityInputKunjungan.this, codeimage1, Toast.LENGTH_SHORT).show();
        btnbatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        btnsimpan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (printing.equals("1") && !Bluetooth.isPrinterConnected(getApplicationContext(), ActivityInputKunjungan.this)) {
////                    pdLoading.dismiss();
//                    Bluetooth.connectPrinter(getApplicationContext(), ActivityInputKunjungan.this);
//                } else {
//                    print();
//                }
//            }
//        });

        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBAdapter db = new DBAdapter(ActivityInputKunjungan.this);
                db.openDB();

                String tj = tujuan.getText().toString();
                String hk = hasilkunjungan.getText().toString();
                String bd = bertemudengan.getText().toString();
                String lb = lokasibertemu.getText().toString();
                String kn = karakternasabah.getText().toString();
                String ap = actionplan.getText().toString();
                if (tj.equals("Select")) {
                    AlertDialog.Builder alta = new AlertDialog.Builder(ActivityInputKunjungan.this);
                    alta.setTitle("Error");
                    alta.setIcon(R.drawable.warning);
                    alta.setMessage("Tujuan Visit Harus Di Pilih");
                    alta.setCancelable(false);
                    alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alta.create();
                    alta.show();
                } else if (hk.equals("Select")) {
                    AlertDialog.Builder alta = new AlertDialog.Builder(ActivityInputKunjungan.this);
                    alta.setTitle("Error");
                    alta.setIcon(R.drawable.warning);
                    alta.setMessage("Hasil Kunjungan Harus Di Pilih");
                    alta.setCancelable(false);
                    alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alta.create();
                    alta.show();
                } else if (bd.equals("Select")) {
                    AlertDialog.Builder alta = new AlertDialog.Builder(ActivityInputKunjungan.this);
                    alta.setTitle("Error");
                    alta.setIcon(R.drawable.warning);
                    alta.setMessage("Bertemu Dengan Harus Di Pilih");
                    alta.setCancelable(false);
                    alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alta.create();
                    alta.show();
                } else if (kn.equals("Select")) {
                    AlertDialog.Builder alta = new AlertDialog.Builder(ActivityInputKunjungan.this);
                    alta.setTitle("Error");
                    alta.setIcon(R.drawable.warning);
                    alta.setMessage("Karakter Nasabah Harus Di Pilih");
                    alta.setCancelable(false);
                    alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alta.create();
                    alta.show();
                } else if (ap.equals("Select")) {
                    AlertDialog.Builder alta = new AlertDialog.Builder(ActivityInputKunjungan.this);
                    alta.setTitle("Error");
                    alta.setIcon(R.drawable.warning);
                    alta.setMessage("Action Plan Harus Di Pilih");
                    alta.setCancelable(false);
                    alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alta.create();
                    alta.show();
                } else {
                    pdLoading = new ProgressDialog(ActivityInputKunjungan.this);
                    pdLoading.setMessage("\tLoading...");
                    pdLoading.setCancelable(false);
                    pdLoading.show();

                    Location location = mapLocation.getLocation(ActivityInputKunjungan.this);
                    if (location == null) {

                        pdLoading.dismiss();
                        final AlertDialog.Builder alta = new AlertDialog.Builder(ActivityInputKunjungan.this);
                        alta.setTitle("Pesan");
                        alta.setIcon(R.drawable.warning);
                        alta.setMessage("Location Tidak Di Temukan Mohon Periksa Settingan Location Di Device Anda");
                        alta.setCancelable(false);
                        alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        alta.create();
                        alta.show();
                    } else {
                        lat = String.valueOf(location.getLatitude());
                        lng = String.valueOf(location.getLongitude());


                        conn = new Conectiondetector(ActivityInputKunjungan.this);
                        if (conn.isConected()) {
                            if (printing.equals("1") && !Bluetooth.isPrinterConnected(getApplicationContext(), ActivityInputKunjungan.this)) {
                                pdLoading.dismiss();
                                Bluetooth.connectPrinter(getApplicationContext(), ActivityInputKunjungan.this);
                            } else {
                                if (tanggalap.getText().toString().equals("")) {
                                    pdLoading.dismiss();

                                    AlertDialog.Builder alta = new AlertDialog.Builder(ActivityInputKunjungan.this);
                                    alta.setTitle("Pesan");
                                    alta.setIcon(R.drawable.warning);
                                    alta.setMessage("Date Action Plan Belum Di Isi !");
                                    alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    alta.create();
                                    alta.show();
                                    db.close();
                                } else if (totalbayar.getText().toString().equals("")) {
                                    pdLoading.dismiss();

                                    AlertDialog.Builder alta = new AlertDialog.Builder(ActivityInputKunjungan.this);
                                    alta.setTitle("Pesan");
                                    alta.setIcon(R.drawable.warning);
                                    alta.setMessage("Total Bayar Belum Di Isi, Apakah Tetap Ingin Mengirim ?");
                                    alta.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            pdLoading.show();
                                            cekimage(codeimage1, lat, lng);

                                        }
                                    });
                                    alta.create();
                                    alta.show();
                                    db.close();
                                } else {
                                    if (edtemail2.getText().toString().isEmpty()) {
                                        cekimage(codeimage1, lat, lng);
                                    } else {
                                        if (edtemail2.getText().toString().trim().matches(emailPattern)) {
                                            pdLoading.show();
                                            cekimage(codeimage1, lat, lng);
                                        } else {
                                            pdLoading.dismiss();
                                            AlertDialog.Builder alta = new AlertDialog.Builder(ActivityInputKunjungan.this);
                                            alta.setTitle("Error");
                                            alta.setIcon(R.drawable.warning);
                                            alta.setMessage("Perubahan email tidak valid dengan format email yang berlaku");
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
                                }

                            }
                        } else {

                            DBAdapter2 db22 = new DBAdapter2(ActivityInputKunjungan.this);
                            db22.openDB();
                            Cursor img3 = db22.getimage3(cif.getText().toString(), codeimage1, "0", "photo_jaminan");
                            if (img3.getCount() > 0) {
                                long result = db22.addkunjungan(id_user, cif.getText().toString(), codeimage1, tujuan.getText().toString(), hasilkunjungan.getText().toString(), keterangan1.getText().toString(), namanasabah.getText().toString(), actionplan.getText().toString(), bertemudengan.getText().toString(), keterangan2.getText().toString(), lokasibertemu.getText().toString(), keterangan3.getText().toString(), karakternasabah.getText().toString(), negatifissue, actionplan.getText().toString(), tanggalap.getText().toString(), resumenasabah.getText().toString(), totaltunggakan.getText().toString(), totalbayar.getText().toString(), perkiraan.getText().toString(), datevisit, lat, lng, "0", edtemail2.getText().toString(), editalamatrm.getText().toString(), alamatusaha1.getText().toString(), pihakbank.getText().toString(), keterangan4.getText().toString(), notif.getText().toString());
                                db22.deleteaccountpercif(cif.getText().toString());
                                db22.deletetunggakan(cif.getText().toString());
                                if (result > 0) {
                                    pdLoading.dismiss();

                                    AlertDialog.Builder alta = new AlertDialog.Builder(ActivityInputKunjungan.this);
                                    alta.setTitle("Pesan");
                                    alta.setIcon(R.drawable.warning);
                                    alta.setMessage("Tidak ada Connection Internet, Data Gagal Terkirim, Dan Di Simpan Di Local Storage !!!");
                                    alta.setCancelable(false);
                                    alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            finish();
                                        }
                                    });
                                    alta.create();
                                    alta.show();
                                }

                                db22.close();
                            } else {
                                pdLoading.dismiss();
                                AlertDialog.Builder alta = new AlertDialog.Builder(ActivityInputKunjungan.this);
                                alta.setTitle("Pesan");
                                alta.setIcon(R.drawable.warning);
                                alta.setMessage("Data Gagal Terkirim, Silahkan Ambil Foto Photo Jaminan !");
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

                    }
                }
            }
        });

        tanggalap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tglap();
            }
        });

        btn_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityInputKunjungan.this, ActivityPhotoInputKunjungan.class);
                intent.putExtra("cif", cif.getText().toString());
                intent.putExtra("loanid", loan);
                intent.putExtra("type", "photo_jaminan");
                intent.putExtra("code", codeimage1);
                startActivity(intent);
            }
        });

        btn_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityInputKunjungan.this, ActivityPhotoInputKunjungan.class);
                intent.putExtra("cif", cif.getText().toString());
                intent.putExtra("loanid", loan);
                intent.putExtra("type", "photo_doc");
                intent.putExtra("code", codeimage1);
                startActivity(intent);
            }
        });

        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);
        spinner4.setOnItemSelectedListener(this);
        spinner5.setOnItemSelectedListener(this);
        spinner6.setOnItemSelectedListener(this);
        spinner7.setOnItemSelectedListener(this);

        pilih1 = (RadioGroup) findViewById(R.id.pilih1);
        pilih2 = (RadioGroup) findViewById(R.id.pilih2);
        pilih1.setOnCheckedChangeListener(this);
        pilih2.setOnCheckedChangeListener(this);
        ya = (RadioButton) findViewById(R.id.ya);
        ya2 = (RadioButton) findViewById(R.id.ya2);
        tidak = (RadioButton) findViewById(R.id.tidak);
        tidak2 = (RadioButton) findViewById(R.id.tidak2);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        rc = (RecyclerView) findViewById(R.id.rc2);
        //SET ITS PROPS
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setItemAnimator(new DefaultItemAnimator());
        adaptertunggakan = new Adapterrowtabletunggakan();
        rc.setAdapter(adaptertunggakan);
        retrievetunggakan();

        listactionplan();
        listtujuan();
        listbertemu();
        listhasilkunjungan();
        listlokasibertemu();
        listkarakternasabah();
        listNotif();
        viewcontact();


    }


    private void print() {
        try {
            byte[] normalText = {27, 33, 0};
            byte[] normalLeftText = {27, 97, 0};
            byte[] normalCenterText = {27, 91, 1};

            String BILL = "";
            String BILL2 = "";
            String BILL3 = "";
            String BILL4 = "";
            String BILL5 = "";
            String BILL6 = "";
            String BILL7 = "";
            String BILL8 = "";
            String BILL9 = "";
            String BILL10 = "";
            String BILL11 = "";
            String BILL12 = "";
            String BILL13 = "";
            String BILL14 = "";
            String BILL15 = "";
            String BILL16 = "";
            String datetime = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss", Locale.getDefault()).format(new Date());



            BILL = BILL + String.format("%1$-15s %2$1s %3$1s ", "Nama", ":", namanasabah.getText().toString());
            BILL2 = BILL2 + String.format("%1$-15s %2$1s %3$1s ", "Cif", ":", cif.getText().toString());
            BILL6 = BILL6 + String.format("%1$-15s %2$1s %3$1s ", "No Refrence", ":", codeimage1);
            BILL9 = BILL9 + String.format("%1$-15s %2$1s %3$1s ", "Total", ":", totaltunggakan.getText().toString());
            BILL13 = BILL13 + String.format("%1$-5s %2$5s %3$1s ", "", "Tagihan", "");
            BILL14 = BILL14 + String.format("%1$-31s %2$1s %3$1s ", "Nominal Yang Di\nSetor", ":", totalbayar.getText().toString());

            BILL15 = BILL15 + String.format("%1$-2s %2$5s ", "Date  :", datetime);
            BILL16 = BILL16 + String.format("%1$-2s %2$5s ", "Agent :", nama_user);
            BluetoothService mService = Bluetooth.getServiceInstance();


            mService.write(PrinterCommand.FEED_LINE);
            mService.write(PrinterCommand.FEED_LINE);
            mService.write(normalText);
            mService.write(PrinterCommand.ESC_ALIGN_CENTER);
            mService.write("KSP-SMS\nCOLLECTION SYSTEM".getBytes());
            mService.write(PrinterCommand.FEED_LINE);
            mService.write(PrinterCommand.ESC_ALIGN_CENTER);
            mService.write("================================".getBytes());
            mService.write(PrinterCommand.FEED_LINE);
            mService.write(normalText);
            mService.write(PrinterCommand.ESC_ALIGN_CENTER);
            mService.write("*** BUKTI PEMBAYARAN ***".getBytes());
            mService.write(PrinterCommand.FEED_LINE);
            mService.write(normalText);
            mService.write(PrinterCommand.ESC_ALIGN_LEFT);
            mService.write(BILL15.getBytes());
            mService.write(PrinterCommand.FEED_LINE);
            mService.write(normalText);
            mService.write(PrinterCommand.ESC_ALIGN_LEFT);
            mService.write(BILL16.getBytes());
            mService.write(PrinterCommand.FEED_LINE);
            mService.write(PrinterCommand.ESC_ALIGN_CENTER);
            mService.write("-------------------------------".getBytes());
            mService.write(PrinterCommand.FEED_LINE);
            mService.write(normalText);
            mService.write(PrinterCommand.ESC_ALIGN_LEFT);
            mService.write(BILL.getBytes());
            mService.write(PrinterCommand.FEED_LINE);
            mService.write(normalText);
            mService.write(PrinterCommand.ESC_ALIGN_LEFT);
            mService.write(BILL2.getBytes());
            mService.write(PrinterCommand.FEED_LINE);
            mService.write(normalText);
            mService.write(PrinterCommand.ESC_ALIGN_LEFT);
            mService.write(BILL6.getBytes());
            mService.write(PrinterCommand.FEED_LINE);
            mService.write(PrinterCommand.ESC_ALIGN_CENTER);
            mService.write("-------------------------------".getBytes());
            mService.write(PrinterCommand.FEED_LINE);
            mService.write(normalText);
            mService.write(PrinterCommand.ESC_ALIGN_LEFT);
            mService.write(BILL13.getBytes());
            mService.write(PrinterCommand.FEED_LINE);
            mService.write(PrinterCommand.ESC_ALIGN_CENTER);
            mService.write("-------------------------------".getBytes());
            mService.write(PrinterCommand.FEED_LINE);
            mService.write(normalText);
            mService.write(PrinterCommand.ESC_ALIGN_LEFT);
            mService.write(BILL9.getBytes());
            mService.write(PrinterCommand.FEED_LINE);
            mService.write(PrinterCommand.ESC_ALIGN_CENTER);
            mService.write("-------------------------------".getBytes());
            mService.write(PrinterCommand.FEED_LINE);
            mService.write(normalText);
            mService.write(PrinterCommand.ESC_ALIGN_LEFT);
            mService.write(BILL14.getBytes());
            mService.write(PrinterCommand.FEED_LINE);
            mService.write(PrinterCommand.ESC_ALIGN_CENTER);
            mService.write("-------------------------------".getBytes());
            mService.write(PrinterCommand.FEED_LINE);
            mService.write(normalText);
            mService.write(PrinterCommand.ESC_ALIGN_CENTER);
            mService.write("**** Terimakasih ****".getBytes());
            mService.write(PrinterCommand.FEED_LINE);
            mService.write(PrinterCommand.FEED_LINE);
            mService.write(PrinterCommand.FEED_LINE);
            mService.write(PrinterCommand.FEED_LINE);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void cekimage(final String codeimage1, final String lat, final String lng) {
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        Cursor img3 = db.getimage3(cif.getText().toString(), codeimage1, "0", "photo_jaminan");
        if (img3.getCount() > 0) {
            sendimage(codeimage1, lat, lng);
        } else {
            pdLoading.dismiss();
            AlertDialog.Builder alta = new AlertDialog.Builder(ActivityInputKunjungan.this);
            alta.setTitle("Pesan");
            alta.setIcon(R.drawable.warning);
            alta.setMessage("Data Gagal Terkirim, Silahkan Ambil Foto Photo Jaminan !");
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

    private void sendimage(final String codeimage1, final String lat, final String lng) {
        conn = new Conectiondetector(this);
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        String cifimage = "";
        String keterangan = "";
        String type = "";
        String file = "";
        String loanid = "";
        String tglvisit = "";
        String code = "";
        Cursor img3 = db.getimage3(cif.getText().toString(), codeimage1, "0", "photo_jaminan");
        if (img3.getCount() > 0) {
            Cursor img4 = db.getimage4(cif.getText().toString(), codeimage1, "0");
            if (img4.moveToFirst()) {
                if (img4.getCount() > 0) {
                    totalImage = img4.getCount();
                    totalUploadedImage = 0;
                    do {
                        final String id = img4.getString(0);
                        cifimage = img4.getString(1);
                        keterangan = img4.getString(5);
                        type = img4.getString(7);
                        file = img4.getString(4);
                        tglvisit = img4.getString(6);
                        loanid = img4.getString(2);
                        code = img4.getString(3);

                        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), getimage(file));
                        MultipartBody.Part image = MultipartBody.Part.createFormData("image", getimage(file).getName(), requestFile);
                        RequestBody f_cif = RequestBody.create(MediaType.parse("text/plan"), cifimage);
                        RequestBody f_loanid = RequestBody.create(MediaType.parse("text/plan"), loanid);
                        RequestBody f_type = RequestBody.create(MediaType.parse("text/plan"), type);
                        RequestBody f_code = RequestBody.create(MediaType.parse("text/plan"), code);
                        RequestBody f_tgl = RequestBody.create(MediaType.parse("text/plan"), tglvisit);
                        RequestBody f_keterangan = RequestBody.create(MediaType.parse("text/plan"), keterangan);
                        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
                        Call<ResponsModel> getdata = api.sendimageik(image, f_cif, f_loanid, f_type, f_code, f_tgl, f_keterangan);
//                            final String finalFile = file;
                        final String finalFile1 = file;
                        getdata.enqueue(new Callback<ResponsModel>() {
                            @Override
                            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                                String kode = response.body().getKode();
                                if (kode.equals("1")) {
                                    DBAdapter2 db2 = new DBAdapter2(ActivityInputKunjungan.this);
                                    //OPEN
                                    db2.openDB();
                                    Long result = db2.updatestsimage(id, "1");
                                    if (result > 0) {
                                        deleteImage(finalFile1);
//                                            db2.deleteimage("1");
                                    }
                                    db2.close();

                                    totalUploadedImage++;
                                    if (totalImage == totalUploadedImage) {
                                        sencontact(codeimage1, lat, lng);
//                                    sendata(codeimage1, lat, lng);
                                    }
                                } else {
                                    pdLoading.dismiss();
                                    AlertDialog.Builder alta = new AlertDialog.Builder(ActivityInputKunjungan.this);
                                    alta.setTitle("Pesan");
                                    alta.setIcon(R.drawable.warning);
                                    alta.setMessage("Data Gagal Terkirim !");
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
//                                pdLoading.dismiss();
                                    //sencontact(codeimage1, lat, lng);
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponsModel> call, Throwable t) {
//                                totalUploadedImage++;
//                                if (totalImage == totalUploadedImage) {
                                DBAdapter2 db3 = new DBAdapter2(ActivityInputKunjungan.this);
                                db3.openDB();
                                long result = db3.addkunjungan(id_user, cif.getText().toString(), codeimage1, tujuan.getText().toString(), hasilkunjungan.getText().toString(), keterangan1.getText().toString(), namanasabah.getText().toString(), actionplan.getText().toString(), bertemudengan.getText().toString(), keterangan2.getText().toString(), lokasibertemu.getText().toString(), keterangan3.getText().toString(), karakternasabah.getText().toString(), negatifissue, actionplan.getText().toString(), tanggalap.getText().toString(), resumenasabah.getText().toString(), totaltunggakan.getText().toString(), totalbayar.getText().toString(), perkiraan.getText().toString(), datevisit, lat, lng, "0", edtemail2.getText().toString(), editalamatrm.getText().toString(), alamatusaha1.getText().toString(), pihakbank.getText().toString(), keterangan4.getText().toString(), notif.getText().toString());

                                if (result > 0) {
                                    db3.deleteaccountpercif(cif.getText().toString());
                                    db3.deletetunggakan(cif.getText().toString());

                                }
//                                    db3.close();
                                pdLoading.dismiss();
                                AlertDialog.Builder alta = new AlertDialog.Builder(ActivityInputKunjungan.this);
                                alta.setTitle("Pesan");
                                alta.setIcon(R.drawable.warning);
                                alta.setMessage("Data Gagal Terkirim ," + t.getMessage() + " dan tersimpan di Local Storage !");
                                alta.setCancelable(false);
                                alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        pdLoading.dismiss();
                                        dialog.dismiss();
                                        finish();
                                    }
                                });

                                alta.create();
                                alta.show();
//                                totalUploadedImage++;
//                                if (totalImage == totalUploadedImage) {
//                                    sencontact(codeimage1, lat, lng);
////                                    sendata(codeimage1, lat, lng);
//                                }
                            }

//                            }
                        });
                    } while (img4.moveToNext());


                } else {
                    pdLoading.dismiss();
                    AlertDialog.Builder alta = new AlertDialog.Builder(ActivityInputKunjungan.this);
                    alta.setTitle("Pesan");
                    alta.setIcon(R.drawable.warning);
                    alta.setMessage("Belum Mengambil Foto, Silahkan ambil Foto terlebih dahulu !");
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
        } else {
            pdLoading.dismiss();
            AlertDialog.Builder alta = new AlertDialog.Builder(ActivityInputKunjungan.this);
            alta.setTitle("Pesan");
            alta.setIcon(R.drawable.warning);
            alta.setMessage("Data Gagal Terkirim, Silahkan Ambil Foto Photo Jaminan !");
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

    public void deleteImage(String filename) {
        File fdelete = new File(Environment.getExternalStoragePublicDirectory("iColls").getAbsolutePath(), "AppsPhoto" + "/" + filename);
        if (fdelete.exists()) {
            if (fdelete.delete()) {
                Log.e("-->", "file Deleted :");
            } else {
                Log.e("-->", "file not Deleted :");
            }
        }
    }

    private void sencontact(final String codeimage1, final String lat, final String lng) {

        DBAdapter2 db = new DBAdapter2(ActivityInputKunjungan.this);
        //OPEN
        db.openDB();
        Cursor cntc = db.getcontactall(cif.getText().toString(), "0");
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
                            String kode = response.body() != null ? response.body().getKode() : "9";
                            if (kode.equals("1")) {
                                DBAdapter2 db1 = new DBAdapter2(ActivityInputKunjungan.this);
                                db1.openDB();
                                db1.updatestscontact(cif, "1");
                                db1.close();

                                totalUploadedContact++;
                                if (totalContact == totalUploadedContact) {
                                    sendata(codeimage1, lat, lng);
                                }

                            } else if (kode.equals("9")) {
//                                Toast.makeText(ActivityInputKunjungan.this, "Terjadi masalah silahkan hubungi admin \n error code : SNDCNCT01", Toast.LENGTH_LONG).show();
                            } else {
                                totalUploadedContact++;
                                if (totalContact == totalUploadedContact) {
                                    sendata(codeimage1, lat, lng);
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponsModelcontactupdate> call, Throwable t) {
                            totalUploadedContact++;
//                            sendata(codeimage1, lat, lng);
//                            pdLoading.dismiss();
                            if (totalContact == totalUploadedContact) {
                                sendata(codeimage1, lat, lng);
                            }
                        }
                    });


                } while (cntc.moveToNext());


            } else {
                sendata(codeimage1, lat, lng);
            }

        } else {
            sendata(codeimage1, lat, lng);
        }
        db.close();
    }

    private void sendata(final String codeimage1, final String lat, final String lng) {
        DBAdapter2 db33 = new DBAdapter2(ActivityInputKunjungan.this);
        db33.openDB();
        long result = db33.addkunjungan(id_user, cif.getText().toString(), codeimage1, tujuan.getText().toString(), hasilkunjungan.getText().toString(), keterangan1.getText().toString(), namanasabah.getText().toString(), actionplan.getText().toString(), bertemudengan.getText().toString(), keterangan2.getText().toString(), lokasibertemu.getText().toString(), keterangan3.getText().toString(), karakternasabah.getText().toString(), negatifissue, actionplan.getText().toString(), tanggalap.getText().toString(), resumenasabah.getText().toString(), totaltunggakan.getText().toString(), totalbayar.getText().toString(), perkiraan.getText().toString(), datevisit, lat, lng, "0", edtemail2.getText().toString(), editalamatrm.getText().toString(), alamatusaha1.getText().toString(), pihakbank.getText().toString(), keterangan4.getText().toString(), notif.getText().toString());
        if (result > 0) {
            final String codeimage = cif.getText().toString() + datevisit;
            ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
            Call<ResponsModelChangePswd> getdata = api.insertkunjungan2(new RequestKunjungan(codeimage1, lat, lng, id_user, tujuan.getText().toString(), namanasabah.getText().toString(), cif.getText().toString(), loan, hasilkunjungan.getText().toString(), keterangan1.getText().toString(), "hiden", bertemudengan.getText().toString(), keterangan2.getText().toString(), lokasibertemu.getText().toString(), keterangan3.getText().toString(), karakternasabah.getText().toString(), keterangan4.getText().toString(), negatifissue, actionplan.getText().toString(), "", tanggalap.getText().toString(), resumenasabah.getText().toString(), totaltunggakan.getText().toString(), perkiraan.getText().toString(), datevisit, totalbayar.getText().toString(), edtemail2.getText().toString(), alamatusaha1.getText().toString(), editalamatrm.getText().toString(), pihakbank.getText().toString(), notif.getText().toString()));
            getdata.enqueue(new Callback<ResponsModelChangePswd>() {
                @Override
                public void onResponse(Call<ResponsModelChangePswd> call, Response<ResponsModelChangePswd> response) {
                    String kode = response.body().getKode();
                    if (kode.equals("1")) {
                        pdLoading.dismiss();
                        DBAdapter2 db3 = new DBAdapter2(ActivityInputKunjungan.this);
                        db3.openDB();

                        Long result = db3.updatelistpendingsenddata(codeimage1);
                        String cif2 = cif.getText().toString();

                        if (result > 0) {
//                        db3.deleteaccount(cif2);
                            db3.deleteaccountpercif(cif2);
                            db3.deletetunggakan(cif2);
                        }
                        db3.close();
                        AlertDialog.Builder alta = new AlertDialog.Builder(ActivityInputKunjungan.this);
                        alta.setTitle("Pesan");
                        alta.setIcon(R.drawable.checklist);
                        alta.setMessage("Data Berhasil Di Kirim Ke Server");
                        alta.setCancelable(false);
                        alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (printing.equals("1")) {
                                    print();
                                }
                                finish();
                                Intent intent = new Intent(ActivityInputKunjungan.this, ActivityDailyPlanVisit.class);
                                startActivity(intent);
                            }
                        });

                        alta.create();
                        alta.show();

                    } else {

                        pdLoading.dismiss();
                        DBAdapter2 db4 = new DBAdapter2(ActivityInputKunjungan.this);
                        db4.openDB();
                        Long result = db4.addkunjungan(id_user, cif.getText().toString(), codeimage1, tujuan.getText().toString(), hasilkunjungan.getText().toString(), keterangan1.getText().toString(), namanasabah.getText().toString(), actionplan.getText().toString(), bertemudengan.getText().toString(), keterangan2.getText().toString(), lokasibertemu.getText().toString(), keterangan3.getText().toString(), karakternasabah.getText().toString(), negatifissue, actionplan.getText().toString(), tanggalap.getText().toString(), resumenasabah.getText().toString(), totaltunggakan.getText().toString(), totalbayar.getText().toString(), perkiraan.getText().toString(), datevisit, lat, lng, "0", edtemail2.getText().toString(), editalamatrm.getText().toString(), alamatusaha1.getText().toString(), pihakbank.getText().toString(), keterangan4.getText().toString(), notif.getText().toString());
                        String cif2 = cif.getText().toString();
                        if (result > 0) {
//                        db4.deleteaccount(cif2);
                            db4.deleteaccountpercif(cif2);
                            db4.deletetunggakan(cif2);
                        }
                        db4.close();
                        String message = response.body().getMessage();
                        AlertDialog.Builder alta = new AlertDialog.Builder(ActivityInputKunjungan.this);
                        alta.setTitle("Pesan");
                        alta.setIcon(R.drawable.warning);
                        alta.setMessage("Data gagal di kirim, dan di simpan di local storage ! \n error code : IPT01 \n message : " + message);
                        alta.setCancelable(false);
                        alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                if (printing.equals("1")) {
                                    print();
                                }
                                finish();
                                Intent intent = new Intent(ActivityInputKunjungan.this, ActivityDailyPlanVisit.class);
                                startActivity(intent);
                            }
                        });

                        alta.create();
                        alta.show();

                    }
                }

                @Override
                public void onFailure(Call<ResponsModelChangePswd> call, Throwable t) {

                    DBAdapter2 db3 = new DBAdapter2(ActivityInputKunjungan.this);
                    db3.openDB();
                    long result = db3.addkunjungan(id_user, cif.getText().toString(), codeimage1, tujuan.getText().toString(), hasilkunjungan.getText().toString(), keterangan1.getText().toString(), namanasabah.getText().toString(), actionplan.getText().toString(), bertemudengan.getText().toString(), keterangan2.getText().toString(), lokasibertemu.getText().toString(), keterangan3.getText().toString(), karakternasabah.getText().toString(), negatifissue, actionplan.getText().toString(), tanggalap.getText().toString(), resumenasabah.getText().toString(), totaltunggakan.getText().toString(), totalbayar.getText().toString(), perkiraan.getText().toString(), datevisit, lat, lng, "0", edtemail2.getText().toString(), editalamatrm.getText().toString(), alamatusaha1.getText().toString(), pihakbank.getText().toString(), keterangan4.getText().toString(), notif.getText().toString());
//                db3.deleteaccount(cif.getText().toString());
                    db3.deleteaccountpercif(cif.getText().toString());
                    db3.deletetunggakan(cif.getText().toString());

                    if (result > 0) {
                        AlertDialog.Builder alta = new AlertDialog.Builder(ActivityInputKunjungan.this);
                        alta.setTitle("Pesan");
                        alta.setIcon(R.drawable.warning);
                        alta.setMessage("Data Gagal Terkirim, &  Di Simpan Di Local Storage, Menunggu Sampai Internet Tersedia !!! \n error code : IPT01 \n message : " + t.getMessage());
                        alta.setCancelable(false);
                        alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                if (printing.equals("1")) {
                                    print();
                                }
                                finish();
                            }
                        });

                        alta.create();
                        alta.show();
                    } else {
                        AlertDialog.Builder alta = new AlertDialog.Builder(ActivityInputKunjungan.this);
                        alta.setTitle("Pesan");
                        alta.setIcon(R.drawable.warning);
                        alta.setMessage("Data gagal terkirim dan gagal di simpan di local storage !!!");
                        alta.setCancelable(false);
                        alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                if (printing.equals("1")) {
                                    print();
                                }
                                finish();
                            }
                        });

                        alta.create();
                        alta.show();
                    }
                    db3.close();

                }
            });
        }
        db33.close();
    }


    private void retrievetunggakan() {

        tunggakan.clear();
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        Cursor c = db.gettunggakan(cif.getText().toString());
        if (c.moveToFirst()) {
            do {
                String id = c.getString(0);
                String idt = c.getString(1);
                String cif = c.getString(2);
                String totaltunggakan = c.getString(3);
                String installment = c.getString(4);
                String duepr = c.getString(5);
                String duein = c.getString(6);
                String duech = c.getString(7);
                String nodaysdo = c.getString(8);

                DataTunggakanLocal data = new DataTunggakanLocal(idt, cif, totaltunggakan, installment, duepr, duein, duech, nodaysdo);
                tunggakanlocal.add(data);
            } while (c.moveToNext());
            adaptertunggakan.setData(tunggakanlocal);

        } else {
            adaptertunggakan.setData(tunggakanlocal);
        }

        db.close();

    }

    private void ceklog() {
        DBAdapter2 db = new DBAdapter2(this);

        //OPEN
        db.openDB();


        //SELECT
        Cursor c = db.getUser();
        if (c.moveToFirst()) {
            statususer = c.getString(3);
            id_user = c.getString(4);
            nama_user = c.getString(0);
        }

    }

    private List<DataAP> list = new ArrayList<>();
    SpinnerAdapter6 adapter6;
    SpinnerAdapter1 adapter1;
    SpinnerAdapter2 adapter2;
    SpinnerAdapter3 adapter3;
    SpinnerAdapter4 adapter4;
    SpinnerAdapter5 adapter5;
    SpinnerAdapter7 adapter7;

    private void listNotif() {
        final DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        final Cursor ap = db.getlistap("18", "1");
        conn = new Conectiondetector(ActivityInputKunjungan.this);
        if (ap.getCount() > 0) {
            if (ap.moveToFirst()) {
                IstSource7.add("Select");
                do {
                    IstSource7.add(ap.getString(0));
//                    Toast.makeText(ActivityInputKunjungan.this, ap.getString(0), Toast.LENGTH_SHORT).show();
                    adapter7 = new SpinnerAdapter7(IstSource7, ActivityInputKunjungan.this);
                }
                while (ap.moveToNext());
                spinner7.setAdapter(adapter6);
            }
        } else {
            IstSource7.add("Select");
            adapter7 = new SpinnerAdapter7(IstSource6, ActivityInputKunjungan.this);
        }
        spinner7.setAdapter(adapter7);
        db.close();
    }

    private void listactionplan() {
        final DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        final Cursor ap = db.getlistap("3", "1");
        conn = new Conectiondetector(ActivityInputKunjungan.this);
        if (ap.getCount() > 0) {
            if (ap.moveToFirst()) {
                IstSource6.add("Select");
                do {
                    IstSource6.add(ap.getString(0));
//                    Toast.makeText(ActivityInputKunjungan.this, ap.getString(0), Toast.LENGTH_SHORT).show();
                    adapter6 = new SpinnerAdapter6(IstSource6, ActivityInputKunjungan.this);
                }
                while (ap.moveToNext());
                spinner6.setAdapter(adapter6);
            }
        } else {
            IstSource6.add("Select");
            adapter6 = new SpinnerAdapter6(IstSource6, ActivityInputKunjungan.this);
        }
        spinner6.setAdapter(adapter6);
        db.close();
    }

    private void listtujuan() {
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        Cursor ap = db.getlistap("13", "1");

        conn = new Conectiondetector(ActivityInputKunjungan.this);

        if (ap.getCount() > 0) {
            if (ap.moveToFirst()) {
                IstSource1.add("Select");
                do {
                    IstSource1.add(ap.getString(0));
                    //Toast.makeText(ActivityInputKunjungan.this, ap.getString(0), Toast.LENGTH_SHORT).show();
                    adapter1 = new SpinnerAdapter1(IstSource1, ActivityInputKunjungan.this);
                }
                while (ap.moveToNext());
            }
        } else {
            IstSource1.add("Select");
            adapter1 = new SpinnerAdapter1(IstSource1, ActivityInputKunjungan.this);
        }
        spinner1.setAdapter(adapter1);
        db.close();


    }

    private void listhasilkunjungan() {
        final DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        final Cursor ap = db.getlistap("14", "1");
        conn = new Conectiondetector(ActivityInputKunjungan.this);

        if (ap.getCount() > 0) {
            if (ap.moveToFirst()) {
                IstSource2.add("Select");
                do {
                    IstSource2.add(ap.getString(0));
                    //Toast.makeText(ActivityInputKunjungan.this, ap.getString(0), Toast.LENGTH_SHORT).show();
                    adapter2 = new SpinnerAdapter2(IstSource2, ActivityInputKunjungan.this);
                }
                while (ap.moveToNext());

            }
        } else {
            IstSource2.add("Select");
            adapter2 = new SpinnerAdapter2(IstSource2, ActivityInputKunjungan.this);
        }
        spinner2.setAdapter(adapter2);
        db.close();
    }

    private void listbertemu() {
        final DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        final Cursor bn = db.getlistap("15", "1");
        conn = new Conectiondetector(ActivityInputKunjungan.this);

        if (bn.getCount() > 0) {
            if (bn.moveToFirst()) {
                IstSource3.add("Select");
                do {
                    IstSource3.add(bn.getString(0));
                    //Toast.makeText(ActivityInputKunjungan.this, ap.getString(0), Toast.LENGTH_SHORT).show();
                    adapter3 = new SpinnerAdapter3(IstSource3, ActivityInputKunjungan.this);
                }
                while (bn.moveToNext());

            }
        } else {
            IstSource3.add("Select");
            adapter3 = new SpinnerAdapter3(IstSource3, ActivityInputKunjungan.this);
        }
        spinner3.setAdapter(adapter3);
        db.close();
    }

    private void listlokasibertemu() {
        final DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        final Cursor bn = db.getlistap("17", "1");
        conn = new Conectiondetector(ActivityInputKunjungan.this);

        if (bn.getCount() > 0) {
            if (bn.moveToFirst()) {
                IstSource4.add("Select");
                do {
                    IstSource4.add(bn.getString(0));
                    //Toast.makeText(ActivityInputKunjungan.this, ap.getString(0), Toast.LENGTH_SHORT).show();
                    adapter4 = new SpinnerAdapter4(IstSource4, ActivityInputKunjungan.this);
                }
                while (bn.moveToNext());

            }
        } else {
            IstSource4.add("Select");
            adapter4 = new SpinnerAdapter4(IstSource4, ActivityInputKunjungan.this);
        }
        spinner4.setAdapter(adapter4);
        db.close();
    }

    private void listkarakternasabah() {
        final DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        final Cursor bn = db.getlistap("16", "1");
        conn = new Conectiondetector(ActivityInputKunjungan.this);
        /*if (conn.isConected()) {
            ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
            Call<ResponsModelAP> getdata = api.viewAP(new RequestActionplan("KN"));
            getdata.enqueue(new Callback<ResponsModelAP>() {
                @Override
                public void onResponse(Call<ResponsModelAP> call, Response<ResponsModelAP> response) {
                    String kode = response.body().getKode();
                    //Toast.makeText(ActivityInputKunjungan.this, "data ada"+kode, Toast.LENGTH_SHORT).show();
                    if (kode.equals("1")) {
                        //Toast.makeText(ActivityInputKunjungan.this, "data ada", Toast.LENGTH_SHORT).show();
                        list = response.body().getResult();
                        IstSource5.add("Select");
                        for (int i = 0; i < list.size(); i++) {
                            IstSource5.add(list.get(i).getDesc());
                            adapter5 = new SpinnerAdapter5(IstSource5, ActivityInputKunjungan.this);
                        }
                        spinner5.setAdapter(adapter5);
                    } else {
                        if (bn.moveToFirst()) {
                            IstSource5.add("Select");
                            do {
                                IstSource5.add(bn.getString(0));
                                //Toast.makeText(ActivityInputKunjungan.this, ap.getString(0), Toast.LENGTH_SHORT).show();
                                adapter5 = new SpinnerAdapter5(IstSource5, ActivityInputKunjungan.this);
                            }
                            while (bn.moveToNext());
                            spinner5.setAdapter(adapter5);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponsModelAP> call, Throwable t) {
                    if (bn.moveToFirst()) {
                        IstSource5.add("Select");
                        do {
                            IstSource5.add(bn.getString(0));
                            //Toast.makeText(ActivityInputKunjungan.this, ap.getString(0), Toast.LENGTH_SHORT).show();
                            adapter5 = new SpinnerAdapter5(IstSource5, ActivityInputKunjungan.this);
                        }
                        while (bn.moveToNext());
                        spinner5.setAdapter(adapter5);
                    }
                }
            });
        } else {*/
        if (bn.getCount() > 0) {
            if (bn.moveToFirst()) {
                IstSource5.add("Select");
                do {
                    IstSource5.add(bn.getString(0));
                    //Toast.makeText(ActivityInputKunjungan.this, ap.getString(0), Toast.LENGTH_SHORT).show();
                    adapter5 = new SpinnerAdapter5(IstSource5, ActivityInputKunjungan.this);
                }
                while (bn.moveToNext());

            }
        } else {
            adapter5 = new SpinnerAdapter5(IstSource5, ActivityInputKunjungan.this);
            IstSource5.add("Select");
        }
        spinner5.setAdapter(adapter5);
        db.close();
    }

    private void showdialog() {
        final Dialog d = new Dialog(this);

        //NO TITLE
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //layout of dialog
        d.setContentView(R.layout.activity_contact);

        final EditText Contact1 = (EditText) d.findViewById(R.id.contact1);
        Contact1.setText(contact1);
        Contact1.setEnabled(false);
        final ImageView imgedit1 = (ImageView) d.findViewById(R.id.edit1);
        final ImageView call1 = (ImageView) d.findViewById(R.id.call1);
        call1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + contact1));
                startActivity(callIntent);
            }
        });

        final ImageView close1 = (ImageView) d.findViewById(R.id.close1);
        imgedit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact1.setEnabled(true);
                imgedit1.setVisibility(View.GONE);
                close1.setVisibility(View.VISIBLE);
            }
        });
        close1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact1.setEnabled(false);
                imgedit1.setVisibility(View.VISIBLE);
                close1.setVisibility(View.GONE);
            }
        });

        final EditText Contact2 = (EditText) d.findViewById(R.id.contact2);
        Contact2.setText(contact2);
        Contact2.setEnabled(false);
        final ImageView imgedit2 = (ImageView) d.findViewById(R.id.edit2);
        final ImageView call2 = (ImageView) d.findViewById(R.id.call2);
        call2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + contact2));
                startActivity(callIntent);
            }
        });
        final ImageView close2 = (ImageView) d.findViewById(R.id.close2);
        imgedit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact2.setEnabled(true);
                imgedit2.setVisibility(View.GONE);
                close2.setVisibility(View.VISIBLE);
            }
        });
        close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact2.setEnabled(false);
                imgedit2.setVisibility(View.VISIBLE);
                close2.setVisibility(View.GONE);
            }
        });
        final EditText Contact3 = (EditText) d.findViewById(R.id.contact3);
        Contact3.setText(contact3);
        Contact3.setEnabled(false);
        final ImageView imgedit3 = (ImageView) d.findViewById(R.id.edit3);
        final ImageView call3 = (ImageView) d.findViewById(R.id.call3);
        call3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + contact3));
                startActivity(callIntent);
            }
        });
        final ImageView close3 = (ImageView) d.findViewById(R.id.close3);
        imgedit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact3.setEnabled(true);
                imgedit3.setVisibility(View.GONE);
                close3.setVisibility(View.VISIBLE);
            }
        });
        close3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact3.setEnabled(false);
                imgedit3.setVisibility(View.VISIBLE);
                close3.setVisibility(View.GONE);
            }
        });
        final EditText Contact4 = (EditText) d.findViewById(R.id.contact4);
        Contact4.setText(contact4);
        Contact4.setEnabled(false);
        final ImageView imgedit4 = (ImageView) d.findViewById(R.id.edit4);
        final ImageView call4 = (ImageView) d.findViewById(R.id.call4);
        call4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + contact4));
                startActivity(callIntent);
            }
        });
        final ImageView close4 = (ImageView) d.findViewById(R.id.close4);
        imgedit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact4.setEnabled(true);
                imgedit4.setVisibility(View.GONE);
                close4.setVisibility(View.VISIBLE);
            }
        });
        close4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact4.setEnabled(false);
                imgedit4.setVisibility(View.VISIBLE);
                close4.setVisibility(View.GONE);
            }
        });
        final EditText Contact5 = (EditText) d.findViewById(R.id.contact5);
        Contact5.setText(contact5);
        Contact5.setEnabled(false);
        final ImageView imgedit5 = (ImageView) d.findViewById(R.id.edit5);
        final ImageView call5 = (ImageView) d.findViewById(R.id.call5);
        call5.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + contact5));
                startActivity(callIntent);
            }
        });
        final ImageView close5 = (ImageView) d.findViewById(R.id.close5);
        imgedit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact5.setEnabled(true);
                imgedit5.setVisibility(View.GONE);
                close5.setVisibility(View.VISIBLE);
            }
        });
        close5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact5.setEnabled(false);
                imgedit5.setVisibility(View.VISIBLE);
                close5.setVisibility(View.GONE);
            }
        });
        final EditText Contact6 = (EditText) d.findViewById(R.id.contact6);
        Contact6.setText(contact6);
        Contact6.setEnabled(false);
        final ImageView imgedit6 = (ImageView) d.findViewById(R.id.edit6);
        final ImageView call6 = (ImageView) d.findViewById(R.id.call6);
        call6.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + contact6));
                startActivity(callIntent);
            }
        });
        final ImageView close6 = (ImageView) d.findViewById(R.id.close6);
        imgedit6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact6.setEnabled(true);
                imgedit6.setVisibility(View.GONE);
                close6.setVisibility(View.VISIBLE);
            }
        });
        close6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact6.setEnabled(false);
                imgedit6.setVisibility(View.VISIBLE);
                close6.setVisibility(View.GONE);
            }
        });
        final EditText Contact7 = (EditText) d.findViewById(R.id.contact7);
        Contact7.setText(contact7);
        Contact7.setEnabled(false);
        final ImageView imgedit7 = (ImageView) d.findViewById(R.id.edit7);
        final ImageView call7 = (ImageView) d.findViewById(R.id.call7);
        call7.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + contact7));
                startActivity(callIntent);
            }
        });
        final ImageView close7 = (ImageView) d.findViewById(R.id.close7);
        imgedit7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact7.setEnabled(true);
                imgedit7.setVisibility(View.GONE);
                close7.setVisibility(View.VISIBLE);
            }
        });
        close7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact7.setEnabled(false);
                imgedit7.setVisibility(View.VISIBLE);
                close7.setVisibility(View.GONE);
            }
        });
        Button simpan = (Button) d.findViewById(R.id.simpan);
        Button close = (Button) d.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog pdLoading = new ProgressDialog(ActivityInputKunjungan.this);
                pdLoading.setMessage("\tLoading...");
                pdLoading.setCancelable(false);
                pdLoading.show();
                DBAdapter2 db = new DBAdapter2(ActivityInputKunjungan.this);
                //OPEN
                db.openDB();
                Cursor a = db.getcountercontact(cif.getText().toString());
                if (a.moveToFirst()) {
                    int contact = a.getInt(0);
                    if (a.getInt(0) > 0) {
                        long result = db.updatecontact(Contact1.getText().toString(), Contact2.getText().toString(), Contact3.getText().toString(), Contact4.getText().toString(), Contact5.getText().toString(), Contact6.getText().toString(), Contact7.getText().toString(), cif.getText().toString());
                        if (result > 0) {
                            pdLoading.dismiss();
                            AlertDialog.Builder alta = new AlertDialog.Builder(ActivityInputKunjungan.this);
                            alta.setTitle("Pesan1");
                            alta.setIcon(R.drawable.checklist);
                            alta.setMessage("Data Berhasil Di Simpan");
                            alta.setCancelable(false);
                            alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    viewcontact();
                                    dialog.dismiss();
                                    d.dismiss();
                                }
                            });

                            alta.create();
                            alta.show();
                        } else {
                            pdLoading.dismiss();
                            AlertDialog.Builder alta = new AlertDialog.Builder(ActivityInputKunjungan.this);
                            alta.setTitle("Pesan2");
                            alta.setIcon(R.drawable.checklist);
                            alta.setMessage("Data Gagal Di Simpan");
                            alta.setCancelable(false);
                            alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    viewcontact();
                                    dialog.dismiss();
                                    d.dismiss();
                                }
                            });

                            alta.create();
                            alta.show();
                        }
                    } else {
                        long result = db.adcontact(cif.getText().toString(), Contact1.getText().toString(), Contact2.getText().toString(), Contact3.getText().toString(), Contact4.getText().toString(), Contact5.getText().toString(), Contact6.getText().toString(), Contact7.getText().toString());
                        if (result > 0) {
                            pdLoading.dismiss();
                            AlertDialog.Builder alta = new AlertDialog.Builder(ActivityInputKunjungan.this);
                            alta.setTitle("Pesan3");
                            alta.setIcon(R.drawable.checklist);
                            alta.setMessage("Data Berhasil Di Simpan");
                            alta.setCancelable(false);
                            alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    viewcontact();
                                    dialog.dismiss();
                                    d.dismiss();
                                }
                            });

                            alta.create();
                            alta.show();
                        } else {
                            pdLoading.dismiss();
                            AlertDialog.Builder alta = new AlertDialog.Builder(ActivityInputKunjungan.this);
                            alta.setTitle("Pesan4");
                            alta.setIcon(R.drawable.checklist);
                            alta.setMessage("Data Gagal Di Simpan");
                            alta.setCancelable(false);
                            alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    viewcontact();
                                    dialog.dismiss();
                                    d.dismiss();
                                }
                            });

                            alta.create();
                            alta.show();
                        }
                    }
                }

                //d.dismiss();
                db.close();
            }
        });
        d.show();

    }

    private void viewcontact() {


        DBAdapter2 db = new DBAdapter2(ActivityInputKunjungan.this);
        //OPEN
        db.openDB();
        Cursor b = db.getcontact(cif.getText().toString());
        if (b.moveToFirst()) {
            do {
                contact1 = b.getString(2);
                contact2 = b.getString(3);
                contact3 = b.getString(4);
                contact4 = b.getString(5);
                contact5 = b.getString(6);
                contact6 = b.getString(7);
                contact7 = b.getString(8);
            } while (b.moveToNext());
        }
        db.close();


    }

    private void tgl() {
        final Dialog c = new Dialog(this);

        //NO TITLE
        c.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //layout of dialog
        c.setContentView(R.layout.calender);
        Objects.requireNonNull(c.getWindow()).setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        CalendarView calender = (CalendarView) c.findViewById(R.id.calender);

        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
                month = month + 1;
                date = year + "-" + (month < 10 ? ("0" + month) : (month)) + "-" + (dayOfMonth < 10 ? ("0" + dayOfMonth) : (dayOfMonth));
                String data1 = year + "-" + (month < 10 ? ("0" + month) : (month));
                String dateparse = new SimpleDateFormat("yyyy-MM", Locale.getDefault()).format(new Date());

//                Toast.makeText(mapLocation, "dateparse: " + dateparse, Toast.LENGTH_SHORT).show();
//                Toast.makeText(mapLocation, "data1: " + data1, Toast.LENGTH_SHORT).show();
                if (data1 == dateparse) {
                    tanggalptp.setText(date);
                } else {
                    Toast.makeText(mapLocation, "Date Action Plan Tidak Boleh Melewati Bulan Berjalan", Toast.LENGTH_SHORT).show();
                }


//                Toast.makeText(Activitycuti.this, tgl1.getText().toString(), Toast.LENGTH_SHORT).show();
                c.dismiss();
            }
        });

        c.show();
    }

    private void tglap() {
        final Dialog c = new Dialog(this);

        //NO TITLE
        c.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //layout of dialog
        c.setContentView(R.layout.calender2);
        Objects.requireNonNull(c.getWindow()).setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        CalendarView calender = (CalendarView) c.findViewById(R.id.calender);

        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
                tanggalap.setText("");
                month = month + 1;
                String date2 = year + "-" + (month < 10 ? ("0" + month) : (month)) + "-" + (dayOfMonth < 10 ? ("0" + dayOfMonth) : (dayOfMonth));
                String data1 = year + "-" + (month < 10 ? ("0" + month) : (month));


//                Toast.makeText(ActivityInputKunjungan.this, "dateparse: " + dateparse, Toast.LENGTH_SHORT).show();
//                Toast.makeText(ActivityInputKunjungan.this, "data1: " + data1, Toast.LENGTH_SHORT).show();
                if (data1.equals(dateparse)) {
                    tanggalap.setText(date2);
                } else {
                    Toast.makeText(ActivityInputKunjungan.this, "Date Action Plan Tidak Boleh Melewati Bulan Berjalan", Toast.LENGTH_SHORT).show();
                }

                c.dismiss();
            }
        });

        c.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_input_kunjungan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            DBAdapter db = new DBAdapter(this);
//            db.openDB();
//            db.Deleteuser();
//            db.close();
//            Intent inten = new Intent(ActivityInputKunjungan.this, SplashScreen.class);
//            startActivity(inten);
//            finish();
//
//            return true;
//        }
        if (id == R.id.action_change) {
            Intent inten = new Intent(ActivityInputKunjungan.this, ChangePassword.class);
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
            /*Intent intent = new Intent(ActivityInputKunjungan.this,ActivityMain.class);
            startActivity(intent);*/
            finish();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(ActivityInputKunjungan.this, ActivityDailyPlanVisit.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_slideshow) {
            Intent inten = new Intent(ActivityInputKunjungan.this, ActivityPTP.class);
            startActivity(inten);
            finish();
        } else if (id == R.id.nav_manage) {
            conn = new Conectiondetector(ActivityInputKunjungan.this);
            if (conn.isConected()) {
                ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
                Call<ResponsModelChangePswd> getdata = api.sendLogout(new ReqBodySubHal1(id_user));
                getdata.enqueue(new Callback<ResponsModelChangePswd>() {
                    @Override
                    public void onResponse(Call<ResponsModelChangePswd> call, Response<ResponsModelChangePswd> response) {
                        String kode = response.body().getKode();
                        if (kode.equals("1")) {
                            DBAdapter2 db = new DBAdapter2(ActivityInputKunjungan.this);
                            db.openDB();
                            db.Deleteuser();
                            db.deleteparameter();
                            db.deletekunjunganall("1");
                            db.deletekpall();
                            db.deleteselfcuredall();
                            db.close();
                            Intent inten = new Intent(ActivityInputKunjungan.this, SplashScreen.class);
                            startActivity(inten);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModelChangePswd> call, Throwable t) {
                        DBAdapter2 db = new DBAdapter2(ActivityInputKunjungan.this);
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
                        Intent inten = new Intent(ActivityInputKunjungan.this, SplashScreen.class);
                        startActivity(inten);
                        finish();
                    }
                });
            } else {
                DBAdapter2 db = new DBAdapter2(ActivityInputKunjungan.this);
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
                Intent inten = new Intent(ActivityInputKunjungan.this, SplashScreen.class);
                startActivity(inten);
                finish();
            }
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public File getimage(String filename) {
//        File file = new File(filename);

        return new File(Environment.getExternalStoragePublicDirectory("Colsys").getAbsolutePath(), "AppsPhoto/" + filename);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkedId == R.id.ya) {
            negatifissue = "ya";
            Toast.makeText(this, negatifissue, Toast.LENGTH_SHORT).show();
        } else if (checkedId == R.id.tidak) {
            negatifissue = "tidak";
            Toast.makeText(this, negatifissue, Toast.LENGTH_SHORT).show();
        }
        if (checkedId == R.id.ya2) {
            printing = "1";
            Toast.makeText(this, printing, Toast.LENGTH_SHORT).show();
        } else if (checkedId == R.id.tidak2) {
            printing = "0";
            Toast.makeText(this, printing, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinner1 = (Spinner) parent;
        spinner2 = (Spinner) parent;
        spinner3 = (Spinner) parent;
        spinner4 = (Spinner) parent;
        spinner5 = (Spinner) parent;
        spinner6 = (Spinner) parent;

        if (spinner1.getId() == R.id.spinner1) {
            tujuan.setText(IstSource1.get(position));
            String tj = tujuan.getText().toString();
            if (tj.equals("Call")) {
                lybtnimage1.setVisibility(View.GONE);
                lybtnimage2.setVisibility(View.GONE);
                lylokasibertemu.setVisibility(View.GONE);
            } else {
                lybtnimage1.setVisibility(View.VISIBLE);
                lybtnimage2.setVisibility(View.VISIBLE);
                lylokasibertemu.setVisibility(View.VISIBLE);
            }
            //Toast.makeText(this, IstSource1.get(position), Toast.LENGTH_SHORT).show();
        }
        if (spinner2.getId() == R.id.spinner2) {
            hasilkunjungan.setText(IstSource2.get(position));
            String kj = hasilkunjungan.getText().toString();

        }
        if (spinner3.getId() == R.id.spinner3) {
            bertemudengan.setText(IstSource3.get(position));
            //Toast.makeText(this, IstSource2.get(position), Toast.LENGTH_SHORT).show();
        }
        if (spinner4.getId() == R.id.spinner4) {
            lokasibertemu.setText(IstSource4.get(position));
            // Toast.makeText(this, IstSource4.get(position), Toast.LENGTH_SHORT).show();
        }
        if (spinner5.getId() == R.id.spinner5) {
            karakternasabah.setText(IstSource5.get(position));
            //Toast.makeText(this, IstSource5.get(position), Toast.LENGTH_SHORT).show();
        }
        if (spinner7.getId() == R.id.spinner7) {
            notif.setText(IstSource7.get(position));
            //Toast.makeText(this, IstSource5.get(position), Toast.LENGTH_SHORT).show();
        }
        if (spinner6.getId() == R.id.spinner6) {
            actionplan.setText(IstSource6.get(position));
            if (actionplan.equals("")) {
                actionplan.setError("Acion Plan Harus Di Isi");
            }
            //Toast.makeText(this, IstSource6.get(position), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void closeimage(View view) {
        ly1.setVisibility(View.GONE);
    }

    public void closeimage2(View view) {
        ly2.setVisibility(View.GONE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT && resultCode == RESULT_OK) {
            Bluetooth.pairPrinter(getApplicationContext(), ActivityInputKunjungan.this);
        } else if (requestCode == REQUEST_CONNECT_DEVICE && resultCode == RESULT_OK) {
            Bluetooth.pairedPrinterAddress(getApplicationContext(), ActivityInputKunjungan.this, data.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS));
        }
    }

}

