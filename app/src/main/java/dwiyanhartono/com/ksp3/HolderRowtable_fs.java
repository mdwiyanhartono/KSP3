package dwiyanhartono.com.ksp3;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

class HolderRowtable_fs extends RecyclerView.ViewHolder  {
    TextView pf,stsfs,apf,tglapf;

    AppCompatCheckBox ck;
    public HolderRowtable_fs(@NonNull View itemView) {
        super(itemView);

        pf = (TextView)itemView.findViewById(R.id.pf);
        stsfs = (TextView)itemView.findViewById(R.id.stsfs);
        apf = (TextView)itemView.findViewById(R.id.apf);
        tglapf = (TextView)itemView.findViewById(R.id.tglapf);

    }



}
