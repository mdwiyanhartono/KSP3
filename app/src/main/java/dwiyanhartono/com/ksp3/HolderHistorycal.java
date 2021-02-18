package dwiyanhartono.com.ksp3;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

class HolderHistorycal extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView tgl, hasil;
    Button btninputvisit, btnhistory, btndetail, btnmaps;

    AppCompatCheckBox ck;
    ItemClickListner itemClickListener;
    public HolderHistorycal(@NonNull View itemView) {
        super(itemView);
        hasil = (TextView)itemView.findViewById(R.id.hasiltxt);
        tgl = (TextView)itemView.findViewById(R.id.tgltxt);

        itemView.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListner itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
