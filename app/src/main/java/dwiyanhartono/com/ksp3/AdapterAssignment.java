package dwiyanhartono.com.ksp3;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.model.DataModel;

public class AdapterAssignment extends RecyclerView.Adapter<HolderAssignment> {

    private List<DataModel> assignment;
    private OnItemClickListener onItemClickListener;

    public AdapterAssignment(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        assignment = new ArrayList<>();

    }

    @NonNull
    @Override
    public HolderAssignment onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_assignment, parent, false);
        return new HolderAssignment(v);
    }

    @Override
    public void onBindViewHolder(final HolderAssignment holder, final int position) {
        final DataModel dm = assignment.get(position);
        holder.buckettxt.setText(dm.getBucket());
        holder.bucketeomtxt.setText(dm.getBucketeom());
        holder.ciftxt.setText(dm.getCif());
        holder.namatxt.setText(dm.getNama());
        holder.ostxt.setText(dm.getOs());
        holder.totaltunggakantxt.setText(dm.getTotaltunggakan());
        holder.alamat.setText(dm.getAlamat());
        String datenow = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        if (dm.getApproved().equals("2")  && dm.getTglrequest().equals(datenow)) {
            holder.ck.setVisibility(View.GONE);
            holder.pending.setVisibility(View.VISIBLE);
        } else if (dm.getApproved().equals("1") && dm.getAction().equals("0")) {
            holder.ck.setOnCheckedChangeListener(null);
            holder.ck.setChecked(dm.isChecked());
            holder.ck.setVisibility(View.GONE);
            holder.pending.setVisibility(View.GONE);
            holder.ceklist.setVisibility(View.VISIBLE);
//            holder.ck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    Log.d("Data Posisiton", position + "");
//                    onItemClickListener.onItemClick(dm.getCif(), isChecked);
//                    dm.setChecked(isChecked);
//                }
//            });
        } else {
            holder.ck.setOnCheckedChangeListener(null);
            holder.ck.setChecked(dm.isChecked());
            holder.pending.setVisibility(View.GONE);
            holder.ck.setVisibility(View.VISIBLE);
            holder.ck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Log.d("Data Posisiton", position + "");
                    onItemClickListener.onItemClick(dm.getCif(), isChecked);
                    dm.setChecked(isChecked);
                }
            });
        }

        holder.btndetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onButonDetailClick(dm.getCif());
            }
        });
        holder.btnhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onButonHistoryClick(dm.getBucketeom(), dm.getCif());
            }
        });
        holder.btnmaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onButonMapsClick(dm.getCif(), dm.getAlamat());
            }
        });

    }

    @Override
    public int getItemCount() {
        return assignment.size();
    }

    interface OnItemClickListener {
        void onItemClick(String id, boolean isChecked);

        void onItemClick(String id);

        void onButonDetailClick(String cif);

        void onButonVisitClick(String cif, String nama, String alamat, String alamatusaha, String email, String bucketeom, String dpd, String loanid);

        void onButonHistoryClick(String loanid, String cif);

        void onButonMapsClick(String cif, String alamat);
    }

    public void setData(List<DataModel> assignment) {
        this.assignment.clear();
        this.assignment = assignment;
        notifyDataSetChanged();
    }
}
