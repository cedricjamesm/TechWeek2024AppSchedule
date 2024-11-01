package model;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prjtechweekapp_finalized.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.RowViewHolder>{

    private ArrayList<EventItem> eventList;
    private Context context;
    private Button btnDetails;

    public EventAdapter(ArrayList<EventItem> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }

    // Inner class
    public class RowViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDescription, tvPresenter, tvTime, tvLanguage, tvMode, tvHost;
        private ImageView imgDisplay;
        private Button btnDetails;

        public RowViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPresenter = itemView.findViewById(R.id.tvPresenter);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvLanguage = itemView.findViewById(R.id.tvLanguage);
            tvMode = itemView.findViewById(R.id.tvMode);
            imgDisplay = itemView.findViewById(R.id.imgDisplay);
            btnDetails = itemView.findViewById(R.id.btnDetails);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvHost = itemView.findViewById(R.id.tvHost);

        }
    }

    @NonNull
    @Override
    public RowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View oneEvent;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        oneEvent = inflater.inflate(R.layout.rv_event, parent, false);
        return new RowViewHolder(oneEvent);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.RowViewHolder holder, int position) {
        EventItem event = eventList.get(position);
        holder.tvTitle.setText(event.getTitle());
        holder.tvDescription.setText(event.getDescription());
        holder.tvPresenter.setText("Presenter: " + event.getPresenter());
        holder.tvTime.setText("Time: " + event.getTime());
        holder.tvLanguage.setText("Language: " + event.getLanguage());
        holder.tvMode.setText("Mode: " + event.getMode());
        holder.tvHost.setText("Host: " + event.getHost());

        //if cancelled is set to yes, then disable the button
        if("yes".equalsIgnoreCase(event.getCancel())){
            holder.btnDetails.setEnabled(false);
            holder.btnDetails.setAlpha(0.3f); //set opacity very light if disabled
            holder.btnDetails.setText("No Details");
        } else {
            holder.btnDetails.setEnabled(true);
            holder.btnDetails.setAlpha(1.0f);
        }

        //display image onto the main scrollview
        String imageUrl = event.getPhoto(); // Assuming you have a method to get the photo URL
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.get().load(imageUrl).into(holder.imgDisplay); // Replace holder.imageView with your actual ImageView reference
        } else {
            // Optionally set a placeholder or handle the error
            holder.imgDisplay.setImageResource(R.drawable.ic_launcher_foreground); // Replace with your placeholder
        }

        holder.btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEventDetail(eventList.get(position));
            }
        });

        Log.d("EventAdapter", "Event: " + event.getTitle() + ", Cancel: " + event.getCancel());
    }

    private void showEventDetail(EventItem eventItem) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.layout_detail);
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        TextView tvDetails = dialog.findViewById(R.id.tvDetails);
        ImageView pPhoto = dialog.findViewById(R.id.pPhoto);

        String detail = eventItem.getBiography();
        String image = eventItem.getPhoto();

        //display same image onto the context as well
        if (image != null && !image.isEmpty()) { //if else statement for when the photo is empty, put a placeholder instead
            Picasso.get().load(image).into(pPhoto);

        } else {
            // Load a placeholder image or handle the case when the path is empty
            pPhoto.setImageResource(R.drawable.ic_launcher_foreground);
        }

        tvDetails.setText(detail);
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
