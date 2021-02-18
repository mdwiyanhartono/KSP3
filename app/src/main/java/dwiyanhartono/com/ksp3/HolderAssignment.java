package dwiyanhartono.com.ksp3;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

class HolderAssignment extends RecyclerView.ViewHolder implements View.OnClickListener  {
    TextView buckettxt, bucketeomtxt, ciftxt, namatxt, ostxt, totaltunggakantxt, alamat;
    AppCompatCheckBox ck;
    Button btninputvisit, btnhistory, btndetail, btnmaps;
    ItemClickListner itemClickListener;
    ImageView pending,ceklist;
    public HolderAssignment(@NonNull View itemView) {
        super(itemView);

        buckettxt = itemView.findViewById(R.id.buckettxt);
        bucketeomtxt = itemView.findViewById(R.id.bucketeomtxt);
        ciftxt = itemView.findViewById(R.id.nociftxt);
        namatxt = itemView.findViewById(R.id.namatxt);
        totaltunggakantxt = itemView.findViewById(R.id.totaltunggakantxt);
        ostxt = itemView.findViewById(R.id.ostxt);
        alamat = itemView.findViewById(R.id.alamattxt);
        ck = itemView.findViewById(R.id.ck);
        btnhistory = itemView.findViewById(R.id.btnhistorycalpenanganan);
        btndetail = itemView.findViewById(R.id.btndetailinventory);
        btnmaps = itemView.findViewById(R.id.btnmaps);
        pending = itemView.findViewById(R.id.pending);
        ceklist = itemView.findViewById(R.id.approve);


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
