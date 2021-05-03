package dwiyanhartono.com.ksp3.api;

import android.content.Context;
import android.database.Cursor;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dwiyanhartono.com.ksp3.database.DBAdapter2;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dwiyanon.
 */

public class Retroserver {

    private static OkHttpClient getUnsafeOkHttpClient(int TO) {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            }};

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    }).readTimeout( TO, TimeUnit.SECONDS)
                    .connectTimeout(TO, TimeUnit.SECONDS)
                    .writeTimeout(TO, TimeUnit.SECONDS);

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
//    private static final String base_url = "https://icolls.banksampoerna.co.id/c24api_collection/collection/";
//    private static final String base_url = "https://anagata.co.id/c24api_collection/collection/";
//    private static final String base_url = "https://192.168.43.51/c24api_collection/collection/";
//    private static final String base_url = "http://10.196.242.20:8787/c24api_collection/collection/";
//    private static final String base_url = "https://10.199.28.15:8888/c24api_collection/collection/";
//    private static final String base_url = "http://103.102.1.51/c24api_collection/collection/"; // server anagata
//    private static final String base_url = "https://192.168.31.138/c24api_collectionNew/collection/"; // anagata
//    private static final String base_url = "https://192.168.43.136/c24api_collectionNew/collection/"; // hotspot mobile
    private static final String base_url = "http://10.51.238.25:8082/c24api_collectionNew/collection/"; // ksp


    private static Retrofit retrofit;

    public static Retrofit getClient(Context context) {
        int TO = 1200;

        DBAdapter2 db = new DBAdapter2(context);
        db.openDB();
        Cursor c = db.gettimeral("M", "TO", "1");
        TO = 1200 ;
        if (c.moveToFirst()) {
             TO = Integer.parseInt(c.getString(0))  ;
//            Toast.makeText(this, String.valueOf(AL)+"AL" , Toast.LENGTH_SHORT).show();
        }
//        Toast.makeText(this, String.valueOf(AL)+"AL", Toast.LENGTH_SHORT).show();
        db.close();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getUnsafeOkHttpClient(TO))
                    .build();
        }
        return retrofit;
    }
}