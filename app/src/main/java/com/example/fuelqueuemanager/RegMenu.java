package com.example.fuelqueuemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegMenu extends AppCompatActivity {

    Button regVehicleBtn, regStationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_menu);


        regVehicleBtn = (Button) findViewById(R.id.regVehicleBtn);
        regStationBtn = (Button) findViewById(R.id.regStationBtn);


        regVehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RegMenu.this, RegisterVehicle.class);
                startActivity(intent);
            }
        });


     regStationBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(RegMenu.this, RegisterStation.class);
            startActivity(intent);
        }
    });
}
}