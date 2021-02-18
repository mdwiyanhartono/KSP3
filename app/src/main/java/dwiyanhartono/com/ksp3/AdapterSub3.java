package dwiyanhartono.com.ksp3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.model.Datasub3_1;

public class AdapterSub3 extends RecyclerView.Adapter<HolderSub3> {

    String alamatusaha,dpd,loanid,email;
    private List<Datasub3_1> assignment;
//    private OnItemClickListener onItemClickListener;

    public AdapterSub3(SubHalaman3 subHalaman3) {
//        this.onItemClickListener = onItemClickListener;
        assignment = new ArrayList<>();

    }

    @NonNull
    @Override
    public HolderSub3 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelsubhalaman3, parent, false);
        return new HolderSub3(v);
    }

    @Override
    public void onBindViewHolder(final HolderSub3 holder, final int position) {
        final Datasub3_1 dm = assignment.get(position);
        holder.namanasabah.setText(dm.getNamanasabah());
        holder.cif.setText(dm.getCif());
//        holder.loan.setText(dm.getLoanid());
        holder.bertemu.setText(dm.getBertemu());
        holder.lokasibertemu.setText(dm.getLokasibertemu());
        holder.actionplan.setText(dm.getActionplan());
        holder.hasilkunjungan.setText(dm.getHasilkunjungan());

    }

    @Override
    public int getItemCount() {
        return assignment.size();
    }

    public void setData(List<Datasub3_1> assignment) {
        this.assignment.clear();
        this.assignment.addAll(assignment);
        notifyDataSetChanged();
    }
}
