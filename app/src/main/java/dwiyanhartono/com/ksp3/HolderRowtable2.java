package dwiyanhartono.com.ksp3;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

class HolderRowtable2 extends RecyclerView.ViewHolder  {
    TextView fasilitas,plafond,os,exppros,expdate,flag,booked;

    AppCompatCheckBox ck;
    public HolderRowtable2(@NonNull View itemView) {
        super(itemView);

        fasilitas = (TextView)itemView.findViewById(R.id.fas);
        plafond = (TextView)itemView.findViewById(R.id.plaf);
        os = (TextView)itemView.findViewById(R.id.os);
        booked = (TextView)itemView.findViewById(R.id.bkd);
        exppros = (TextView)itemView.findViewById(R.id.expros);
        expdate = (TextView)itemView.findViewById(R.id.expdt);
        flag = (TextView)itemView.findViewById(R.id.flgpb);

    }



}
