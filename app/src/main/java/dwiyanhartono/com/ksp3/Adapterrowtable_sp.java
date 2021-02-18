package dwiyanhartono.com.ksp3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.model.DataSp;

public class Adapterrowtable_sp extends RecyclerView.Adapter<HolderRowtable_sp> {

    private List<DataSp> jaminan;

    public Adapterrowtable_sp(ActivityDetailHistorycal activityDetailHistorycal) {
        jaminan = new ArrayList<>();

    }

    @NonNull
    @Override
    public HolderRowtable_sp onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowtable_sp, parent, false);
        return new HolderRowtable_sp(v);
    }

    @Override
    public void onBindViewHolder(final HolderRowtable_sp holder, final int position) {
        final DataSp dm = jaminan.get(position);
        holder.spke.setText(dm.getSpke());
        holder.tglsp.setText(dm.getTglsp());
        holder.speke.setText(dm.getSpeke());
        holder.tglspe.setText(dm.getTglspeke());




    }

    @Override
    public int getItemCount() {
        return jaminan.size();
    }



    public void setData(List<DataSp> assignment) {
        this.jaminan.clear();
        this.jaminan = assignment;
        notifyDataSetChanged();
    }
}
