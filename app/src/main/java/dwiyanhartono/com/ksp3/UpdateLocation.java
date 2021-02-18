package dwiyanhartono.com.ksp3;

import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UpdateLocation extends Thread{
    MapLocation mapLocation = new MapLocation();
    Location location;

    String lat = "";
    String lng = "";
    String datetime = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
    @Override
    public void run() {
         /*while (true) {
            try {

                //get location from your location provider
                //location = mapLocation.getLocation();
                if (location != null) {
                    lat = String.valueOf(location.getLatitude()); // Updated lat
                    lng = String.valueOf(location.getLongitude()); // Updated long
                }
                else {
                    String latLongStr = "no lat and longitude found";
                }
                mapLocation.saveLocationLocalStorage(,"", lat, lng, datetime);
                //pause thread every 10 seconds
                Thread.sleep(1000);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }*/
    }


}
