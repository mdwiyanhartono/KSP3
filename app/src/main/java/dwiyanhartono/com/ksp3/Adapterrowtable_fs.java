package dwiyanhartono.com.ksp3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.model.DataFs;

public class Adapterrowtable_fs extends RecyclerView.Adapter<HolderRowtable_fs> {

    private List<DataFs> jaminan;

    public Adapterrowtable_fs(ActivityDetailHistorycal activityDetailHistorycal) {
        jaminan = new ArrayList<>();

    }

    @NonNull
    @Override
    public HolderRowtable_fs onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowtable_fc, parent, false);
        return new HolderRowtable_fs(v);
    }

    @Override
    public void onBindViewHolder(final HolderRowtable_fs holder, final int position) {
        final DataFs dm = jaminan.get(position);
        holder.pf.setText(dm.getPfs());
        holder.stsfs.setText(dm.getStatusfs());
        holder.apf.setText(dm.getApfs());
        holder.tglapf.setText(dm.getTglfs());




    }

    @Override
    public int getItemCount() {
        return jaminan.size();
    }



    public void setData(List<DataFs> assignment) {
        this.jaminan.clear();
        this.jaminan = assignment;
        notifyDataSetChanged();
    }
}
