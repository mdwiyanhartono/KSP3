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
import dwiyanhartono.com.ksp3.model.DataTesimage_1;

public class AdapterTesimageList extends RecyclerView.Adapter<HolderTesimageList> {

    String alamatusaha, dpd, loanid, email;
    private List<DataTesimage_1> assignment;
    private AdapterTesimageList.OnItemClickListener onItemClickListener;

    public AdapterTesimageList(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        assignment = new ArrayList<>();
    }


    @NonNull
    @Override
    public HolderTesimageList onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelimagelist, parent, false);
        return new HolderTesimageList(v);
    }

    @Override
    public void onBindViewHolder(final HolderTesimageList holder, final int position) {
        final DataTesimage_1 dm = assignment.get(position);

//        File image = new File(dm.getF_file_doc());
//        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//        Bitmap bitmap1 = null;
//        bitmap1 = BitmapFactory.decodeFile(image.getAbsolutePath(), bmOptions);

//        ByteArrayOutputStream baos = new  ByteArrayOutputStream();
//        bitmap1.compress(Bitmap.CompressFormat.JPEG,100, baos);
//        byte [] b = baos.toByteArray();
//        String temp = Base64.encodeToString(b, Base64.DEFAULT);
//
//
//
        byte[] decodedString1 = Base64.decode(dm.getF_file_doc(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString1, 0, decodedString1.length);

        holder.textimage1.setText(dm.getF_keterangan());
        holder.id.setText(dm.getF_id());
        holder.image1.setImageBitmap(getResizedBitmap(decodedByte,900));
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onButonupdate(holder.id.getText().toString(), holder.textimage1.getText().toString());
            }
        });
        holder.min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.edit.setVisibility(View.VISIBLE);
                holder.min.setVisibility(View.GONE);
                holder.textimage1.setEnabled(false);
                holder.ly2.setVisibility(View.GONE);

            }
        });


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.textimage1.setEnabled(true);
                holder.edit.setVisibility(View.GONE);
                holder.min.setVisibility(View.VISIBLE);
                holder.ly2.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return assignment.size();
    }

    // Untuk resize bitmap
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public void setData(List<DataTesimage_1> assignment) {
        this.assignment.clear();
        this.assignment.addAll(assignment);
        notifyDataSetChanged();
    }

    interface OnItemClickListener {

        void onButonupdate(String text, String toString);
    }
}
