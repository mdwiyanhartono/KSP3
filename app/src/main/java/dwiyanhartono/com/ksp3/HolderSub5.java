package dwiyanhartono.com.ksp3;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

class HolderSub5 extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView nominal,tanggalactionplan,namaanggota, cif, loan, hasilkunjungan, bertemu, lokasibertemu, actionplan;
    Button btninputvisit, btnhistory, btndetail, btnmaps;

    LinearLayout lytglptp;
    AppCompatCheckBox ck;
    ItemClickListner itemClickListener;

    public HolderSub5(@NonNull View itemView) {
        super(itemView);

        namaanggota = (TextView) itemView.findViewById(R.id.namaanggota);
        cif = (TextView) itemView.findViewById(R.id.cif);
        loan = (TextView) itemView.findViewById(R.id.loanid);
        hasilkunjungan = (TextView) itemView.findViewById(R.id.hasilkunjungan);
        bertemu= (TextView) itemView.findViewById(R.id.bertemu);
        lokasibertemu = (TextView) itemView.findViewById(R.id.lokasibertemu);
        actionplan= (TextView) itemView.findViewById(R.id.actionplan);
        tanggalactionplan = (TextView) itemView.findViewById(R.id.tanggalactionplan);
        ck = (AppCompatCheckBox) itemView.findViewById(R.id.ck);
        nominal = (TextView) itemView.findViewById(R.id.nominalplan);
//        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListner itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
