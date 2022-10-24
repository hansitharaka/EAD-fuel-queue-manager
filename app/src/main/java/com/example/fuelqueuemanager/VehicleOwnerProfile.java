package com.example.fuelqueuemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.BatchUpdateException;

public class VehicleOwnerProfile extends AppCompatActivity {

    Button editVehicleOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.Theme_FuelQueueManager);
        setContentView(R.layout.activity_vehicle_owner_profile);

        editVehicleOwner = (Button) findViewById(R.id.editVehicleOwner);

        editVehicleOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(getApplicationContext(), "Vehicle Registered", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(VehicleOwnerProfile.this, SplashScreen.class);
                startActivity(intent);
            }
        });
    }
}