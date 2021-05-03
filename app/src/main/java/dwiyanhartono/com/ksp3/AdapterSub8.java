package dwiyanhartono.com.ksp3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.model.Datasub8_1;

public class AdapterSub8 extends RecyclerView.Adapter<HolderSub8> {

    String alamatusaha,dpd,loanid,email;
    private List<Datasub8_1> assignment;
//    private OnItemClickListener onItemClickListener;

    public AdapterSub8(SubHalaman8 subHalaman8) {
//        this.onItemClickListener = onItemClickListener;
        assignment = new ArrayList<>();

    }

    @NonNull
    @Override
    public HolderSub8 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelsubhalaman8, parent, false);
        return new HolderSub8(v);
    }

    @Override
    public void onBindViewHolder(final HolderSub8 holder, final int position) {
        final Datasub8_1 dm = assignment.get(position);
        holder.namaanggota1.setText(dm.getNamaanggota());
        holder.cif1.setText(dm.getCif());
//        holder.loan.setText(dm.getLoanid());
//        holder.jenisfasilits.setText(dm.getJenisfasilitas());
        holder.namaproduk.setText(dm.getNamaproduk());
    }

    @Override
    public int getItemCount() {
        return assignment.size();
    }

    public void setData(List<Datasub8_1> assignment) {
        this.assignment.clear();
        this.assignment.addAll(assignment);
        notifyDataSetChanged();
    }
}