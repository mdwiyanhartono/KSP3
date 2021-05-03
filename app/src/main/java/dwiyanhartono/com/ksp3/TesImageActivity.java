package dwiyanhartono.com.ksp3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.api.ApiRequestData;
import dwiyanhartono.com.ksp3.api.Retroserver;
import dwiyanhartono.com.ksp3.base.BaseActivity;
import dwiyanhartono.com.ksp3.model.DataTesimage;
import dwiyanhartono.com.ksp3.model.Requestimage;
import dwiyanhartono.com.ksp3.model.ResponsModelTesimage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TesImageActivity extends BaseActivity {

    Button btnviewimage;
    ImageView imageView;
    RecyclerView rc1;
    AdapterTesimage adapter2;
    ProgressDialog pdLoading;
    private List<DataTesimage> dataimage = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tes_image);

        Intent i = getIntent();
        String cif = i.getExtras().getString("cif");
        String tglvisit = i.getExtras().getString("tglvisit");
        String code_image = i.getExtras().getString("code_image");
        String loanid = i.getExtras().getString("loanid");
        retrivew(cif,tglvisit,loanid,code_image);
        rc1 = (RecyclerView)findViewById(R.id.rc1);
        rc1.setLayoutManager(new LinearLayoutManager(this));
        rc1.setItemAnimator(new DefaultItemAnimator());
        adapter2 = new AdapterTesimage(this);
        rc1.setAdapter(adapter2);

        /*
        imageView = (ImageView) findViewById(R.id.imageview);*/
        btnviewimage = (Button) findViewById(R.id.btnviewimage);
        btnviewimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


//        Toast.makeText(this, cif+loanid+code_image, Toast.LENGTH_SHORT).show();
    }

    private void retrivew(String cif, String tglvisit, String loanid, String code_image) {
        pdLoading = new ProgressDialog(TesImageActivity.this);
        pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(true);
        pdLoading.show();
        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModelTesimage> getdata = api.viewtesimage(new Requestimage(cif,code_image,loanid));
        getdata.enqueue(new Callback<ResponsModelTesimage>() {
            @Override
            public void onResponse(Call<ResponsModelTesimage> call, Response<ResponsModelTesimage> response) {
                String kode = response.body().getKode();
                if (kode.equals("1")) {
                    pdLoading.dismiss();
                    dataimage = response.body().getResult();
                    adapter2.setData(dataimage);
                }
                 else {
                    pdLoading.dismiss();
                    Toast.makeText(TesImageActivity.this, "Data Tidak Di Temukan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsModelTesimage> call, Throwable t) {
                pdLoading.dismiss();
                Toast.makeText(TesImageActivity.this, "Data Tidak Di Temukan" +"\n"+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
