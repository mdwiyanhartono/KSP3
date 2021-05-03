package dwiyanhartono.com.ksp3;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class GPSTracker extends Service implements LocationListener {

    // flag for GPS status
    boolean isGPSEnabled = false;
    // flag for network status
    boolean isNetworkEnabled = false;
    boolean canGetLocation = false;

    double latitude; // latitude
    double longitude; // longitude

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1; // 1 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 300000; // 1 sec

    // Declaring a Location Manager
    protected LocationManager locationManager;

    public static Location curLocation;
    private Boolean locationChanged;
    private Handler handler = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();

        curLocation = getLocation();

        if (curLocation == null)
            Toast.makeText(getBaseContext(), "Unable to get your location", Toast.LENGTH_SHORT).show();
        else {
            //Action ketika ambil location awal
            Toast.makeText(getBaseContext(), String.valueOf(curLocation.getLatitude()) + ":" + String.valueOf(curLocation.getLongitude()), Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        handler.postDelayed(GpsFinder, 1);
    }

    public Runnable GpsFinder = new Runnable() {
        public void run() {
            Location tempLoc = getLocation();
            if (tempLoc != null)
                curLocation = tempLoc;
            handler.postDelayed(GpsFinder, 300000);// register again to start after 1 seconds...
        }
    };

    @Override
    public void onDestroy() {
        handler.removeCallbacks(GpsFinder);
        handler = null;
        Toast.makeText(this, "Stop services", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("MissingPermission")
    public Location getLocation() {
        Location location = null; // location
        try {
            if (locationManager == null)
                locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            // getting GPS status
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true;
                // First get location from Network Provider
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }

                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);

                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

    public void stopUsingGPS() {
        if (locationManager != null) {
            locationManager.removeUpdates(GPSTracker.this);
        }
    }

    public double getLatitude() {
        if (curLocation != null) {
            latitude = curLocation.getLatitude();
        }

        // return latitude
        return latitude;
    }

    public double getLongitude() {
        if (curLocation != null) {
            longitude = curLocation.getLongitude();
        }

        // return longitude
        return longitude;
    }

    public boolean canGetLocation() {
        return this.canGetLocation;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (curLocation == null) {
            curLocation = location;
            locationChanged = true;
        } else if (curLocation.getLatitude() == location.getLatitude() && curLocation.getLongitude() == location.getLongitude()) {
            locationChanged = false;
            return;
        } else
            locationChanged = true;

        curLocation = location;

        if (locationChanged) {
            locationManager.removeUpdates(this);
            if (curLocation == null)
                Toast.makeText(getBaseContext(), "Unable to get your location", Toast.LENGTH_SHORT).show();
            else {

                //Action Jika location berubah
                //Toast.makeText(getBaseContext(), String.valueOf( curLocation.getLatitude())+":"+String.valueOf( curLocation.getLongitude()), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        if (status == 0)// UnAvailable
        {
        } else if (status == 1)// Trying to Connect
        {
        } else if (status == 2) {// Available
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}