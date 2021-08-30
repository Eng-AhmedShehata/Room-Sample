package com.ashehata.roomsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // to get all upcoming trips
        LiveData<List<Trip>> listLiveData = RoomDB.getTrips(this).getAllUpcoming();
        listLiveData.observe(this, new Observer<List<Trip>>() {
            @Override
            public void onChanged(List<Trip> trips) {
                Toast.makeText(MainActivity.this, trips.size() + "", Toast.LENGTH_SHORT).show();
                // pass data to the adapter
                // TODO
                //TripsAdapter tripsAdapter = new TripsAdapter(trips);
                // display to rv
                //recyclerView.setAdapter(productsAdapter);
            }
        });

        // to get other trips
        LiveData<List<Trip>> otherListLiveData = RoomDB.getTrips(this).getAllHistory();
        otherListLiveData.observe(this, new Observer<List<Trip>>() {
            @Override
            public void onChanged(List<Trip> trips) {
                Toast.makeText(MainActivity.this, trips.size() + "", Toast.LENGTH_SHORT).show();
                // TODO
                //TripsAdapter tripsAdapter = new TripsAdapter(trips);
                // display to rv
                //recyclerView.setAdapter(productsAdapter);
            }
        });
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> notes = new ArrayList<>();
                notes.add("sfd");
                // to insert item
                Executors.newSingleThreadExecutor().execute(() -> {
                    RoomDB.getTrips(MainActivity.this).insert(
                            new Trip(
                                    "ahmed",
                                    50L,
                                    60,
                                    TripState.UPCOMING.name(),
                                    TripType.ONE_WAY.name(),
                                    "from",
                                    "to",
                                    notes
                            )
                    );
                });
            }
        });




    }
}