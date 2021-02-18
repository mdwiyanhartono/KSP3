package dwiyanhartono.com.ksp3;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

class SpinnerAdapter2 extends BaseAdapter {
    private List<String> Isdata2;
    private Activity activity;
    private LayoutInflater inflater;

    public SpinnerAdapter2(List<String> isdata, Activity activity) {
        Isdata2 = isdata;
        this.activity = activity;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return Isdata2.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null)
            view = inflater.inflate(R.layout.spinner2, null);
        TextView tv = (TextView) view.findViewById(R.id.textview);
        tv.setText(Isdata2.get(position));
        return view;
    }
}
