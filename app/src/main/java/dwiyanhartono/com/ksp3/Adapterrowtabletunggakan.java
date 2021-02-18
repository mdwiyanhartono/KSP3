package dwiyanhartono.com.ksp3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.model.DataTunggakanLocal;

public class Adapterrowtabletunggakan extends RecyclerView.Adapter<HolderRowtabletunggakan> {

    private List<DataTunggakanLocal> tunggakan;

    public Adapterrowtabletunggakan( ) {
        tunggakan = new ArrayList<>();

    }

    @NonNull
    @Override
    public HolderRowtabletunggakan onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowtabletunggakan2, parent, false);
        return new HolderRowtabletunggakan(v);
    }

    @Override
    public void onBindViewHolder(final HolderRowtabletunggakan holder, final int position) {
        final DataTunggakanLocal dm = tunggakan.get(position);

        holder.flid.setText(dm.getID());
        holder.flpi.setText(dm.getPD_ID());
        holder.fltd.setText(dm.getTD());
        holder.flit.setText(dm.getINSTALMENT_DATE());
        holder.fldp.setText(dm.getDUE_PR());
        holder.fldi.setText(dm.getDUE_IN());
        holder.fldc.setText(dm.getDUE_CH());
        holder.flndo.setText(dm.getNO_DAYS_OD());
//        holder.fldc.setText(dm.getFlag());




    }

    @Override
    public int getItemCount() {
        return tunggakan.size();
    }

    public void setData(List<DataTunggakanLocal> assignment) {
        this.tunggakan.clear();
        this.tunggakan.addAll(assignment);
        notifyDataSetChanged();
    }
}
