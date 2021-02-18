package dwiyanhartono.com.ksp3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.model.DataJaminan;

public class Adapterrowtable1 extends RecyclerView.Adapter<HolderRowtable1> {

    private List<DataJaminan> jaminan;

    public Adapterrowtable1() {
        jaminan = new ArrayList<>();

    }

    @NonNull
    @Override
    public HolderRowtable1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowtable, parent, false);
        return new HolderRowtable1(v);
    }

    @Override
    public void onBindViewHolder(final HolderRowtable1 holder, final int position) {
        final DataJaminan dm = jaminan.get(position);
        holder.jenis.setText(dm.getJaminan());
        holder.alamaat.setText(dm.getLocation());
        holder.value.setText(dm.getValue());
        holder.no.setText(String.valueOf(dm.getNumber()));
    }

    @Override
    public int getItemCount() {
        return jaminan.size();
    }


    public void setData(List<DataJaminan> assignment) {
        this.jaminan.clear();
        this.jaminan = assignment;
        notifyDataSetChanged();
    }
}
