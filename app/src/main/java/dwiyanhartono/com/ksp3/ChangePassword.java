package dwiyanhartono.com.ksp3;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import dwiyanhartono.com.ksp3.api.ApiRequestData;
import dwiyanhartono.com.ksp3.api.Retroserver;
import dwiyanhartono.com.ksp3.database.DBAdapter2;
import dwiyanhartono.com.ksp3.model.RequestChangepsd;
import dwiyanhartono.com.ksp3.model.ResponsModelChangePswd;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity implements Validator.ValidationListener {

    EditText  psdconfirm;
    Button kirim;
    TextView text1;

    String agentid, id_user, nama, email;
    Validator validator;

    @NotEmpty(sequence = 1, messageResId = R.string.input_diperlukan)
    @BindView(R.id.psdold)
    @Order(value = 1)
    EditText psdold;

    @NotEmpty(sequence = 1, messageResId = R.string.input_diperlukan)
    @Password(min = 8, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE, sequence = 2, messageResId = R.string.password_salah)
    @BindView(R.id.psdnew)
    @Order(value = 4)
    EditText psdnew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ceklog();

        validator = new Validator(this);
        validator.setValidationListener(this);

        psdold = (EditText) findViewById(R.id.psdold);
        psdnew = (EditText) findViewById(R.id.psdnew);
        psdconfirm = (EditText) findViewById(R.id.psdconfirm);
        kirim = (Button) findViewById(R.id.kirim);
        text1 = (TextView) findViewById(R.id.textinfo);

        psdconfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                text1.setText("Masukan Ulang Password Baru");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String pssnew = psdnew.getText().toString();
                String psscon = psdconfirm.getText().toString();

                if (psscon.equals(pssnew)) {
                    text1.setText("Password Sesuai");
                    text1.setTextColor(Color.BLUE);
                } else {
                    text1.setText("Password Tidak Sesuai");
                    text1.setTextColor(Color.RED);
                }

            }
        });

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (psdold.getText().toString().length() == 0) {
//                    psdold.setError("Username tidak boleh kosong");
//                } else if (psdconfirm.getText().toString().length() == 0) {
//                    psdconfirm.setError("Password tidak boleh kosong");
//                }
                validator.validate();
            }
        });
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
        Toast.makeText(this, id_user, Toast.LENGTH_SHORT).show();
    }
    String kode1;
    @Override
    public void onValidationSucceeded() {
        String info = text1.getText().toString();
        if (info.equals("Password Sesuai")) {
            final ProgressDialog pdLoading = new ProgressDialog(ChangePassword.this);

            ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
            Call<ResponsModelChangePswd> getdata = api.sendChangepsd(new RequestChangepsd(psdold.getText().toString(), psdconfirm.getText().toString(), id_user));
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
            getdata.enqueue(new Callback<ResponsModelChangePswd>() {
                @Override
                public void onResponse(Call<ResponsModelChangePswd> call, Response<ResponsModelChangePswd> response) {
                    kode1 = response.body().getKode();
                    String message = response.body().getMessage();
                    if (kode1.equals("1")) {
                        pdLoading.dismiss();
                        DBAdapter2 db = new DBAdapter2(ChangePassword.this);
                        db.openDB();
                        db.Deleteuser();
                        db.close();
                        AlertDialog.Builder alta = new AlertDialog.Builder(ChangePassword.this);
                        alta.setTitle("Info");
                        alta.setIcon(R.drawable.checklist);
                        alta.setMessage(message);
                        alta.setCancelable(false);
                        alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent inten = new Intent(ChangePassword.this, SplashScreen.class);
                                startActivity(inten);
                                finish();
                            }
                        });

                        alta.create();
                        alta.show();

                    } else {
                        pdLoading.dismiss();
                        String message1 = response.body().getMessage();
                        AlertDialog.Builder alta = new AlertDialog.Builder(ChangePassword.this);
                        alta.setTitle("Info1");
                        alta.setIcon(R.drawable.warning);
                        alta.setMessage(message1);
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
                public void onFailure(Call<ResponsModelChangePswd> call, Throwable t) {
                    pdLoading.dismiss();
//                    Toast.makeText(ChangePassword.this, kode1, Toast.LENGTH_SHORT).show();

                    AlertDialog.Builder alta = new AlertDialog.Builder(ChangePassword.this);
                    alta.setTitle("Info2");
                    alta.setIcon(R.drawable.warning);
                    alta.setMessage(t.getMessage());
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
        } else {
            Toast.makeText(ChangePassword.this, "Password Baru Tidak Sesuai", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            }
        }

        if (errors.size() > 0) {
            if (errors.get(0).getView() instanceof EditText) {
                errors.get(0).getView().requestFocus();
            } else {
                String message = errors.get(0).getCollatedErrorMessage(this);
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onBackPressed() {
        Intent inten = new Intent(ChangePassword.this, SplashScreen.class);
        startActivity(inten);
        finish();
    }
}
