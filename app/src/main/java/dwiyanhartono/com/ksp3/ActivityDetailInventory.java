package dwiyanhartono.com.ksp3;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.api.ApiRequestData;
import dwiyanhartono.com.ksp3.api.Retroserver;
import dwiyanhartono.com.ksp3.base.BaseActivity;
import dwiyanhartono.com.ksp3.database.DBAdapter;
import dwiyanhartono.com.ksp3.database.DBAdapter2;
import dwiyanhartono.com.ksp3.model.DataContact;
import dwiyanhartono.com.ksp3.model.DataFasilitas;
import dwiyanhartono.com.ksp3.model.DataJaminan;
import dwiyanhartono.com.ksp3.model.DataModelDetailAccount;
import dwiyanhartono.com.ksp3.model.ReqBodySubHal1;
import dwiyanhartono.com.ksp3.model.Requestcontact;
import dwiyanhartono.com.ksp3.model.Requestupcontact;
import dwiyanhartono.com.ksp3.model.ResponsModelChangePswd;
import dwiyanhartono.com.ksp3.model.ResponsModelContact;
import dwiyanhartono.com.ksp3.model.ResponsModelDetailAccount;
import dwiyanhartono.com.ksp3.model.ResponsModelFasilitas;
import dwiyanhartono.com.ksp3.model.ResponsModelJaminan;
import dwiyanhartono.com.ksp3.model.ResponsModelcontactupdate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ALL")
public class ActivityDetailInventory extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TableLayout tl1;
    RecyclerView rv, rc;
    String id_user, nama, email, ip, port, sisid, str;

    String jenis1, alamat, value, cif;
    private List<DataJaminan> jaminans = new ArrayList<>();

    ActivityMain activityMain;

    Conectiondetector conn;
    private List<DataFasilitas> fasilitas = new ArrayList<>();
    Button btncontact;
    Adapterrowtable1 adapter;
    Adapterrowtable2 adapter2;
    TextView enama, ekelamin, ealmtrm, ealmtush, ecif, eacctno, eaoname, ecabang, epuk;
    private List<DataContact> Contacts = new ArrayList<>();
    String contact1, contact2, contact3, contact4, contact5, contact6, contact7, f_cif;

    private List<DataModelDetailAccount> mItems = new ArrayList<>();

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_inventory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        btncontact = (Button) findViewById(R.id.btncontact);
        btncontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog();
            }
        });
        enama = (TextView) findViewById(R.id.nama);
        ekelamin = (TextView) findViewById(R.id.klamin);
        ealmtrm = (TextView) findViewById(R.id.almtrm);
        ealmtush = (TextView) findViewById(R.id.almtush);
        ecif = (TextView) findViewById(R.id.cif);
        epuk = (TextView) findViewById(R.id.puk);
        eacctno = (TextView) findViewById(R.id.rek);
        eaoname = (TextView) findViewById(R.id.aoname);
        ecabang = (TextView) findViewById(R.id.cabang);

        Intent i = getIntent();
        cif = i.getExtras().getString("cif");

        ceklog();
        rv = (RecyclerView) findViewById(R.id.myRecycler);
        //SET ITS PROPS
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        adapter = new Adapterrowtable1();
        rv.setAdapter(adapter);


        rc = (RecyclerView) findViewById(R.id.rc2);
        //SET ITS PROPS
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setItemAnimator(new DefaultItemAnimator());
        adapter2 = new Adapterrowtable2();
        rc.setAdapter(adapter2);


        retrievefasilitas();
        retrievejaminan();
        retrievedetail();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        viewcontact();


    }

    private void viewcontact() {
        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModelContact> getdata = api.viewcontact(new Requestcontact(cif));
        getdata.enqueue(new Callback<ResponsModelContact>() {
            private Call<ResponsModelContact> call;
            private Response<ResponsModelContact> response;

            @Override
            public void onResponse(Call<ResponsModelContact> call, Response<ResponsModelContact> response) {
                String kode = response.body().getKode();
                if (kode.equals("1")) {
                    Contacts = response.body().getResult();
                    for (int i = 0; i < Contacts.size(); i++) {
                        contact1 = Contacts.get(i).getContact1();
                        contact2 = Contacts.get(i).getContact2();
                        contact3 = Contacts.get(i).getContact3();
                        contact4 = Contacts.get(i).getContact4();
                        contact5 = Contacts.get(i).getContact5();
                        contact6 = Contacts.get(i).getContact6();
                        contact7 = Contacts.get(i).getContact7();
                        //tlpon.setText(contact1);
                        /*Toast.makeText(ActivityInputKunjungan.this, a, Toast.LENGTH_SHORT).show();
                        Log.i("Value of element " + i, Contacts.get(i).getContact1());*/
                    }
                    String ab = String.valueOf(response.body().getResult());
                    //Toast.makeText(Activityinputkunjungan.this, ab, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityDetailInventory.this, "data tidak ada", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponsModelContact> call, Throwable t) {

                Toast.makeText(ActivityDetailInventory.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

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
                final ProgressDialog pdLoading = new ProgressDialog(ActivityDetailInventory.this);
                pdLoading.setMessage("\tLoading...");
                pdLoading.setCancelable(false);
                pdLoading.show();
                ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
                Call<ResponsModelcontactupdate> getdata = api.Updatecontact(new Requestupcontact(cif, Contact1.getText().toString(), Contact2.getText().toString(), Contact3.getText().toString(), Contact4.getText().toString(), Contact5.getText().toString(), Contact6.getText().toString(), Contact7.getText().toString()));
                getdata.enqueue(new Callback<ResponsModelcontactupdate>() {
                    @Override
                    public void onResponse(Call<ResponsModelcontactupdate> call, Response<ResponsModelcontactupdate> response) {
                        String kode = response.body().getKode();
                        if (kode.equals("1")) {
                            pdLoading.dismiss();
                            DBAdapter db = new DBAdapter(ActivityDetailInventory.this);
                            //OPEN
                            db.openDB();
                            Cursor a = db.getcountercontact(ecif.getText().toString());
                            if (a.moveToFirst()) {
                                String contact = String.valueOf(a.getInt(0));
                                if (a.getInt(0) > 0) {
                                    db.adcontact(contact1, contact2, contact3, contact4, contact5, contact6, contact7);
                                } else {
                                    db.updatecontact(contact1, contact2, contact3, contact4, contact5, contact6, contact7, ecif.getText().toString());
                                }
                            }
                            db.close();
//                            Toast.makeText(ActivityDetailInventory.this, "sukses", Toast.LENGTH_SHORT).show();
                            viewcontact();
                            AlertDialog.Builder alta = new AlertDialog.Builder(ActivityDetailInventory.this);
                            alta.setTitle("Pesan");
                            alta.setIcon(R.drawable.checklist);
                            alta.setMessage("Data Berhasil Di Kirim Ke Server");
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
                            d.dismiss();
                        } else {
                            AlertDialog.Builder alta = new AlertDialog.Builder(ActivityDetailInventory.this);
                            alta.setTitle("Pesan");
                            alta.setIcon(R.drawable.warning);
                            alta.setMessage("Data Gagal Di Kirim Ke Server");
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
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponsModelcontactupdate> call, Throwable t) {
                        AlertDialog.Builder alta = new AlertDialog.Builder(ActivityDetailInventory.this);
                        alta.setTitle("Pesan");
                        alta.setIcon(R.drawable.warning);
                        alta.setMessage("Data Gagal Di Kirim Ke Server, Karna :" + t.getMessage());
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
                    }
                });

            }
        });
        d.show();

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
    }

    private void retrievedetail() {
        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModelDetailAccount> getdata = api.sendDetail(new Requestcontact(cif));
        getdata.enqueue(new Callback<ResponsModelDetailAccount>() {
            @Override
            public void onResponse(Call<ResponsModelDetailAccount> call, Response<ResponsModelDetailAccount> response) {
                String kode = response.body().getKode();

                if (kode.equals("1")) {
                    mItems = response.body().getResult();
                    for (int i = 0; i < mItems.size(); i++) {
                        String acctno = mItems.get(i).getAcctno();
                        String agentid = mItems.get(i).getAgentid();
                        String nama = mItems.get(i).getNama();
                        String bucket = mItems.get(i).getBucket();
                        String cif = mItems.get(i).getCif();
                        String os = mItems.get(i).getOs();
                        String totaltunggakan = mItems.get(i).getTotaltunggakan();
                        String alamat = mItems.get(i).getAlamat();
                        String dpd = mItems.get(i).getDpd();
                        String alamatusaha = mItems.get(i).getAlamatusaha();
                        String email = mItems.get(i).getEmail();
                        String loanid = mItems.get(i).getLoanid();
                        String eombucked = mItems.get(i).getBucketeom();

                        enama.setText(nama);
                        ekelamin.setText(mItems.get(i).getGender());
                        ealmtrm.setText(alamat);
                        ealmtush.setText(alamatusaha);
                        ecif.setText(cif);
                        epuk.setText("tidak ada");
                        eacctno.setText(acctno);
                        eaoname.setText(mItems.get(i).getAoname());
                        ecabang.setText(mItems.get(i).getCabang());

                    }
                } else {
                    Toast.makeText(ActivityDetailInventory.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsModelDetailAccount> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(ActivityDetailInventory.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void retrievefasilitas() {
        fasilitas.clear();
        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModelFasilitas> getdata = api.viewfasilitas(new Requestcontact(cif));
        getdata.enqueue(new Callback<ResponsModelFasilitas>() {
            class TAG {
            }

            @Override
            public void onResponse(Call<ResponsModelFasilitas> call, Response<ResponsModelFasilitas> response) {
                String kode = response.body().getKode();
                if (kode.equals("1")) {
                    fasilitas = response.body().getResult();
                    adapter2.setData(fasilitas);
//                    Toast.makeText(ActivityDetailInventory.this, kode, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsModelFasilitas> call, Throwable t) {
                Toast.makeText(ActivityDetailInventory.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void retrievejaminan() {
        jaminans.clear();
        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModelJaminan> getdata = api.viewjaminan(new Requestcontact(cif));
        getdata.enqueue(new Callback<ResponsModelJaminan>() {
            @Override
            public void onResponse(Call<ResponsModelJaminan> call, Response<ResponsModelJaminan> response) {
                String kode = response.body().getKode();
                if (kode.equals("1")) {
                    jaminans = response.body().getResult();
                    int loop = 0;
                    while (loop < jaminans.size()) {
                        jaminans.get(loop).setNumber(loop + 1);
                        loop++;
                    }
                    adapter.setData(jaminans);
                }
            }

            @Override
            public void onFailure(Call<ResponsModelJaminan> call, Throwable t) {
                Toast.makeText(ActivityDetailInventory.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
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
        getMenuInflater().inflate(R.menu.activity_detail_inventory, menu);
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
//            DBAdapter db=new DBAdapter(this);
//            db.openDB();
//            db.Deleteuser();
//            db.close();
//            Intent inten = new Intent(ActivityDetailInventory.this, SplashScreen.class);
//            startActivity(inten);
//            finish();
//            return true;
//        }
        if (id == R.id.action_change) {
            Intent inten = new Intent(ActivityDetailInventory.this, ChangePassword.class);
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
            /*Intent intent = new Intent(ActivityDetailInventory.this,ActivityMain.class);
            startActivity(intent);*/
            finish();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(ActivityDetailInventory.this, ActivityDetailInventory.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(ActivityDetailInventory.this, ActivityPTP.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {
            conn = new Conectiondetector(ActivityDetailInventory.this);
            if (conn.isConected()) {
                ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
                Call<ResponsModelChangePswd> getdata = api.sendLogout(new ReqBodySubHal1(id_user));
                getdata.enqueue(new Callback<ResponsModelChangePswd>() {
                    @Override
                    public void onResponse(Call<ResponsModelChangePswd> call, Response<ResponsModelChangePswd> response) {
                        String kode = response.body().getKode();
                        if (kode.equals("1")) {
                            DBAdapter2 db = new DBAdapter2(ActivityDetailInventory.this);
                            db.openDB();
                            db.Deleteuser();
                            db.deleteparameter();
                            db.deletekunjunganall("1");
                            db.deletekpall();
                            db.deleteselfcuredall();
                            db.close();
                            Intent inten = new Intent(ActivityDetailInventory.this, SplashScreen.class);
                            startActivity(inten);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModelChangePswd> call, Throwable t) {
                        DBAdapter2 db = new DBAdapter2(ActivityDetailInventory.this);
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
                        Intent inten = new Intent(ActivityDetailInventory.this, SplashScreen.class);
                        startActivity(inten);
                        finish();
                    }
                });
            } else {
                DBAdapter2 db = new DBAdapter2(ActivityDetailInventory.this);
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
                Intent inten = new Intent(ActivityDetailInventory.this, SplashScreen.class);
                startActivity(inten);
                finish();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
