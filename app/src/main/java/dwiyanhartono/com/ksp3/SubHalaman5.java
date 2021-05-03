package dwiyanhartono.com.ksp3;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.api.ApiRequestData;
import dwiyanhartono.com.ksp3.api.Retroserver;
import dwiyanhartono.com.ksp3.base.BaseActivity;
import dwiyanhartono.com.ksp3.database.DBAdapter2;
import dwiyanhartono.com.ksp3.model.Dataptp;
import dwiyanhartono.com.ksp3.model.Dataptp_1;
import dwiyanhartono.com.ksp3.model.ReqBodyApplyAssignment;
import dwiyanhartono.com.ksp3.model.ResponsModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubHalaman5 extends BaseActivity implements AdapterSub5.OnItemClickListener {

    RecyclerView rc1;

    Conectiondetector conn;
    String statususer,nama,id_user,email;

    ImageView apply;
    AdapterSub5 adapter;
    private List<Dataptp> mItems = new ArrayList<>();
    private List<Dataptp_1> dataptpttoday= new ArrayList<>();
    private List<Dataptp_1> dataplanvisit = new ArrayList<>();
    List<String> selectedDebitur = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_halaman5);

        apply = (ImageView) findViewById(R.id.apply);
        rc1 = (RecyclerView) findViewById(R.id.rc1);
        rc1.setLayoutManager(new LinearLayoutManager(this));
        rc1.setItemAnimator(new DefaultItemAnimator());

        ceklog();
        if(statususer == null){
            Intent i = new Intent(SubHalaman5.this, ActivityLogin.class);
            startActivity(i);
            finish();
        } else {
            adapter = new AdapterSub5(this);
            adapter.setData(dataptpttoday);
            rc1.setAdapter(adapter);
            reteview();
        }
    }

    private void reteview() {
        mItems.clear();
        dataptpttoday.clear();
        selectedDebitur.clear();
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String date2 = new SimpleDateFormat("yyyy-MM", Locale.getDefault()).format(new Date());
        Cursor a = db.getptptoday(date,date2);
        if (a.moveToFirst()) {
//            Toast.makeText(this, "masuk 1", Toast.LENGTH_SHORT).show();
            do {
                String namaanggota = a.getString( 6 );
                String cif = a.getString( 4 );
                String loanid = a.getString( 1 );
                String hasilkunjungan = a.getString( 9 );
                String bertemu = a.getString( 11);
                String lokasibertemu = a.getString( 13 );
                String actionplan = a.getString( 18 );
                String tanggalactionplan = a.getString( 19 );
                String nominal = a.getString( 22 );
                Dataptp_1 data = new Dataptp_1(namaanggota,cif,loanid,hasilkunjungan,bertemu,lokasibertemu,actionplan,tanggalactionplan,nominal);
                dataptpttoday.add(data);
//                Toast.makeText(this, nama1+"/" + cif + "/" + datetime, Toast.LENGTH_SHORT).show();
            } while (a.moveToNext());
            adapter.setData(dataptpttoday);
//            Toast.makeText(this, nama +"/" + cif +"/" + datetime, Toast.LENGTH_SHORT).show();
        }
        db.close();


    }

    private void ceklog() {
        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        Cursor c = db.getUser();
        if (c.moveToFirst()) {
            statususer = c.getString( 3);
            id_user = c.getString(4);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void onStop() {
        super.onStop();
    }

    private Timer timer;
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemClick(String cif, boolean isChecked) {
        if (isChecked) {
            Log.d("Add Data", cif);
//            Toast.makeText(SubHalaman5.this, "Add Data: " + id, Toast.LENGTH_SHORT).show();
            selectedDebitur.add(cif);
        } else {
            Log.d("Remove Data", cif);
//            Toast.makeText(SubHalaman5.this, "Remove Data: " + id, Toast.LENGTH_SHORT).show();
            selectedDebitur.remove(cif);
        }
        String a = "";
        for (String s : selectedDebitur) {
            a += s + "#";
        }
        Log.d("Data Selected", a);
//        Toast.makeText(SubHalaman5.this, "Data Selected: " + a, Toast.LENGTH_SHORT).show();

    }

    public void apply(View view) {
        final ProgressDialog pdLoading = new ProgressDialog(this);
        pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(false);
        pdLoading.show();
        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModel> getdata = api.sendassignment(new ReqBodyApplyAssignment(selectedDebitur,id_user,""));
        getdata.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                String kode2 = response.body().getKode();
                if (kode2.equals("1")) {
                    pdLoading.dismiss();
                    AlertDialog.Builder alta = new AlertDialog.Builder(SubHalaman5.this);
                    alta.setTitle("Pesan");
                    alta.setIcon(R.drawable.checklist);
                    alta.setMessage("Data Berhasil Di Kirim Ke Server , Dan Menunggu Aproved !!!");
                    alta.setCancelable(false);
                    alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alta.create();
                    alta.show();
                    reteview();
                } else {
                    pdLoading.dismiss();

                    AlertDialog.Builder alta = new AlertDialog.Builder(SubHalaman5.this);
                    alta.setTitle("Pesan");
                    alta.setIcon(R.drawable.warning);
                    alta.setMessage("Data gagal Di Kirim Ke Server , Silahakan Pilih Terlebih Dahulu Debitur Yang Akan Di Kunjungi!!!");
                    alta.setCancelable(false);
                    alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alta.create();
                    alta.show();
                    reteview();
                }
            }

            @Override
            public void onFailure(Call<ResponsModel> call, Throwable t) {
                pdLoading.dismiss();
                AlertDialog.Builder alta = new AlertDialog.Builder(SubHalaman5.this);
                alta.setTitle("Pesan");
                alta.setIcon(R.drawable.warning);
                alta.setMessage("Data gagal Di Kirim Ke Server ," + t.getMessage() + "!!!");
                alta.setCancelable(false);
                alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alta.create();
                alta.show();
                Toast.makeText(SubHalaman5.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("error", t.getMessage());
            }
        });

    }
}
