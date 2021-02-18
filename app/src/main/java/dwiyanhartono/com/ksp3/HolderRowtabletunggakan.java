package dwiyanhartono.com.ksp3;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

class HolderRowtabletunggakan extends RecyclerView.ViewHolder  {
//    TextView fasilitas,plafond,os,exppros,expdate,flag,booked;
    TextView flid,flpi,fltd,flit,fldp,fldi,flndo,fldc;

    AppCompatCheckBox ck;
    public HolderRowtabletunggakan(@NonNull View itemView) {
        super(itemView);

        flid = (TextView)itemView.findViewById(R.id.flid);
        flpi = (TextView)itemView.findViewById(R.id.flpi);
        fltd = (TextView)itemView.findViewById(R.id.fltd);
        flit = (TextView)itemView.findViewById(R.id.flit);
        fldp = (TextView)itemView.findViewById(R.id.fldp);
        fldi = (TextView)itemView.findViewById(R.id.fldi);
        flndo = (TextView)itemView.findViewById(R.id.flndo);
        fldc = (TextView)itemView.findViewById(R.id.fldc);

    }



}
