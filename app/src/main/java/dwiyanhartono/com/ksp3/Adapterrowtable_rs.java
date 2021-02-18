package dwiyanhartono.com.ksp3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.model.DataRs;

public class Adapterrowtable_rs extends RecyclerView.Adapter<HolderRowtable_rs> {

    private List<DataRs> jaminan;

    public Adapterrowtable_rs(ActivityDetailHistorycal activityDetailHistorycal) {
        jaminan = new ArrayList<>();

    }

    @NonNull
    @Override
    public HolderRowtable_rs onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowtable_restrukturisasi, parent, false);
        return new HolderRowtable_rs(v);
    }

    @Override
    public void onBindViewHolder(final HolderRowtable_rs holder, final int position) {
        final DataRs dm = jaminan.get(position);
        holder.rske.setText(dm.getRestrukturisasike());
        holder.gp.setText(dm.getGraceperiode());
        holder.jkgp.setText(dm.getJangkawaktugp());
    }

    @Override
    public int getItemCount() {
        return jaminan.size();
    }



    public void setData(List<DataRs> assignment) {
        this.jaminan.clear();
        this.jaminan = assignment;
        notifyDataSetChanged();
    }
}
