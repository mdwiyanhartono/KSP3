package dwiyanhartono.com.ksp3;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

class HolderRowtable_rs extends RecyclerView.ViewHolder  {
    TextView rske,gp,jkgp;

    AppCompatCheckBox ck;
    public HolderRowtable_rs(@NonNull View itemView) {
        super(itemView);

        rske = (TextView)itemView.findViewById(R.id.reske);
        gp = (TextView)itemView.findViewById(R.id.gp);
        jkgp = (TextView)itemView.findViewById(R.id.jwgp);

    }



}
