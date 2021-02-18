package dwiyanhartono.com.ksp3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.scottyab.rootbeer.RootBeer;

/**
 * class to pretend we are doing some really clever stuff that takes time
 * <p/>
 * Old skool Async - this could been nicer but just threw together at the mo
 */
public class CheckRootTask extends AsyncTask<Boolean, Integer, Boolean> {

    @SuppressLint("StaticFieldLeak")
    private final Context mContext;
    private final OnCheckRootFinishedListener mListener;

    public interface OnCheckRootFinishedListener {
        void onCheckRootFinished(boolean isRooted);
    }

    public CheckRootTask(Context ctx, OnCheckRootFinishedListener listener) {
        mListener = listener;
        mContext = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Boolean doInBackground(Boolean... params) {
        RootBeer check = new RootBeer(mContext);
        check.checkForNativeLibraryReadAccess();
        check.setLogging(true);
        return check.isRooted();
    }

    @Override
    protected void onPostExecute(Boolean isRooted) {
        super.onPostExecute(isRooted);
        mListener.onCheckRootFinished(isRooted);
    }

}
