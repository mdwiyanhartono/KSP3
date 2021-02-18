package dwiyanhartono.com.ksp3;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import dwiyanhartono.com.ksp3.api.ApiRequestData;
import dwiyanhartono.com.ksp3.api.Retroserver;
import dwiyanhartono.com.ksp3.model.DataLocation;
import dwiyanhartono.com.ksp3.model.Requestcontact;
import dwiyanhartono.com.ksp3.model.ResponsModelLocation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityMapsTracking extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener {

    private GoogleMap mMap;

    String latOwn = "-6.233879";
    String longOwn = "106.799192";
    String latacount = "";
    String longacount = "";
    String lat3 = "";
    String lng3 = "";
    EditText alamat1;
    String cif, acctno, alamat;
    private static final int LOCATION_REQUEST = 500;
    Marker marker, markeragent;
    ArrayList<LatLng> listPoints;
    private DataLocation dataLocation;
    private List<DataLocation> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_tracking);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        Intent i = getIntent();
        cif = i.getExtras().getString("cif");
        alamat = i.getExtras().getString("alamat");
        /*acctno=i.getExtras().getString("acctno");
         */

        alamat1 = (EditText) findViewById(R.id.alamat);
        /*alamat1.setEnabled(false);*/
        alamat1.setText(alamat);
//        Toast.makeText(this, cif, Toast.LENGTH_SHORT).show();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        listPoints = new ArrayList<>();

        String getlock = alamat1.getText().toString();
//        GeocodingLocation Locationaddress = new GeocodingLocation();
//        getLocationFromAddress(alamat1.getText().toString());

    }

    public GeoPoint getLocationFromAddress(final GoogleMap googleMap ,String strAddress) {

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        GeoPoint p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new GeoPoint((double) (location.getLatitude() * 1E6),
                    (double) (location.getLongitude() * 1E6));
            GeoPoint Lat1 = new GeoPoint((double) (location.getLatitude() * 1E6));

            lat3 = String.valueOf(location.getLatitude());
            lng3 = String.valueOf(location.getLongitude());

//            Toast.makeText(this, lat3+"/"+lng3, Toast.LENGTH_SHORT).show();
            mMap = googleMap;
            LatLng indonesia = new LatLng(Double.parseDouble(lat3), Double.parseDouble(lng3));
            marker = mMap.addMarker(new MarkerOptions().position(indonesia));
            marker.setTag(new MarkerInfo(cif, acctno));
            //        mMap.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);
            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker2));


            String url = getRequestUrl();
            TaskRequestDirections taskRequestDirections = new TaskRequestDirections();
            taskRequestDirections.execute(url);

            return p1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p1;
    }

    private void retrivew(final GoogleMap googleMap) {
        final ProgressDialog pdLoading = new ProgressDialog(this);
        pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(false);
        pdLoading.show();
        ApiRequestData api = Retroserver.getClient(getApplicationContext()).create(ApiRequestData.class);
        Call<ResponsModelLocation> getdata = api.viewlocation(new Requestcontact(cif));
        getdata.enqueue(new Callback<ResponsModelLocation>() {
            @Override
            public void onResponse(Call<ResponsModelLocation> call, Response<ResponsModelLocation> response) {
                String kode = response.body().getKode();
                Log.d("kode", kode);
                if (kode.equals("1")) {
                    pdLoading.dismiss();
                    dataLocation = response.body().getResult();
                    latacount = dataLocation.getLat();
                    longacount = dataLocation.getLng();

                    mMap = googleMap;
                    LatLng indonesia = new LatLng(Double.parseDouble(latacount), Double.parseDouble(longacount));
                    marker = mMap.addMarker(new MarkerOptions().position(indonesia));
                    marker.setTag(new MarkerInfo(cif, acctno));
                    //        mMap.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);
                    marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker2));

//                    Toast.makeText(ActivityMapsTracking.this, latacount + "/" + longacount, Toast.LENGTH_LONG).show();
                    String url = getRequestUrl();
                    TaskRequestDirections taskRequestDirections = new TaskRequestDirections();
                    taskRequestDirections.execute(url);

                } else if (kode.equals("0")) {
                    pdLoading.dismiss();
                    dataLocation = response.body().getResult();
                    Toast.makeText(ActivityMapsTracking.this, latacount + "/" + longacount, Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ResponsModelLocation> call, Throwable t) {
                pdLoading.dismiss();
                Toast.makeText(ActivityMapsTracking.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("error", t.getMessage());
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

//        Toast.makeText(this, lat3+"/"+lng3, Toast.LENGTH_LONG).show();
        mMap = googleMap;
        /*DBAdapter db=new DBAdapter(this);



        //OPEN
        db.openDB();

        Cursor c = db.getlonglattrack(cif,acctno);
        if (c.moveToFirst()) {
            latacount = c.getString(1);
            longacount = c.getString(0);
        }

        db.close();*/


//        Toast.makeText(this, lat3+"/"+lng3, Toast.LENGTH_LONG).show();
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
            return;
        }

        MapLocation mapLocation = new MapLocation();

        Location location = mapLocation.getLocation(this);
        if (location != null) {
            latOwn = String.valueOf(location.getLatitude());
            longOwn = String.valueOf(location.getLongitude());
        }

        //Toast.makeText(ActivityMapsTracking.this, latacount+"/"+longacount, Toast.LENGTH_LONG).show();
        /*LatLng indonesia = new LatLng(Double.parseDouble(latacount),Double.parseDouble(longacount) );
        marker = mMap.addMarker(new MarkerOptions().position(indonesia));
        marker.setTag(new MarkerInfo(cif, acctno));
//        mMap.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);
        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.location));*/

        LatLng indonesia1 = new LatLng(Double.parseDouble(latOwn), Double.parseDouble(longOwn));
        markeragent = mMap.addMarker(new MarkerOptions().position(indonesia1).title("Agent"));
        markeragent.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.hm));
        markeragent.setTag(new MarkerInfo("000", "0000"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(indonesia1));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(indonesia1, 9.0f));
        mMap.setMyLocationEnabled(true);
        mMap.setMinZoomPreference(5.0f);
        mMap.setMaxZoomPreference(18.0f);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        mMap.setMyLocationEnabled(true);
//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng latLng) {
//                Log.d("latlng", latLng.latitude+":"+latLng.longitude);
//                //Reset marker when already 2
//                if (listPoints.size() == 2) {
//                    listPoints.clear();
//                    mMap.clear();
//                }
//                //Save first point select
//                listPoints.add(latLng);
//                //Create marker
//                MarkerOptions markerOptions = new MarkerOptions();
//                markerOptions.position(latLng);
//                Log.d("sizelist", String.valueOf(listPoints.size()));
//                if (listPoints.size() == 1) {
//      bcbss.PNG              //Add first marker to the map
//                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
//                } else {
//                    //Add second marker to the map
//                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//                }
//                mMap.addMarker(markerOptions);
//
//                if (listPoints.size() == 2) {
//                    //Create the URL to get request from first marker to second marker
//                    String url = getRequestUrl(listPoints.get(0), listPoints.get(1));
//                    Log.d("url", url);
//                    TaskRequestDirections taskRequestDirections = new TaskRequestDirections();
//                    taskRequestDirections.execute(url);
//                }
//            }
//        });
//        retrivew(googleMap);
        getLocationFromAddress(googleMap,alamat);

    }

    private String getRequestUrl() {
        //Value of origin
        String str_org = "origin=" + latOwn + "," + longOwn;
        //Value of destination
        String str_dest = "destination=" + lat3 + "," + lng3;
        //Set value enable the sensor
        String sensor = "sensor=false";
        //Mode for find direction
        String mode = "mode=driving";
        //Build the full param
        String param = str_org + "&" + str_dest + "&" + sensor + "&" + mode;
        //Output format
        String output = "json";
        //Create url to request
//        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + param + "&" + "key=AIzaSyDPpwtHPht7fUkfsPVMtYAUpGk3vx0wQkE";
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + param + "&" + "key=AIzaSyDPpwtHPht7fUkfsPVMtYAUpGk3vx0wQkE";
        return url;
    }

    private String requestDirection(String reqUrl) throws IOException {
        String responseString = "";
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(reqUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            //Get the response result
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }

            responseString = stringBuffer.toString();
            bufferedReader.close();
            inputStreamReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            httpURLConnection.disconnect();
        }
        return responseString;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                }
                break;
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    @SuppressLint("StaticFieldLeak")
    public class TaskRequestDirections extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String responseString = "";
            try {
                responseString = requestDirection(strings[0]);
                Log.d("string[0]_1", strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Parse json here
            TaskParser taskParser = new TaskParser();
            taskParser.execute(s);
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class TaskParser extends AsyncTask<String, Void, List<List<HashMap<String, String>>>> {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... strings) {
            JSONObject jsonObject = null;
            List<List<HashMap<String, String>>> routes = null;
            try {

                jsonObject = new JSONObject(strings[0]);
                DirectionsParser directionsParser = new DirectionsParser();
                routes = directionsParser.parse(jsonObject);
                Log.d("string[0]_2", strings[0]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> lists) {
            //Get list route and display it into the map

            ArrayList points = null;

            PolylineOptions polylineOptions = null;

            for (List<HashMap<String, String>> path : lists) {
                points = new ArrayList();
                polylineOptions = new PolylineOptions();

                for (HashMap<String, String> point : path) {
                    double lat = Double.parseDouble(point.get("lat"));
                    double lon = Double.parseDouble(point.get("lon"));

                    points.add(new LatLng(lat, lon));
                }

                polylineOptions.addAll(points);
                polylineOptions.width(15);
                polylineOptions.color(Color.BLUE);
                polylineOptions.geodesic(true);
            }

            if (polylineOptions != null) {
                mMap.addPolyline(polylineOptions);
            } else {
                Toast.makeText(getApplicationContext(), "Direction not found!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    private class GeoPoint {
        public GeoPoint(double v, double v1) {
            String lat1 = String.valueOf(v);
            String lng1 = String.valueOf(v1);
        }

        public GeoPoint(double v) {
        }
    }
}
