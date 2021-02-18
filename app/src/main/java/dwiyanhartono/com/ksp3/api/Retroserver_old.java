package dwiyanhartono.com.ksp3.api;


import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import dwiyanhartono.com.ksp3.R;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dwiyan on.
 */

public class Retroserver_old {
//    private  static  final String base_url = "http://anagata.co.id/c24api_collection/collection/";
//    private static final String base_url = "http://192.168.31.138:8888/c24api_collection/collection/";
//    private static final String base_url = "http://192.168.40.127:8888/c24api_collection/collection/";
//    private static final String base_url = "http://192.168.43.136:8888/c24api_collection/collection/";
    private  static  final String base_url = "https://icolls.banksampoerna.co.id/c24api_collection/collection/";
//    private  static  final String base_url = "http://169.254.1 78.86:8080/api_collection/collection/";
//    private  static  final String base_url = "http://10.199.104.82:8080/api_collection/collection/";
//    private  static  final String base_url = "http://10.199.28.15:8888/c24api_collection/collection/";
//    private  static  final String base_url = "http://192.168.31.138:8888/c24api_collection/collection/";
//    private  static  final String base_url = "http://172.20.10.3:8080/api_collection/collection/";

    private static Retrofit retrofit;

    public static Retrofit getClient(Context context) {
        if (retrofit == null) {
            OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
            try {
                CertificateFactory cf = null;
                cf = CertificateFactory.getInstance("X.509");

                Certificate ca;
                // I'm using Java7. If you used Java6 close it manually with finally.
                try (InputStream cert = context.getResources().openRawResource(R.raw.certificate)) {
                    ca = cf.generateCertificate(cert);
                }

                // Creating a KeyStore containing our trusted CAs
                String keyStoreType = KeyStore.getDefaultType();
                KeyStore keyStore = KeyStore.getInstance(keyStoreType);
                keyStore.load(null, null);
                keyStore.setCertificateEntry("ca", ca);

                // Creating a TrustManager that trusts the CAs in our KeyStore.
                String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
                TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
                tmf.init(keyStore);

                TrustManager[] trustManagers = tmf.getTrustManagers();

                X509TrustManager trustManager = (X509TrustManager) trustManagers[0];

                // Creating an SSLSocketFactory that uses our TrustManager
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, new TrustManager[]{trustManager}, null);

                okHttpClient.hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });

                okHttpClient.readTimeout(30, TimeUnit.SECONDS)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .sslSocketFactory(sslContext.getSocketFactory(), trustManager);

            } catch (CertificateException | IOException | KeyStoreException | NoSuchAlgorithmException | KeyManagementException e) {
                e.printStackTrace();
            }

            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient.build())
                    .build();
        }

        return retrofit;
    }
}
