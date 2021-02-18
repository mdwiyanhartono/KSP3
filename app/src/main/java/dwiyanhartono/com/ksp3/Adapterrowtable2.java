package dwiyanhartono.com.ksp3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.model.DataFasilitas;

public class Adapterrowtable2 extends RecyclerView.Adapter<HolderRowtable2> {

    private List<DataFasilitas> fasilitas;

    public Adapterrowtable2( ) {
        fasilitas = new ArrayList<>();

    }

    @NonNull
    @Override
    public HolderRowtable2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowtable2, parent, false);
        return new HolderRowtable2(v);
    }

    @Override
    public void onBindViewHolder(final HolderRowtable2 holder, final int position) {
        final DataFasilitas dm = fasilitas.get(position);
        holder.fasilitas.setText(dm.getFasilitas());
        holder.plafond.setText(dm.getPlafon());
        holder.booked.setText(dm.getBooked());
        holder.os.setText(dm.getOs());
        holder.exppros.setText(dm.getExpprocess());
        holder.expdate.setText(dm.getExpdate());
        holder.flag.setText(dm.getFlag());




    }

    @Override
    public int getItemCount() {
        return fasilitas.size();
    }



    public void setData(List<DataFasilitas> assignment) {
        this.fasilitas.clear();
        this.fasilitas = assignment;
        notifyDataSetChanged();
    }
}
