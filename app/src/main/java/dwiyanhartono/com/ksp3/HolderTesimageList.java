package dwiyanhartono.com.ksp3;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

class HolderTesimageList extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView id;
    EditText textimage1;
    LinearLayout ly2 ;
    Button update;

    ImageView image1,edit,min;
    AppCompatCheckBox ck;
    ItemClickListner itemClickListener;
    public HolderTesimageList(@NonNull View itemView) {
        super(itemView);

        image1 = (ImageView)itemView.findViewById(R.id.imageview1);
        update = (Button)itemView.findViewById(R.id.btnupdate);
        ly2 = (LinearLayout)itemView.findViewById(R.id.ly2);
        edit = (ImageView)itemView.findViewById(R.id.edit);
        min = (ImageView)itemView.findViewById(R.id.minus);

        textimage1 = (EditText) itemView.findViewById(R.id.textimage);
        id = (TextView)itemView.findViewById(R.id.textid);

    }
    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListner itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
