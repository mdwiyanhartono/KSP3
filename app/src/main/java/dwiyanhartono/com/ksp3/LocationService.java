package dwiyanhartono.com.ksp3;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class LocationService extends Service {
    // Thread initialization
    Thread t = new UpdateLocation();

    @Override
    public void onCreate() {
        super.onCreate();
        // starting the thread
        t.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(!t.isAlive())
        {
            t.start();
        }
        return Service.START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
