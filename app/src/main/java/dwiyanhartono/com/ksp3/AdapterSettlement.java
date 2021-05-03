package dwiyanhartono.com.ksp3;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.model.Dataptp;
import dwiyanhartono.com.ksp3.model.Datasettlement;

public class AdapterSettlement extends RecyclerView.Adapter<HolderSettlement> {

    String alamatusaha,dpd,loanid,email;
    private List<Datasettlement> settlement;
    private OnItemClickListener onItemClickListener;

    public AdapterSettlement(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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
        holder.namaanggota.setText(dm.getNamaanggota());
        holder.cif.setText(dm.getCif());
        holder.nominal.setText(dm.getNominal());
        holder.LD.setText(dm.getLd());
        holder.norek.setText(dm.getNorek());
        holder.ck.setOnCheckedChangeListener(null);
        holder.ck.setChecked(dm.isChecked());
        holder.ck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("Data Posisiton", position + "");
                onItemClickListener.onItemClick(dm.getId(), isChecked);
                dm.setChecked(isChecked);
            }
        });

    }

    interface OnItemClickListener {
        void onItemClick(String id, boolean isChecked);
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
