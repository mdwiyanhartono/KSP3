package dwiyanhartono.com.ksp3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.model.Dataplanvisit;

public class AdapterPlanvisit extends RecyclerView.Adapter<HolderPlanvisit> {

    String alamatusaha,dpd,loanid,email,cif,norek;
    private List<Dataplanvisit> assignment;
    private OnItemClickListener onItemClickListener;

    public AdapterPlanvisit(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        assignment = new ArrayList<>();

    }

    @NonNull
    @Override
    public HolderPlanvisit onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelplanvisit, parent, false);
        return new HolderPlanvisit(v);
    }

    @Override
    public void onBindViewHolder(final HolderPlanvisit holder, final int position) {
        final Dataplanvisit dm = assignment.get(position);
        holder.buckettxt.setText(dm.getBucket());
        holder.bucketeomtxt.setText(dm.getBucketeom());
        holder.ciftxt.setText(dm.getCif());
        holder.namatxt.setText(dm.getNama());
        holder.ostxt.setText(dm.getOs());
        holder.totaltunggakantxt.setText(dm.getTotaltunggakan());
        holder.alamat.setText(dm.getAlamat());
        holder.loan.setText(dm.getLoanid());
        norek = dm.getAcctno();
        alamatusaha = dm.getAlamatusaha();
        email = dm.getEmail();
        dpd = dm.getDpd();
        loanid = dm.getLoanid();
        cif = dm.getCif();

        holder.btninputvisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onButonVisitClick(norek,holder.ciftxt.getText().toString(),holder.namatxt.getText().toString(),holder.alamat.getText().toString(),dm.getAlamatusaha(),dm.getEmail(),holder.bucketeomtxt.getText().toString(),dm.getDpd(),holder.loan.getText().toString(),dm.getTotaltunggakan(),dm.getAngsuran(),dm.getNotlp());
            }
        });
        holder.btndetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onButonDetailClick(dm.getCif());
            }
        });
        holder.btnhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onButonHistoryClick(holder.loan.getText().toString(),holder.ciftxt.getText().toString());
            }
        });
        holder.btnmaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onButonMapsClick(holder.ciftxt.getText().toString(),holder.alamat.getText().toString());
            }
        });


    }

    @Override
    public int getItemCount() {
        return assignment.size();
    }

    interface OnItemClickListener {
        void onItemClick(String id);

        void onButonDetailClick(String cif);

        void onButonVisitClick(String norek ,String cif, String nama, String alamatusaha, String email, String string, String dpd, String s, String loanid, String totaldue, String angsuran,String notlp);

        void onButonHistoryClick(String loan, String cif);

        void onButonMapsClick(String cif, String alamat);
    }

    public void setData(List<Dataplanvisit> assignment) {
        this.assignment.clear();
        this.assignment.addAll(assignment);
        notifyDataSetChanged();
    }
}
