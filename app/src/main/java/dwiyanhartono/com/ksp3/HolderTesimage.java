package dwiyanhartono.com.ksp3;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

class HolderTesimage extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView textimage1,texttype;

    ImageView image1,image2;
    AppCompatCheckBox ck;
    ItemClickListner itemClickListener;
    public HolderTesimage(@NonNull View itemView) {
        super(itemView);

        image1 = (ImageView)itemView.findViewById(R.id.imageview1);
//        image2 = (ImageView)itemView.findViewById(R.id.imageview2);
        textimage1 = (TextView)itemView.findViewById(R.id.textimage);
        texttype = (TextView)itemView.findViewById(R.id.type);

    }
    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListner itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
