package dwiyanhartono.com.ksp3;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.model.DataHistorical;

public class AdapterHistory extends RecyclerView.Adapter<HolderHistorycal> {

    String alamatusaha, dpd, loanid, email;
    private List<DataHistorical> assignment;
    private OnItemClickListener onItemClickListener;

    public AdapterHistory(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        assignment = new ArrayList<>();

    }

    @NonNull
    @Override
    public HolderHistorycal onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_historycal, parent, false);

        return new HolderHistorycal(v);

    }

    @Override
    public void onBindViewHolder(final HolderHistorycal holder, final int position) {
        final DataHistorical dm = assignment.get(position);

        holder.tgl.setText(dm.getTglvisit());
        holder.hasil.setText(dm.getKunjungan());

        final String bayar = dm.getTotalbayar();
        final String bertemu = dm.getBertemu();
        final String lokasi = dm.getLokasibertemu();
        final String karakter = dm.getKarakter();
        final String resume = dm.getResumeanggota();
        final String actionplan = dm.getActionplan();
        final String cif = dm.getCif();
        final String tanggalvisit= dm.getTglvisit();
        final String loanid = dm.getLoanid();
        final String agentid = dm.getAgentid();
        final String agentname = dm.getAgentname();
        final String dateplan = dm.getDateplan();
        final String code_image = dm.getCode_image();
        holder.setItemClickListener(new ItemClickListner() {
            @Override
            public void onItemClick(View v, int pos) {

                Intent intent = new Intent(v.getContext(), ActivityDetailHistorycal.class);
                intent.putExtra("hasil",holder.hasil.getText().toString());
                intent.putExtra("bayar",dm.getTotalbayar());
                intent.putExtra("bertemu",dm.getBertemu());
                intent.putExtra("lokasi",dm.getLokasibertemu());
                intent.putExtra("karakter",dm.getKarakter());
                intent.putExtra("resume",dm.getResumeanggota());
                intent.putExtra("actionplan",dm.getActionplan());
                intent.putExtra("cif",dm.getCif());
                intent.putExtra("tglvisit",dm.getTglvisit());
                intent.putExtra("loanid",dm.getLoanid());
                intent.putExtra("agentname",dm.getAgentname());
                intent.putExtra("agentid",dm.getAgentid());
                intent.putExtra("dateplan",dm.getDateplan());
                intent.putExtra("code_image",dm.getCode_image());

                v.getContext().startActivity(intent);
//                Toast.makeText(v.getContext(), dm.getKunjungan(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(v.getContext(), code_image, Toast.LENGTH_SHORT).show();
//                Toast.makeText(v.getContext(), agentname, Toast.LENGTH_SHORT).show();
//                Toast.makeText(v.getContext(), "a"+loanid, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return assignment.size();
    }

    interface OnItemClickListener {

    }

    public void setData(List<DataHistorical> assignment) {
        this.assignment.clear();
        this.assignment = assignment;
        notifyDataSetChanged();
    }
}
