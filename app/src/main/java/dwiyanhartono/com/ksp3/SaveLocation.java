package dwiyanhartono.com.ksp3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import dwiyanhartono.com.ksp3.api.ApiRequestData;
import dwiyanhartono.com.ksp3.api.Retroserver;
import dwiyanhartono.com.ksp3.database.DBAdapter2;
import dwiyanhartono.com.ksp3.model.RequestLocation;
import dwiyanhartono.com.ksp3.model.ResponsModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveLocation extends BroadcastReceiver {
    MapLocation mapLocation = new MapLocation();
String nama, id_user;

@Override
public void onReceive(Context context, Intent intent) {
    saveToLocalStorage(context);
}

private void saveToLocalStorage(Context context) {

    try {

        DBAdapter2 db = new DBAdapter2(context);

        //OPEN
        db.openDB();

        //SELECT
        Cursor c = db.getUser();
        if (c.moveToFirst()) {
            nama = c.getString(0);
            id_user = c.getString(4);
        }
        String lat = "";
        String lng = "";
        String datetime = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        Location location = mapLocation.getLocation(context);
        if (location != null) {
            lat = String.valueOf(location.getLatitude());
            lng = String.valueOf(location.getLongitude());
        }else {

        }
        if (id_user != null && lat != "" && lng != "" ) {
//                Toast.makeText(context, "lat : "+lat +"lng : "+lng, Toast.LENGTH_SHORT).show();
            DBAdapter2 dbAdapter = new DBAdapter2(context);
            dbAdapter.openDB();
            dbAdapter.addloc(lat, lng, "BELUM TERKIRIM", datetime, id_user);
            dbAdapter.close();
            Log.d("e", String.valueOf(mapLocation.isIsNetworkEnabled()));
            if (mapLocation.isIsNetworkEnabled()) {
                sendToServer(context);
            }
        } else {
            Toast.makeText(context, "Anda Tidak Medapatkan location dengan "+"lat : "+lat +" dan long : "+lng, Toast.LENGTH_SHORT).show();
        }

    } catch (Exception e) {
        //Toast.makeText( context, e.getMessage(), Toast.LENGTH_SHORT ).show();
        e.printStackTrace();
    }
}

private void sendToServer(final Context context) {

    sendinputkunjungan();
    DBAdapter2 db = new DBAdapter2(context);
    //OPEN
    db.openDB();
    //SELECT
    Cursor c = db.getlocation();

    if (c.moveToFirst()) {
        do {
            final int id = c.getInt(0);
            final String user = c.getString(1);
            final String lat = c.getString(3);
            final String lng = c.getString(2);
            final String datetime = c.getString(4);
            String type = "Location";
            //send( user, lat, lng );
            if (mapLocation.isIsNetworkEnabled()) {
                ApiRequestData api = Retroserver.getClient(context).create(ApiRequestData.class);
                Call<ResponsModel> getdata = api.sendLocation(new RequestLocation(user, lat, lng, datetime));
                getdata.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        String kode = response.body() != null ? response.body().getKode() : "9";
                        switch (kode) {
                            case "1":
//                                Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
                                DBAdapter2 db = new DBAdapter2(context);
                                //OPEN
                                db.openDB();
                                db.DeleteLocation(id);
                                db.close();
                                break;
                            case "9":
//                                    Toast.makeText(context, "Response code : sl_SLV_01", Toast.LENGTH_SHORT).show();
                                break;
                            default:

                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {

//                            Toast.makeText(context, "Response code : sl_SLVerr_02", Toast.LENGTH_SHORT).show();
                    }
                });
                //new Backgroundworker(context).execute(type, user, lat, lng, datetime);
            }
        } while (c.moveToNext());
    }

    db.close();
}

    private void sendinputkunjungan() {

    }

    public void send(final String user, final String lat, final String lng) {

    new Runnable() {
        @Override
        public void run() {
            try {
                String location_url = "http://192.168.3.104/APIKSPKSS/uploadlocation.php";
                URL url = new URL(location_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8") + "&"
                        + URLEncoder.encode("lat", "UTF-8") + "=" + URLEncoder.encode(lat, "UTF-8") + "&"
                        + URLEncoder.encode("lng", "UTF-8") + "=" + URLEncoder.encode(lng, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();

                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                Log.d("RESULT", result);
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.d("ERR", e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("ERR1", e.getMessage());
            }
        }
    };
}
}
