package isfaaghyth.app.multipleselected.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import isfaaghyth.app.multipleselected.R;
import isfaaghyth.app.multipleselected.bean.Time;
import isfaaghyth.app.multipleselected.ui.TimeListener;

/**
 * Created by isfaaghyth on 9/8/18.
 * github: @isfaaghyth
 */

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.Holder> {

    private Context context;
    private List<Time> times;
    private TimeListener listener;

    public TimeAdapter(Context context, List<Time> times) {
        this.context = context;
        this.times = times;
    }

    public void setListener(TimeListener listener) {
        this.listener = listener;
    }

    @Override public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @Override public void onBindViewHolder(Holder holder, int position) {
        final Time time = times.get(position);
        holder.txtDayName.setText(time.getDayName());
        for (final Integer eachTime: time.getHours()) {
            Button bt = new Button(context);
            bt.setText(String.valueOf(eachTime));
            bt.setBackgroundColor(Color.WHITE);
            bt.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            holder.vgTime.addView(bt);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onTimeClicked(v, time.getDayName(), eachTime);
                }
            });
        }
    }

    @Override public int getItemCount() {
        return times.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private TextView txtDayName;
        private ViewGroup vgTime;

        public Holder(View itemView) {
            super(itemView);
            txtDayName = itemView.findViewById(R.id.txt_day_name);
            vgTime = itemView.findViewById(R.id.vg_time);
        }
    }
}
