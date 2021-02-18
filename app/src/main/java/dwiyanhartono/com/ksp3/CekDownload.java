package dwiyanhartono.com.ksp3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import dwiyanhartono.com.ksp3.api.ApiRequestData;
import dwiyanhartono.com.ksp3.api.Retroserver;
import dwiyanhartono.com.ksp3.model.RequestLocation;
import dwiyanhartono.com.ksp3.model.ResponsModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CekDownload extends BroadcastReceiver {
    MapLocation mapLocation = new MapLocation();
    String nama, id_user;

    @Override
    public void onReceive(Context context, Intent intent) {
//    saveToLocalStorage(context);
        cekserver(context);
    }

    private void saveToLocalStorage(Context context) {

    }

    private void cekserver(final Context context) {

        if (mapLocation.isIsNetworkEnabled()) {
            ApiRequestData api = Retroserver.getClient(context).create(ApiRequestData.class);
            Call<ResponsModel> getdata = api.sendLocation(new RequestLocation( "", "", "", ""));
            getdata.enqueue(new Callback<ResponsModel>() {
                @Override
                public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                    String kode = response.body().getKode();
                    if (kode.equals("1")) {
                    }
                }

                @Override
                public void onFailure(Call<ResponsModel> call, Throwable t) {

                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
