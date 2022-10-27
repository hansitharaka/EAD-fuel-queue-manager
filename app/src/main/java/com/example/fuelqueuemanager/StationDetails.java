package com.example.fuelqueuemanager;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import java.util.List;

import Model.Station;

public class StationDetails extends AppCompatActivity {

    TextView stationName,stationAddress,waitingTime,petrolAvailability,dieselAvailability,petrolVehiclesNum,dieselVehiclesNum;
    Button checkin_btn,checkout_btn;
    EditText edtStationName,edtStationAddress,edtAvgTime;
    List<Station> stationList;
    String BaseURL = "https://fuelmanagementsystem.azurewebsites.net/";

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_details);

        stationName = findViewById(R.id.stationName);
        stationAddress = findViewById(R.id.stationAddress);
        waitingTime = findViewById(R.id.waitingTime);
        petrolAvailability = findViewById(R.id.petrolAvailability);
        dieselAvailability = findViewById(R.id.dieselAvailability);
        petrolVehiclesNum = findViewById(R.id.petrolVehiclesNum);
        dieselVehiclesNum = findViewById(R.id.dieselVehiclesNum);
        checkin_btn = findViewById(R.id.checkin_btn);
        checkout_btn = findViewById(R.id.checkout_btn);
        edtStationName = findViewById(R.id.edtStationName);
        edtStationAddress = findViewById(R.id.edtStationAddress);
        edtAvgTime = findViewById(R.id.edtAvgTime);
        checkin_btn = findViewById(R.id.checkin_btn);
        checkout_btn = findViewById(R.id.checkout_btn);

        String name = "Malabe Ceypetco Station";
        String address = "Malabe";
        String waiting = "2.30 hrs";
        String pAvailable = "YES";
        String dAvailable = "NO";
        String pVehicle = "12";
        String dVehicle = "0";

        edtStationName.setText(name);
        edtStationAddress.setText(address);
        edtAvgTime.setText(waiting);
        petrolAvailability.setText(pAvailable);
        dieselAvailability.setText(dAvailable);
        petrolVehiclesNum.setText(pVehicle);
        dieselVehiclesNum.setText(dVehicle);

//        Intent intent = getIntent();

        //calling function to get Station details
        getStationDetails(name);

        checkin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String status = "checking";
//                changeCheckinstatus(username,status);

            }
        });

        checkout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status = "checkOut";
//                changeCheckinstatus(username,status);
            }
        });

    }

    //method to get the station details
    public void getStationDetails(String username){

        String getUrl = "https://fuelmanagementsystem.azurewebsites.net/FuelStation/GetDetailsById?id="+username;
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

                                //getting the details
                                edtStationName.setText(stationObject.getString("stationName"));
                                edtStationAddress.setText(stationObject.getString("address"));
                                edtAvgTime.setText(stationObject.getString("avgWTime"));
                                petrolAvailability.setText(stationObject.getString("pAvailability"));
                                dieselAvailability.setText(stationObject.getString("dAvailability"));
                                petrolVehiclesNum.setText(stationObject.getString("pvNum"));
                                dieselVehiclesNum.setText(stationObject.getString("dvNum"));

                                //add to the array
                                 stationList.add(station);


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

    //method to change checking status
    public void changeCheckinstatus(String stationName, String status){


            //insert the data
            String postURL = BaseURL+"/FuelStation/changeCheckingStatus"+stationName;
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject postData = new JSONObject();

            try{
                postData.put("checkinstatus",status);


            }catch(Exception e){
                e.printStackTrace();
            }

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.PUT, postURL, postData, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println("----------------response------------------"+response);
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            error.printStackTrace();
                            System.out.println("response"+error.toString());

                        }
                    });


            //add to the requestQueue
            requestQueue.add(jsonObjectRequest);

            //Move to the vehicle owner main page
            Intent intent = new Intent(StationDetails.this, VehicleOwnerMain.class);
            startActivity(intent);





    }
}