package dwiyanhartono.com.ksp3;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.model.Dataptp_1;

public class AdapterSub5 extends RecyclerView.Adapter<HolderSub5> {

    String alamatusaha,dpd,loanid,email;
    private List<Dataptp_1> assignment;

    private OnItemClickListener onItemClickListener;

    public AdapterSub5(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        assignment = new ArrayList<>();


        Context c;

    }

    @NonNull
    @Override
    public HolderSub5 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelsubhalaman5, parent, false);
        return new HolderSub5(v);
    }

    @Override
    public void onBindViewHolder(final HolderSub5 holder, final int position) {
        final Dataptp_1 dm = assignment.get(position);
        holder.namanasabah.setText(dm.getNamanasabah());
        holder.cif.setText(dm.getCif());
//        holder.loan.setText(dm.getLoanid());
        holder.bertemu.setText(dm.getBertemu());
        holder.lokasibertemu.setText(dm.getLokasibertemu());
        holder.actionplan.setText(dm.getActionplan());
        holder.hasilkunjungan.setText(dm.getHasilkunjungan());
        holder.tanggalactionplan.setText(dm.getTanggalaction());
        holder.nominal.setText(dm.getNominal());

        holder.ck.setOnCheckedChangeListener(null);
        holder.ck.setChecked(dm.isChecked());
        holder.ck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("Data Posisiton", position+"");
                onItemClickListener.onItemClick(dm.getCif(), isChecked);
                dm.setChecked(isChecked);
            }
        });


    }

    @Override
    public int getItemCount() {
        return assignment.size();
    }



    public void setData(List<Dataptp_1> assignment) {
        this.assignment.clear();
        this.assignment.addAll(assignment);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(String cif, boolean isChecked);
    }
}
