package com.example.prjtechweekapp_finalized;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import model.EventAdapter;
import model.EventItem;

public class DayActivity extends AppCompatActivity implements ValueEventListener, View.OnClickListener {

    ArrayList<EventItem> eventList;
    RecyclerView rvEvents;
    EventAdapter adapter;
    Context context;
    String selectedDay;
    Button btnBack;

    DatabaseReference techWeekDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_day);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initialize();
    }

    private void initialize() {
        //get the day that corresponds to what button the user pressed
        selectedDay = getIntent().getStringExtra("selectedDay");
        techWeekDatabase = FirebaseDatabase.getInstance().getReference("event");
        techWeekDatabase.addValueEventListener(this);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
    }

    private void initializeRecyclerView(){
        rvEvents = findViewById(R.id.rvEvents);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvEvents.setLayoutManager(layoutManager);
        rvEvents.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new EventAdapter(eventList, this);
        rvEvents.setAdapter(adapter);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if (snapshot.exists()) {
            Toast.makeText(this, "Data exists", Toast.LENGTH_LONG).show();

            // Initialize the event list before populating
            eventList = new ArrayList<EventItem>();

            try {
                DataSnapshot daySnap = snapshot.child("event_detail").child(selectedDay);
                TextView tvDay = findViewById(R.id.tvDay);
                TextView tvTheme = findViewById(R.id.tvTheme);
                TextView tvMasterCeremony = findViewById(R.id.tvMasterCeremony);
                tvDay.setText("Day: " + daySnap.child("day").getValue().toString());
                tvTheme.setText("Theme: " + daySnap.child("theme").getValue().toString());
                tvMasterCeremony.setText("Master Ceremony: " + daySnap.child("masterceremony").getValue().toString());

                // Populate the event list
                for (DataSnapshot eventSnap : daySnap.getChildren()) {
                    if (eventSnap.exists()) {
                        String title = (String) eventSnap.child("title").getValue();
                        String description = (String) eventSnap.child("description").getValue();
                        String time = (String) eventSnap.child("time").getValue();
                        String language = (String) eventSnap.child("language").getValue();
                        String speaker = (String) eventSnap.child("presenter").getValue();
                        String mode = (String) eventSnap.child("mode").getValue();
                        String photo = (String) eventSnap.child("photo").getValue();
                        String host = (String) eventSnap.child("host").getValue();
                        String cancel = (String) eventSnap.child("cancel").getValue();

                        EventItem event = new EventItem(title, description, speaker, time, language, mode, photo, description, host, cancel);
                        if (title != null)
                            eventList.add(event);
                    } else {
                        Log.d("RESULT", "Event does not exist");
                    }
                }

                // Now that we have data, initialize the RecyclerView
                initializeRecyclerView();

            } catch (Exception e) {
                Log.d("RESULT", e.getMessage());
            }
        } else {
            Toast.makeText(this, "No data found for this day.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }

    @Override
    public void onClick(View view) {
        finish();
    }
}