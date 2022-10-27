package com.example.fuelqueuemanager;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Model.Station;

public class StationOwnerMain extends AppCompatActivity {

    Button petrol_btn,diesel_btn,viewUserProfile;
    TextView petrolVehicles,dieselVehicles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.Theme_FuelQueueManager);
        setContentView(R.layout.activity_station_owner_main);

        petrol_btn = (Button) findViewById(R.id.petrol_btn);
        diesel_btn = (Button) findViewById(R.id.diesel_btn);
        viewUserProfile = (Button) findViewById(R.id.viewUserProfile);

        //getting the value of role from the splash screen
//        Intent result = getIntent();
//        String username = result.getStringExtra("user");

//        displayQueue(username);

        //on click method for view user profile button
        viewUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //move to the station profile
                Intent intent = new Intent(StationOwnerMain.this, StationProfile.class);
//                intent.putExtra("user",username);
                startActivity(intent);
            }
        });


        //on click method for petrol button
        petrol_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //set the btn value to 2
                Integer btn = 2;
                //move to the petrol page
                Intent intent = new Intent(StationOwnerMain.this, FuelAvailability.class);
                intent.putExtra("btn",btn);
                startActivity(intent);
            }
        });

        //on click method for diesel button
        diesel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //set the btn value to 1
                Integer btn = 1;
                //move to the petrol page
                Intent intent = new Intent(StationOwnerMain.this, FuelAvailability.class);
                intent.putExtra("btn",btn);
                startActivity(intent);

            }
        });

    }

    public void displayQueue(String username){


        String getUrl = "https://fuelmanagementsystem.azurewebsites.net/FuelStation/GetQueuebyStation?id="+username;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                getUrl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i(TAG, response.toString());

                        //Retrieve each response object and add it to the ArrayList
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                System.out.println("Successfully Retrieved");
                                JSONObject stationObject = response.getJSONObject(i);

                                Station station = new Station();
//                                station.setStationName(stationObject.getString("stationName"));
//                                station.setAddress(stationObject.getString("address"));

                                //getting the details
                                petrolVehicles.setText(stationObject.getString("pVehicles"));
                                dieselVehicles.setText(stationObject.getString("dVehicles"));


//                                 stationList.add(station);
//
//                                System.out.println(product.getProductName());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.getMessage());
                    }
                }

        );

        // Add the request to the RequestQueue
//        ApiService.getInstance(mContext).addToRequestQueue(jsonArrayRequest);
        //add to the queue
        requestQueue.add(jsonArrayRequest);


    }

}