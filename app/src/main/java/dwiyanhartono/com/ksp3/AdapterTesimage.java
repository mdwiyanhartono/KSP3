package dwiyanhartono.com.ksp3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.model.DataTesimage;

public class AdapterTesimage extends RecyclerView.Adapter<HolderTesimage> {

    String alamatusaha,dpd,loanid,email;
    private List<DataTesimage> assignment;

    public AdapterTesimage(TesImageActivity tesImageActivity) {
        assignment = new ArrayList<>();
    }


    @NonNull
    @Override
    public HolderTesimage onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelimage, parent, false);
        return new HolderTesimage(v);
    }

    @Override
    public void onBindViewHolder(final HolderTesimage holder, final int position) {
        final DataTesimage dm = assignment.get(position);

        byte[] decodedString1 = Base64.decode(dm.getF_file_doc(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString1,0, decodedString1.length);


        holder.textimage1.setText(dm.getF_keterangan());
        holder.texttype.setText(dm.getF_type());
        holder.image1.setImageBitmap(decodedByte);

    }

    @Override
    public int getItemCount() {
        return assignment.size();
    }

    public void setData(List<DataTesimage> assignment) {
        this.assignment.clear();
        this.assignment = assignment;
        notifyDataSetChanged();
    }
}
