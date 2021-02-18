package dwiyanhartono.com.ksp3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.model.Dataptp;
import dwiyanhartono.com.ksp3.model.Datasettlement;

public class AdapterSettlement extends RecyclerView.Adapter<HolderSettlement> {

    String alamatusaha,dpd,loanid,email;
    private List<Datasettlement> settlement;

    public AdapterSettlement(ActivityPTP activityPTP) {
//        this.onItemClickListener = onItemClickListener;
        settlement = new ArrayList<>();

    }

    @NonNull
    @Override
    public HolderSettlement onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelsettlement, parent, false);
        return new HolderSettlement(v);
    }

    public void onBindViewHolder(final HolderSettlement holder, final int position) {
        final Datasettlement dm = settlement.get(position);
        holder.namanasabah.setText(dm.getNamanasabah());
        holder.cif.setText(dm.getCif());
        holder.nominal.setText(dm.getNominal());
        holder.tunggakan.setText(dm.getTunggakan());
        holder.noref.setText(dm.getNorefrence());

    }
    @Override
    public int getItemCount() {
        return settlement.size();
    }

    public void setData(List<Datasettlement> settlement) {
        this.settlement.clear();
        this.settlement = settlement;
        notifyDataSetChanged();
    }
}
