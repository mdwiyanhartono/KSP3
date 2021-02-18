package dwiyanhartono.com.ksp3;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.database.DBAdapter2;
import dwiyanhartono.com.ksp3.model.DataTesimage;
import dwiyanhartono.com.ksp3.model.DataTesimage_1;

public class TesImageActivityList extends AppCompatActivity implements AdapterTesimageList.OnItemClickListener {

    Button btnviewimage;
    ImageView imageView;
    RecyclerView rc1;
    String cif, tglvisit, code_image, loanid;
    AdapterTesimageList adapter2;
    private List<DataTesimage> dataimage = new ArrayList<>();
    private List<DataTesimage_1> dataimage1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tes_image);

        Intent i = getIntent();
        cif = i.getExtras().getString("cif");
        tglvisit = i.getExtras().getString("type");
        code_image = i.getExtras().getString("code_image");
        loanid = i.getExtras().getString("loanid");
        rc1 = (RecyclerView) findViewById(R.id.rc1);
        rc1.setLayoutManager(new LinearLayoutManager(this));
        rc1.setItemAnimator(new DefaultItemAnimator());
        adapter2 = new AdapterTesimageList(this);
        rc1.setAdapter(adapter2);

        retrivew(cif, tglvisit, loanid, code_image);


        /*
        imageView = (ImageView) findViewById(R.id.imageview);*/
        btnviewimage = (Button) findViewById(R.id.btnviewimage);
        btnviewimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


//        Toast.makeText(this, cif + loanid + code_image, Toast.LENGTH_SHORT).show();
    }


    public String getFileName(String imageNameByTime) {
        String filename = "";
        File file = new File(Environment.getExternalStoragePublicDirectory("Colsys").getAbsolutePath(), "AppsPhoto");
        if (!file.exists()) {
            file.mkdirs();
        }
        filename = file.getAbsolutePath() + "/" + imageNameByTime;
        Log.d("filename", filename);
        return filename;
    }
    private void retrivew(String cif, String tglvisit, String loanid, String code_image) {

        dataimage1.clear();
//        Toast.makeText(this, cif + "/   " + tglvisit + "/  " + loanid + "/  " + code_image, Toast.LENGTH_LONG).show();
        final ProgressDialog pdLoading = new ProgressDialog(this);
        pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(false);
        pdLoading.show();

        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        Cursor img = db.getimage(cif,code_image,tglvisit);
        if(img.getCount() > 0){
            if(img.moveToFirst()){
                pdLoading.dismiss();
                do {
                    String id = img.getString(0);
                    String keterangan = img.getString(5);
                    String type = img.getString(7);
                    String file = img.getString(4);

                    File image = new File(getFileName(file));
                    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                    Bitmap bitmap1 = null;
                    bitmap1 = BitmapFactory.decodeFile(image.getAbsolutePath(), bmOptions);

                    ByteArrayOutputStream baos = new  ByteArrayOutputStream();
                    bitmap1.compress(Bitmap.CompressFormat.JPEG,100, baos);
                    byte [] b = baos.toByteArray();
                    String temp = Base64.encodeToString(b, Base64.DEFAULT);

                    DataTesimage_1 data1 = new DataTesimage_1(id,keterangan,type,temp);
                    dataimage1.add(data1);

//                    Toast.makeText(this, id + "/" + keterangan + "/" + type + "/" + file +"/"+ data1.getF_type() , Toast.LENGTH_LONG).show();

                } while (img.moveToNext());
            } else {
                pdLoading.dismiss();
                Toast.makeText(this, "Data Tidak Ada", Toast.LENGTH_LONG).show();
            }
           adapter2.setData(dataimage1);
        } else {
            pdLoading.dismiss();
            Toast.makeText(this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
        }




        db.close();
    }

    @Override
    public void onButonupdate(String id, String text) {


        DBAdapter2 db = new DBAdapter2(this);
        db.openDB();
        long result = db.updateimage(id,text);
        if(result > 0){
            AlertDialog.Builder alta = new AlertDialog.Builder(TesImageActivityList.this);
            alta.setTitle("Pesan");
            alta.setIcon(R.drawable.checklist);
            alta.setMessage("Data Berhasil Di Simpan");
            alta.setCancelable(false);
            alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    retrivew(cif, tglvisit, loanid, code_image);
                }
            });
            alta.create();
            alta.show();
        } else {
            AlertDialog.Builder alta = new AlertDialog.Builder(TesImageActivityList.this);
            alta.setTitle("Error");
            alta.setIcon(R.drawable.warning);
            alta.setMessage("Gagal Update");
            alta.setCancelable(false);
            alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    retrivew(cif, tglvisit, loanid, code_image);
                }
            });
            alta.create();
            alta.show();
        }
        db.close();
    }
}
