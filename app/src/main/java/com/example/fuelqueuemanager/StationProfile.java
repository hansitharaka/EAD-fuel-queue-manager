package com.example.fuelqueuemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StationProfile extends AppCompatActivity {

    Button editStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.Theme_FuelQueueManager);
        setContentView(R.layout.activity_station_profile);

        editStation = (Button) findViewById(R.id.editStation);

        editStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(getApplicationContext(), "Vehicle Registered", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(StationProfile.this, VehicleOwnerProfile.class);
                startActivity(intent);
            }
        });
    }
}