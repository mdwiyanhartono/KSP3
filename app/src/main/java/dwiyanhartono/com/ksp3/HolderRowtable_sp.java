package dwiyanhartono.com.ksp3;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

class HolderRowtable_sp extends RecyclerView.ViewHolder  {
    TextView spke,tglsp,speke,tglspe;

    AppCompatCheckBox ck;
    public HolderRowtable_sp(@NonNull View itemView) {
        super(itemView);

        spke = (TextView)itemView.findViewById(R.id.spke);
        tglsp = (TextView)itemView.findViewById(R.id.tglsp);
        speke = (TextView)itemView.findViewById(R.id.speke);
        tglspe = (TextView)itemView.findViewById(R.id.tglspeke);

    }



}
