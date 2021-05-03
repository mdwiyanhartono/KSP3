package dwiyanhartono.com.ksp3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.model.Dataptp;

public class AdapterPtptoday extends RecyclerView.Adapter<HolderPTP> {

    String alamatusaha,dpd,loanid,email;
    private List<Dataptp> assignment;

    public AdapterPtptoday(ActivityPTP activityPTP) {
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
        final Dataptp dm = assignment.get(position);
        holder.namaanggota.setText(dm.getNamaanggota());
        holder.cif.setText(dm.getCif());
        holder.loan.setText(dm.getLoanid());
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



    public void setData(List<Dataptp> assignment) {
        this.assignment.clear();
        this.assignment = assignment;
        notifyDataSetChanged();
    }
}
