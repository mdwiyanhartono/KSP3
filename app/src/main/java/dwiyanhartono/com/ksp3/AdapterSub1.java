package dwiyanhartono.com.ksp3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dwiyanhartono.com.ksp3.model.Datasub1_1;

public class AdapterSub1 extends RecyclerView.Adapter<HolderSub1> {

    String alamatusaha,dpd,loanid,email;
    private List<Datasub1_1> assignment;
//    private OnItemClickListener onItemClickListener;

    public AdapterSub1(SubHalaman1 subHalaman1) {
//        this.onItemClickListener = onItemClickListener;
        assignment = new ArrayList<>();

    }

    @NonNull
    @Override
    public HolderSub1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelsubhalaman, parent, false);
        return new HolderSub1(v);
    }

    @Override
    public void onBindViewHolder(final HolderSub1 holder, final int position) {
        final Datasub1_1 dm = assignment.get(position);
        holder.namanasabah.setText(dm.getNamanasabah());
        holder.cif.setText(dm.getCif());
//        holder.loan.setText(dm.getLoanid());
        holder.bertemu.setText(dm.getBertemu());
        holder.lokasibertemu.setText(dm.getLokasibertemu());
        holder.actionplan.setText(dm.getActionplan());
        holder.hasilkunjungan.setText(dm.getHasilkunjungan());
//        holder.buckettxt.setText(dm.getLokasibertemu());
//        holder.bucketeomtxt.setText(dm.getLoanid());
//        holder.ciftxt.setText(dm.getCif());
//        holder.namatxt.setText(dm.getNamanasabah());
//        holder.ostxt.setText(dm.getActionplan());
//        holder.totaltunggakantxt.setText(dm.getHasilkunjungan());
//        holder.alamat.setText(dm.getBertemu());


//        holder.btninputvisit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickListener.onButonVisitClick(holder.ciftxt.getText().toString(),holder.namatxt.getText().toString(),holder.alamat.getText().toString(),alamatusaha,email,holder.bucketeomtxt.getText().toString(),dpd,loanid);
//            }
//        });
//        holder.btndetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickListener.onButonDetailClick(dm.getAcctno());
//            }
//        });
//        holder.btnhistory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickListener.onButonHistoryClick(dm.getCif());
//            }
//        });
//        holder.btnmaps.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickListener.onButonMapsClick(holder.ciftxt.getText().toString(),holder.alamat.getText().toString());
//            }
//        });
//

    }

    @Override
    public int getItemCount() {
        return assignment.size();
    }

//    @Override
//    public int getItemCount() {
//        return assignment.size();
//    }
//
//    interface OnItemClickListener {
//        void onItemClick(String id);
//
//        void onButonDetailClick(String cif);
//
//        void onButonVisitClick(String cif, String toString, String alamatusaha, String email, String string, String dpd, String s, String loanid);
//
//        void onButonHistoryClick(String s);
//
//        void onButonMapsClick(String cif, String alamat);
//    }

    public void setData(List<Datasub1_1> assignment) {
        this.assignment.clear();
        this.assignment.addAll(assignment);
        notifyDataSetChanged();
    }
}
