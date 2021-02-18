package dwiyanhartono.com.ksp3;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

class HolderSub4 extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView namanasabah, cif, loan, hasilkunjungan, bertemu, lokasibertemu, actionplan;
    Button btninputvisit, btnhistory, btndetail, btnmaps;

    LinearLayout lytglptp;
    AppCompatCheckBox ck;
    ItemClickListner itemClickListener;

    public HolderSub4(@NonNull View itemView) {
        super(itemView);

        namanasabah = (TextView) itemView.findViewById(R.id.namanasabah);
        cif = (TextView) itemView.findViewById(R.id.cif);
        loan = (TextView) itemView.findViewById(R.id.loanid);
        hasilkunjungan = (TextView) itemView.findViewById(R.id.hasilkunjungan);
        bertemu= (TextView) itemView.findViewById(R.id.bertemu);
        lokasibertemu = (TextView) itemView.findViewById(R.id.lokasibertemu);
        actionplan= (TextView) itemView.findViewById(R.id.actionplan);


    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListner itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
