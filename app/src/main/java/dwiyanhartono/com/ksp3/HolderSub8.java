package dwiyanhartono.com.ksp3;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

class HolderSub8 extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView jenisfasilits,namaproduk, namanasabah1, cif1, loan, hasilkunjungan, bertemu, lokasibertemu, actionplan;
    Button btninputvisit, btnhistory, btndetail, btnmaps;

    LinearLayout lytglptp;
    AppCompatCheckBox ck;
    ItemClickListner itemClickListener;

    public HolderSub8(@NonNull View itemView) {
        super(itemView);

        namanasabah1 = (TextView) itemView.findViewById(R.id.namanasabah);
        cif1 = (TextView) itemView.findViewById(R.id.cif);
//        loan = (TextView) itemView.findViewById(R.id.loanid);
//        jenisfasilits = (TextView) itemView.findViewById(R.id.jenisfasilitas);
        namaproduk = (TextView) itemView.findViewById(R.id.namaproduk1);

    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListner itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
