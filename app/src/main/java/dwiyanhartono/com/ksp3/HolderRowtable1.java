package dwiyanhartono.com.ksp3;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

class HolderRowtable1 extends RecyclerView.ViewHolder  {
    TextView no,jenis,alamaat,value;

    AppCompatCheckBox ck;
    public HolderRowtable1(@NonNull View itemView) {
        super(itemView);

        no = (TextView)itemView.findViewById(R.id.no);
        jenis = (TextView)itemView.findViewById(R.id.jenis);
        alamaat = (TextView)itemView.findViewById(R.id.alamat);
        value = (TextView)itemView.findViewById(R.id.value);

    }



}
