package com.example.fuelqueuemanager;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.BatchUpdateException;
import java.util.List;

import Model.Owner;
import Model.Station;

public class VehicleOwnerProfile extends AppCompatActivity {

    Button editVehicleOwner;
    EditText vehicleOwnerName, vehicleNo, fuelType;
    List<Station> stationList;
    List<Owner> ownerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.Theme_FuelQueueManager);
        setContentView(R.layout.activity_vehicle_owner_profile);

        editVehicleOwner = (Button) findViewById(R.id.editVehicleOwner);
        vehicleOwnerName = (EditText) findViewById(R.id.vehicleOwnerName);
        vehicleNo = (EditText) findViewById(R.id.vehicleNo);
        fuelType = (EditText) findViewById(R.id.fuelType);

        //getting the value of role from the splash screen
        Intent result = getIntent();
        String username = result.getStringExtra("user");

        String name = "John Doe";
        String vNo = "KO 1270";
        String fType = "Petrol";

        vehicleOwnerName.setText(name);
        vehicleNo.setText(vNo);
        fuelType.setText(fType);

//        getProfile(username);


        editVehicleOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(getApplicationContext(), "Vehicle Registered", Toast.LENGTH_LONG).show();

                editProfile(username);


            }
        });

    }

    public void getProfile(String username) {

        String getUrl = "https://fuelmanagementsystem.azurewebsites.net/VehicleOwner/GetDetailsById?id=" + username;
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

                                Owner owner = new Owner();

                                //getting the details
                                vehicleOwnerName.setText(stationObject.getString("name"));
                                vehicleNo.setText(stationObject.getString("vehicleNo"));
                                fuelType.setText(stationObject.getString("fuelType"));


                                //add to the array
                                ownerList.add(owner);


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

        //add to the queue
        requestQueue.add(jsonArrayRequest);
    }

    //method to edit user profile
    public void editProfile(String username) {

        //assign the values
        String userName = vehicleOwnerName.getText().toString();
        String vNo = vehicleNo.getText().toString();
        String fuelT = fuelType.toString();


        //if any input fields are empty
        if (userName.equals("") || vNo.equals("") || fuelT.equals("")) {

            //display an error toast message
            Toast.makeText(getApplicationContext(), "Please Fill All the Fields", Toast.LENGTH_SHORT).show();

        } else {

            //insert the data
            String postURL = "https://fuelmanagementsystem.azurewebsites.net/VehicleOwner/VehicleOwner/UpdateVehicalOwner"+username;
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject postData = new JSONObject();

            try {

//                postData.put("ownerName", username);
                postData.put("vehicleNo", vNo);
                postData.put("fuelType", fuelT);

            } catch (Exception e) {
                e.printStackTrace();
            }

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.PUT, postURL, postData, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println("----------------response------------------" + response);
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            error.printStackTrace();
                            System.out.println("response" + error.toString());

                        }
                    });


            //add to the requestQueue
            requestQueue.add(jsonObjectRequest);

            //display a success toast
            Toast.makeText(getApplicationContext(), "Successfully Updated", Toast.LENGTH_SHORT).show();
            //move to the Login Page
            Intent intent = new Intent(VehicleOwnerProfile.this, VehicleOwnerProfile.class);
            startActivity(intent);


        }
    }
}