package dwiyanhartono.com.ksp3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.model.Datakp_1;

public class AdapterSub6 extends RecyclerView.Adapter<HolderPTP> {

    String alamatusaha,dpd,loanid,email;
    private List<Datakp_1> assignment;
//    private OnItemClickListener onItemClickListener;

    public AdapterSub6(SubHalaman6 subHalaman6) {
//        this.onItemClickListener = onItemClickListener;
        assignment = new ArrayList<>();

    }

    @NonNull
    @Override
    public HolderPTP onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelptp, parent, false);
        return new HolderPTP(v);
    }

    @Override
    public void onBindViewHolder(final HolderPTP holder, final int position) {
        final Datakp_1 dm = assignment.get(position);
        holder.namanasabah.setText(dm.getNamanasabah());
        holder.cif.setText(dm.getCif());
//        holder.loan.setText(dm.getLoanid());
        holder.bertemu.setText(dm.getBertemu());
        holder.lokasibertemu.setText(dm.getLokasibertemu());
        holder.actionplan.setText(dm.getActionplan());
        holder.hasilkunjungan.setText(dm.getHasilkunjungan());
        holder.tanggalactionplan.setText(dm.getTanggalaction());
        holder.nominal.setText(dm.getNominal());
    }

    @Override
    public int getItemCount() {
        return assignment.size();
    }

    public void setData(List<Datakp_1> assignment) {
        this.assignment.clear();
        this.assignment.addAll(assignment);
        notifyDataSetChanged();
    }
}