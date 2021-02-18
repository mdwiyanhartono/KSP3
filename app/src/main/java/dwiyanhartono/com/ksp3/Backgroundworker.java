package dwiyanhartono.com.ksp3;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

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

import dwiyanhartono.com.ksp3.database.DBAdapter;

public class Backgroundworker extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog.Builder alertDialog;

    private String ip,port1;
    public Backgroundworker(Context ctx) {
        context = ctx;
        getnetwork();
    }

    private void getnetwork() {
        DBAdapter db=new DBAdapter(context);

        //OPEN
        db.openDB();

        //INSERT
        Cursor c=db.getnetwork();
        if (c.moveToFirst()){
            ip = c.getString( 0);
            port1 = c.getString( 1);
        }

        db.close();

    }
    @Override
    protected String doInBackground(String... params) {
        getnetwork();
        String type = params[0];
        String login_url = "http://192.168.3.105/Login1.php";
        String register_url = "http://192.168.3.106/register.php";
        String location_url = "http://"+ ip +"/c24system/APIKSPKSS/uploadlocation.php";
        if (type.equals( "Login" )) {
            try {
                String username = params[1];
                String password = params[2];
                URL url = new URL( login_url );
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod( "POST" );
                httpURLConnection.setDoOutput( true );
                httpURLConnection.setDoInput( true );
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter( outputStream, "UTF-8" ) );
                String post_data = URLEncoder.encode( "username", "UTF-8" ) + "=" + URLEncoder.encode( username, "UTF-8" ) + "&" + URLEncoder.encode( "password", "UTF-8" ) + "=" + URLEncoder.encode( password, "UTF-8" );
                bufferedWriter.write( post_data );
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream, "iso-8859-1" ) );
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals( "Register" )) {
            try {
                String name = params[1];
                String email = params[2];
                String username = params[3];
                String password = params[4];
                URL url = new URL( register_url );
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod( "POST" );
                httpURLConnection.setDoOutput( true );
                httpURLConnection.setDoInput( true );
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter( outputStream, "UTF-8" ) );
                String post_data = URLEncoder.encode( "sname", "UTF-8" ) + "=" + URLEncoder.encode( name, "UTF-8" ) + "&"
                        + URLEncoder.encode( "semail", "UTF-8" ) + "=" + URLEncoder.encode( email, "UTF-8" ) + "&"
                        + URLEncoder.encode( "user_name", "UTF-8" ) + "=" + URLEncoder.encode( username, "UTF-8" ) + "&"
                        + URLEncoder.encode( "user_pass", "UTF-8" ) + "=" + URLEncoder.encode( password, "UTF-8" );
                bufferedWriter.write( post_data );
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream, "iso-8859-1" ) );
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (type.equals( "Location" )) {
            try {
                String user = params[1];
                String lat = params[2];
                String lng = params[3];
                String datetime = params[4];
                URL url = new URL( location_url );

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod( "POST" );
                httpURLConnection.setDoOutput( true );
                httpURLConnection.setDoInput( true );

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter( outputStream, "UTF-8" ) );

                String post_data = URLEncoder.encode( "user", "UTF-8" ) + "=" + URLEncoder.encode( user, "UTF-8" ) + "&"
                        + URLEncoder.encode( "lat", "UTF-8" ) + "=" + URLEncoder.encode( lat, "UTF-8" ) + "&"
                        + URLEncoder.encode( "lng", "UTF-8" ) + "=" + URLEncoder.encode( lng, "UTF-8" ) + "&"
                        + URLEncoder.encode( "datetime", "UTF-8" ) + "=" + URLEncoder.encode( datetime, "UTF-8" );

                bufferedWriter.write( post_data );
                bufferedWriter.flush();
                bufferedWriter.close();

                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream, "iso-8859-1" ) );
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }


    @Override
    protected void onPreExecute() {
//        alertDialog = new AlertDialog.Builder( context );
//        alertDialog.setTitle( "Login Status" );
    }

    @Override
    protected void onPostExecute(String result) {

//        alertDialog.setMessage( result );
//        alertDialog.show();
        if(result!=null) {
            Log.d( "", result );
            if (!result.equals( "error" )) {
                DBAdapter db = new DBAdapter( context );
                //OPEN
                db.openDB();
                db.DeleteLocation(1);
                db.close();
            }
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate( values );
    }
}
