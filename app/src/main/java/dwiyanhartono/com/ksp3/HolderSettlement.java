package dwiyanhartono.com.ksp3;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

class HolderSettlement extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView nominal,namaanggota, cif,LD,tunggakan,norek;
    Button btnprint, btnsettlement;

    LinearLayout lytglptp;
    AppCompatCheckBox ck;
    ItemClickListner itemClickListener;

    public HolderSettlement(@NonNull View itemView) {
        super(itemView);

        namaanggota = (TextView) itemView.findViewById(R.id.namaanggota);
        cif = (TextView) itemView.findViewById(R.id.cif);
        nominal = (TextView) itemView.findViewById(R.id.nominal);
        LD = (TextView) itemView.findViewById(R.id.ld);
        norek = (TextView) itemView.findViewById(R.id.norekanggota);
//        tunggakan = (TextView) itemView.findViewById(R.id.tunggakan);
//        btnprint = (Button) itemView.findViewById(R.id.print);
//        btnsettlement = (Button) itemView.findViewById(R.id.settlement);
        ck = itemView.findViewById(R.id.ck);
//        btnprint.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListner itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
