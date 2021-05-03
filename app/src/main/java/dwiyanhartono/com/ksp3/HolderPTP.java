package dwiyanhartono.com.ksp3;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

class HolderPTP extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView nominal,tanggalactionplan,namaanggota, cif, loan, hasilkunjungan, bertemu, lokasibertemu, actionplan;
    Button btninputvisit, btnhistory, btndetail, btnmaps;

    LinearLayout lytglptp;
    AppCompatCheckBox ck;

    ItemClickListner itemClickListener;

    public HolderPTP(@NonNull View itemView) {
        super(itemView);

        namaanggota = (TextView) itemView.findViewById(R.id.namaanggota);
        cif = (TextView) itemView.findViewById(R.id.cif);
//        loan = (TextView) itemView.findViewById(R.id.loanid);
        hasilkunjungan = (TextView) itemView.findViewById(R.id.hasilkunjungan);
        bertemu= (TextView) itemView.findViewById(R.id.bertemu);
        lokasibertemu = (TextView) itemView.findViewById(R.id.lokasibertemu);
        actionplan= (TextView) itemView.findViewById(R.id.actionplan);
        tanggalactionplan = (TextView) itemView.findViewById(R.id.tanggalactionplan);
        nominal = (TextView) itemView.findViewById(R.id.nominalplan);
        ck = itemView.findViewById(R.id.ck);

    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListner itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
