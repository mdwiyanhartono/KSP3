package dwiyanhartono.com.ksp3;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;
import dwiyanhartono.com.ksp3.database.DBAdapter;

public class MapLocation extends Service implements LocationListener {
    // flag for GPS status
    public static boolean isGPSEnabled = false;
    // flag for network status
    public static boolean isNetworkEnabled = false;
    public static boolean canGetLocation = false;

    public static double latitude; // latitude
    public static double longitude; // longitude

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1; // 1 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 300000; // 1 sec

    // Declaring a Location Manager
    public static LocationManager locationManager;

    public static Location curLocation;

    public void saveLocationLocalStorage(String user, String lat, String lng, String datetime) {
        DBAdapter dbAdapter = new DBAdapter(getApplicationContext());
        //dbAdapter.addloc( user,lat,lng,"BELUM terkirim", datetime);
    }

    @SuppressLint("MissingPermission")
    public Location getLocation(Context context) {
        Location location = null; // location
        try {
            if (locationManager == null)
                locationManager = (LocationManager) context.getSystemService( LOCATION_SERVICE );
            // getting GPS status
            isGPSEnabled = locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER );

            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled( LocationManager.NETWORK_PROVIDER );

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true;
                // First get location from Network Provider
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this );

                    Log.d( "Network", "Network" );
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation( LocationManager.NETWORK_PROVIDER );

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
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this );

                        Log.d( "GPS Enabled", "GPS Enabled" );
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation( LocationManager.GPS_PROVIDER );

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
            locationManager.removeUpdates( MapLocation.this );
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

    public boolean isIsNetworkEnabled() {
        return isNetworkEnabled;
    }

    @Nullable
    public boolean canGetLocation() {
        return this.canGetLocation;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
