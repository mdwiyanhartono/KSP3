package dwiyanhartono.com.ksp3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.model.Datasub4_1;

public class AdapterSub4 extends RecyclerView.Adapter<HolderSub4> {

    String alamatusaha,dpd,loanid,email;
    private List<Datasub4_1> assignment;
//    private OnItemClickListener onItemClickListener;

    public AdapterSub4(SubHalaman4 subHalaman4) {
//        this.onItemClickListener = onItemClickListener;
        assignment = new ArrayList<>();

    }

    @NonNull
    @Override
    public HolderSub4 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelsubhalaman4, parent, false);
        return new HolderSub4(v);
    }

    @Override
    public void onBindViewHolder(final HolderSub4 holder, final int position) {
        final Datasub4_1 dm = assignment.get(position);
        holder.namaanggota.setText(dm.getNamaanggota());
        holder.cif.setText(dm.getCif());
        holder.loan.setText(dm.getLoanid());
        holder.bertemu.setText(dm.getBertemu());
        holder.lokasibertemu.setText(dm.getLokasibertemu());
        holder.actionplan.setText(dm.getActionplan());
        holder.hasilkunjungan.setText(dm.getHasilkunjungan());
    }

    @Override
    public int getItemCount() {
        return assignment.size();
    }

    public void setData(List<Datasub4_1> assignment) {
        this.assignment.clear();
        this.assignment.addAll(assignment);
        notifyDataSetChanged();
    }
}