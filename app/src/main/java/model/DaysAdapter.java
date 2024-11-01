package model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prjtechweekapp_finalized.DayActivity;
import com.example.prjtechweekapp_finalized.R;
import com.google.android.material.button.MaterialButton;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.DaysViewHolder> {

    private final Context context;
    private final String[] days;

    public DaysAdapter(Context context, String[] days) {
        this.context = context;
        this.days = days;
    }

    @NonNull
    @Override
    public DaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_day_card, parent, false);
        return new DaysViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DaysViewHolder holder, int position) {
        String day = days[position];
        holder.tvDayName.setText(day);

        // Set click listener for the button
        holder.btnRedirect.setOnClickListener(v -> {
            Intent intent = new Intent(context, DayActivity.class);
            intent.putExtra("selectedDay", day.toLowerCase());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return days.length;
    }

    public static class DaysViewHolder extends RecyclerView.ViewHolder {
        TextView tvDayName;
        MaterialButton btnRedirect;

        public DaysViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDayName = itemView.findViewById(R.id.tvDayName);
            btnRedirect = itemView.findViewById(R.id.btnRedirect);
        }
    }
}