package dwiyanhartono.com.ksp3;

import android.annotation.SuppressLint;
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
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import dwiyanhartono.com.ksp3.api.ApiRequestData;
import dwiyanhartono.com.ksp3.api.Retroserver;
import dwiyanhartono.com.ksp3.database.DBHelper;
import dwiyanhartono.com.ksp3.model.ResponsModel;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPhoto extends AppCompatActivity {

    String i1;
    Intent intent;
    DBHelper database;
    Uri fileUri;
    Button btn_choose_image, btn_save_image, btn_showimage;
    ImageView imageView;
    Bitmap bitmap = null, decoded;
    public final int REQUEST_CAMERA = 0;
    public final int SELECT_FILE = 1;

    int bitmap_size = 100; // image quality 1 - 100;
    int max_resolution_image = 100;

    String imageNameByTime = "";
    String date= new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());

    String iduser ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainphoto);
        Log.d("aa", "ac");
        database = new DBHelper(this);

        Intent i = getIntent();
        iduser = i.getExtras().getString("agentid");

        btn_choose_image = (Button) findViewById(R.id.btn_choose_image);
        btn_save_image = (Button) findViewById(R.id.btn_save_image);
        btn_showimage = (Button) findViewById(R.id.btn_showimage);

        imageView = (ImageView) findViewById(R.id.image_view);

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
                Intent i = new Intent(MainActivityPhoto.this, ActivityMain.class);
                startActivity(i);
                finish();
                /*startActivity(new Intent(MainActivityPhoto.this, galery.class));*/
            }
        });
    }

    public void deleteImage(String filename) {
        File fdelete = new File(filename);
        if (fdelete.exists()) {
            if (fdelete.delete()) {
                Log.e("-->", "file Deleted :");
            } else {
                Log.e("-->", "file not Deleted :");
            }
        }
    }

    private void saveImage(String imageNameByTime) {
        if (bitmap != null) {
            saveImageToFolder(bitmap, imageNameByTime);
        } else
        {
            AlertDialog.Builder alta = new AlertDialog.Builder(MainActivityPhoto.this);
            alta.setTitle("Error");
            alta.setIcon(R.drawable.warning);
            alta.setMessage("Ambil Photo Dulu");
            alta.setCancelable(false);
            alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

//                                    finish();
                }
            });
            alta.create();
            alta.show();
//            Toast.makeText(this, "Ambil foto dlu", Toast.LENGTH_SHORT).show();
        }
    }

    private void selectImage() {
        imageView.setImageResource(0);
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityPhoto.this);
        builder.setTitle("Add Photo!");
        builder.setIcon(R.drawable.camera);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //fileUri = getOutputMediaFileUri();
                    //intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } /*else if (items[item].equals("Take Video")){
                    Intent intent = new Intent(MainActivityPhoto.this,Video.class);
                    startActivity(intent);

                }*/ else if (items[item].equals("Choose from Library")) {
                    intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @SuppressLint("MissingSuperCall")
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("onActivityResult", "requestCode " + requestCode + ", resultCode " + resultCode);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                try {
                    //Log.e("CAMERA", fileUri.getPath());

                    //bitmap = BitmapFactory.decodeFile(fileUri.getPath());
                    bitmap = (Bitmap) data.getExtras().get("data");
                    setToImageView(getResizedBitmap(bitmap, max_resolution_image));
                    //setToImageView(getResizedBitmap(bitmap, max_resolution_image));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == SELECT_FILE && data != null && data.getData() != null) {
                try {
                    // mengambil gambar dari Gallery
                    bitmap = MediaStore.Images.Media.getBitmap(MainActivityPhoto.this.getContentResolver(), data.getData());
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
            final ProgressDialog pdLoading = new ProgressDialog(MainActivityPhoto.this);
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
            bmp = getResizedBitmap(bmp, max_resolution_image);
            final String filepath = getFileName(imageNameByTime);
            try {
                FileOutputStream os = new FileOutputStream(filepath);
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, os);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), getimage(imageNameByTime));
                MultipartBody.Part image = MultipartBody.Part.createFormData("image", getimage(imageNameByTime).getName(), requestFile);
                RequestBody f_cif = RequestBody.create(MediaType.parse("text/plan"), iduser);
                RequestBody f_type= RequestBody.create(MediaType.parse("text/plan"), "photo");
                RequestBody f_code= RequestBody.create(MediaType.parse("text/plan"), iduser+date);
                RequestBody f_tgl= RequestBody.create(MediaType.parse("text/plan"), date);

                ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
                Call<ResponsModel> getdata = api.sendimage(image,f_cif,f_type,f_code,f_tgl);
                getdata.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        String kode = response.body().getKode();
                        if(kode.equals("1")){
                            pdLoading.dismiss();
                            AlertDialog.Builder alta = new AlertDialog.Builder(MainActivityPhoto.this);
                            alta.setTitle("Pesan");
                            alta.setIcon(R.drawable.checklist);
                            alta.setMessage("Data Berhasil Di Simpan");
                            alta.setCancelable(false);
                            alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    deleteImage(filepath);
                                    dialog.dismiss();
                                    Intent i = new Intent(MainActivityPhoto.this, ActivityMain.class);
                                    startActivity(i);
                                    finish();
                                }
                            });

                            alta.create();
                            alta.show();

//                            Toast.makeText(MainActivityPhoto.this, "Sukses", Toast.LENGTH_SHORT).show();

                        } else {
                            pdLoading.dismiss();
                            AlertDialog.Builder alta = new AlertDialog.Builder(MainActivityPhoto.this);
                            alta.setTitle("Pesan");
                            alta.setIcon(R.drawable.warning);
                            alta.setMessage("Data Gagal Di Simpan");
                            alta.setCancelable(false);
                            alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
//                                    finish();
                                }
                            });
                            alta.create();
                            alta.show();
//                            Toast.makeText(MainActivityPhoto.this, "gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pdLoading.dismiss();
                        AlertDialog.Builder alta = new AlertDialog.Builder(MainActivityPhoto.this);
                        alta.setTitle("Error");
                        alta.setIcon(R.drawable.warning);
                        alta.setMessage("Data Gagal Di Simpan ," + t.getMessage());
                        alta.setCancelable(false);
                        alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
//                                    finish();
                            }
                        });
                        alta.create();
                        alta.show();
//                        Toast.makeText(MainActivityPhoto.this, "gagal"+","+ t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
//                Toast.makeText(this, "Sukses", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Log.d("Debug", e.toString());
            }
        } else
        {
//            Toast.makeText(this, "Ambil foto dlu", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder alta = new AlertDialog.Builder(MainActivityPhoto.this);
            alta.setTitle("Error");
            alta.setIcon(R.drawable.warning);
            alta.setMessage("Ambil Photo Dulu");
            alta.setCancelable(false);
            alta.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
//                                    finish();
                }
            });
            alta.create();
            alta.show();
        }
    }

    public File getimage(String filename) {
        File file = new File(getFileName(filename));

        return file;
    }

    /* private void savetodb(String imageNameByTime){
         if (bitmap !=null) {
             database.savePathImage(getFileName(imageNameByTime));
         } else Toast.makeText(this, "Ambil foto dlu", Toast.LENGTH_SHORT).show(); {}
     }*/
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

    public Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    private static File getOutputMediaFile() {

        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "DeKa");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.e("Monitoring", "Oops! Failed create Monitoring directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_DeKa_" + timeStamp + ".jpg");

        return mediaFile;
    }

    public String getFileName(String imageNameByTime) {
        String filename = "";
        File file = new File(Environment.getExternalStoragePublicDirectory("iColls").getAbsolutePath(), "AppsPhoto");
        if (!file.exists()) {
            file.mkdirs();
        }
        filename = file.getAbsolutePath() + "/" + imageNameByTime + ".jpg";
        Log.d("filename", filename);
        return filename;
    }

    public void addphoto(View view) {
        selectImage();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(MainActivityPhoto.this, ActivityMain.class);
        startActivity(i);
        finish();
    }


    String currentPhotoPath;

    private File createImageFile(String imageFileName) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        //String imageFileName = "JPEG_" + timeStamp + "_";
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
