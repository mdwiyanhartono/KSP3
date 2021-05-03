package dwiyanhartono.com.ksp3;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import dwiyanhartono.com.ksp3.base.BaseActivity;
import dwiyanhartono.com.ksp3.database.DBAdapter2;
import dwiyanhartono.com.ksp3.database.DBHelper;

public class ActivityPhotoInputKunjungan extends BaseActivity {

    String fileUri1;
    Intent intent;
    DBHelper database;
    Uri fileUri;
    Button btn_listimage, btn_choose_image, btn_save_image, btn_showimage;
    ImageView imageView;
    Bitmap bitmap = null, decoded;
    public final int REQUEST_CAMERA = 0;
    EditText ket;
    public final int SELECT_FILE = 1;

    int bitmap_size = 100; // image quality 1 - 100;
    int max_resolution_image = 900;

    String imageNameByTime = "";
    String date = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());

    String cif, loanid, type, code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputphoto);
        Log.d("aa", "ac");
        database = new DBHelper(this);


        Intent i = getIntent();
        cif = i.getExtras().getString("cif");
        loanid = i.getExtras().getString("loanid");
        type = i.getExtras().getString("type");
        code = i.getExtras().getString("code");

//        Toast.makeText(this, cif + "/" + loanid + "/" + type, Toast.LENGTH_SHORT).show();

        btn_choose_image = (Button) findViewById(R.id.btn_choose_image);
        btn_save_image = (Button) findViewById(R.id.btn_save_image);
        btn_showimage = (Button) findViewById(R.id.btn_showimage);
        btn_listimage = (Button) findViewById(R.id.btn_listimage);
        ket = (EditText) findViewById(R.id.keterangan);

        imageView = (ImageView) findViewById(R.id.image_view);
        btn_listimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityPhotoInputKunjungan.this, TesImageActivityList.class);
                i.putExtra("code_image", code);
                i.putExtra("type", type);
                i.putExtra("cif", cif);
                i.putExtra("loanid", loanid);
                startActivity(i);

            }
        });

        btn_choose_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        btn_save_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageNameByTime = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                saveImage(imageNameByTime);
                // saveImageToFolder(bitmap,imageNameByTime);
                //savetodb(imageNameByTime);
                imageView.setImageResource(0);
            }
        });
        btn_showimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                startActivity(new Intent(ActivityPhotoInputKunjungan.this, galery.class));
            }
        });
    }


    private void saveImage(String imageNameByTime) {
        if (bitmap != null) {
            saveImageToFolder(bitmap, imageNameByTime);
        } else Toast.makeText(this, "ambil foto dlu", Toast.LENGTH_SHORT).show();
        {
        }
    }

    private void selectImage() {
        imageView.setImageResource(0);
        final CharSequence[] items = {"Take Photo",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityPhotoInputKunjungan.this);
        builder.setTitle("Add Photo!");
        builder.setIcon(R.drawable.camera);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File filePhoto = null;
                    try {
                        filePhoto = createImageFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (filePhoto != null) {
                        fileUri = null;
                        fileUri = FileProvider.getUriForFile(ActivityPhotoInputKunjungan.this,
                                "dwiyanhartono.com.android.fileprovider",
                                filePhoto);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                        startActivityForResult(intent, REQUEST_CAMERA);
//                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
                    }

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("onActivityResult", "requestCode " + requestCode + ", resultCode " + resultCode);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                try {
                    //Log.e("CAMERA", fileUri.getPath());

                    bitmap = BitmapFactory.decodeFile(currentPhotoPath);
//                    bitmap = (Bitmap) data.getExtras().get("data");
                    setToImageView(getResizedBitmap(bitmap, max_resolution_image));
//                    setToImageView(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == SELECT_FILE && data != null && data.getData() != null) {
                try {
                    // mengambil gambar dari Gallery
                    bitmap = MediaStore.Images.Media.getBitmap(ActivityPhotoInputKunjungan.this.getContentResolver(), data.getData());
                    setToImageView(getResizedBitmap(bitmap, max_resolution_image));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Untuk menampilkan bitmap pada ImageView
    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));
        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imageView.setImageBitmap(bmp);
    }

    private void saveImageToFolder(Bitmap bmp, String imageNameByTime) {
        if (bmp != null) {
//            bmp = getResizedBitmap(bmp, max_resolution_image);
            String filepath = getFileName(imageNameByTime);
            try {
                final ProgressDialog pdLoading = new ProgressDialog(ActivityPhotoInputKunjungan.this);
                pdLoading.setMessage("\tLoading...");
                pdLoading.setCancelable(false);
                pdLoading.show();
                FileOutputStream os = new FileOutputStream(filepath);

                bmp.compress(Bitmap.CompressFormat.JPEG, 50, os);

                DBAdapter2 db = new DBAdapter2(this);
                db.openDB();
                long result = db.addimage(cif, "", code, imageNameByTime + ".jpg", ket.getText().toString(), date, type, "0");
                if (result > 0) {
                    pdLoading.dismiss();
                    AlertDialog.Builder alta = new AlertDialog.Builder(ActivityPhotoInputKunjungan.this);
                    alta.setTitle("Pesan");
                    alta.setIcon(R.drawable.checklist);
                    alta.setMessage("Data Berhasil Di Simpan");
                    alta.setCancelable(false);
                    alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            ket.setText("");
                            bitmap = null;
                        }
                    });
                    alta.create();
                    alta.show();
                }
                db.close();

            } catch (Exception e) {
                Log.d("Debug", e.toString());
            }
        } else Toast.makeText(this, "ambil foto dulu", Toast.LENGTH_SHORT).show();
        {

        }
    }

    public File getimage(String filename) {
        File file = new File(getFileName(filename));

        return file;
    }

    // Untuk resize bitmap
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public Uri getOutputMediaFileUri() throws IOException {
        return Uri.fromFile(getFileName1(imageNameByTime));
    }

    private File getOutputMediaFile(String imageNameByTime) throws IOException {

        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS + "/Colsys").getAbsolutePath(), "AppsPhoto");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.e("Monitoring", "Oops! Failed create Monitoring directory");
                return null;
            }
        }


        // Create a media file name

        File image = File.createTempFile(
                imageNameByTime,  /* prefix */
                ".jpg",         /* suffix */
                mediaStorageDir      /* directory */
        );

        fileUri1 = image.getAbsolutePath();
        return image ;
    }

    public String getFileName(String imageNameByTime) {
        String filename = "";
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS + "/Colsys").getAbsolutePath(), "AppsPhoto");
        if (!file.exists()) {
            file.mkdirs();
        }
        filename = file.getAbsolutePath() + "/" + imageNameByTime + ".jpg";
        Log.d("filename", filename);
        return filename;
    }

    private File getFileName1(String imageNameByTime) {

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS + "/Colsys").getAbsolutePath(), "AppsPhoto");
        if (!file.exists()) {
            file.mkdirs();
        }
        File filename = new File (file.getAbsolutePath() + File.separator + imageNameByTime + ".jpg" );
        return filename;
    }

    public void addphoto(View view) {
        selectImage();
    }

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
