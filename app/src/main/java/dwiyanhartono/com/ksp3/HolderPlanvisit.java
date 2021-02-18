package dwiyanhartono.com.ksp3;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

class HolderPlanvisit extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView loan, buckettxt, bucketeomtxt, ciftxt, namatxt, ostxt, totaltunggakantxt, alamat;
    Button btninputvisit, btnhistory, btndetail, btnmaps;

    AppCompatCheckBox ck;
    ItemClickListner itemClickListener;
    public HolderPlanvisit(@NonNull View itemView) {
        super(itemView);

        buckettxt = (TextView) itemView.findViewById(R.id.buckettxt);
        bucketeomtxt = (TextView) itemView.findViewById(R.id.bucketeomtxt);
        ciftxt = (TextView) itemView.findViewById(R.id.nociftxt);
        loan = (TextView) itemView.findViewById(R.id.loantext);
        namatxt = (TextView) itemView.findViewById(R.id.namatxt);
        totaltunggakantxt = (TextView) itemView.findViewById(R.id.totaltunggakantxt);
        ostxt = (TextView) itemView.findViewById(R.id.ostxt);
        alamat = (TextView) itemView.findViewById(R.id.alamattxt);
        btninputvisit = (Button) itemView.findViewById(R.id.btninputkunjungan);
        btnhistory = (Button) itemView.findViewById(R.id.btnhistorycalpenanganan);
        btndetail = (Button) itemView.findViewById(R.id.btndetailinventory);
        btnmaps = (Button) itemView.findViewById(R.id.btnmaps);

        btninputvisit.setOnClickListener(this);
        btndetail.setOnClickListener(this);
        btnhistory.setOnClickListener(this);
        btnmaps.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListner itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
