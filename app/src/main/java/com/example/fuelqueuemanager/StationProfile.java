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

import java.util.List;

import DBhelper.dbhelper;
import Model.Station;

public class StationProfile extends AppCompatActivity {


    Button editStation;
    EditText stationsName,stationsAddress,stationsEmail,stationsPassword;
    dbhelper mydb;
    String BaseURL = "https://fuelmanagementsystem.azurewebsites.net/";
    List<Station> stationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.Theme_FuelQueueManager);
        setContentView(R.layout.activity_station_profile);

        editStation = (Button) findViewById(R.id.editStation);
        stationsName = (EditText) findViewById(R.id.stationsName);
        stationsAddress = (EditText) findViewById(R.id.stationsAddress);
        stationsEmail = (EditText) findViewById(R.id.stationsEmail);

        String name = "Malabe Ceypetco Station";
        String address = "Malabe";
        String email = "malabeceypetcostation@gmail.com";

        stationsName.setText(name);
        stationsAddress.setText(address);
        stationsEmail.setText(email);



        mydb = new dbhelper(this);

        //call method to display data
        getDataFromRequest();

//        String id = "ab9aaf48-b68d-4aca-be8a-37c516907440";
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        String getUrl = "https://fuelmanagementsystem.azurewebsites.net/FuelStation/GetStationById";
//
//
//        // as our data is in json object format so we are using
//        // json object request to make data request from our url.
//        // in below line we are making a json object
//        // request and creating a new json object request.
//        // inside our json object request we are calling a
//        // method to get the data, "url" from where we are
//        // calling our data,"null" as we are not passing any data.
//        // later on we are calling response listener method
//        // to get the response from our API.
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, getUrl, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                // inside the on response method.
//                // we are hiding our progress bar.
////                loadingPB.setVisibility(View.GONE);
//
//                // in below line we are making our card
//                // view visible after we get all the data.
////                courseCV.setVisibility(View.VISIBLE);
//                try {
//                    // now we get our response from API in json object format.
//                    // in below line we are extracting a string with its key
//                    // value from our json object.
//                    // similarly we are extracting all the strings from our json object.
//                    String courseName = response.getString("stationName");
//                    String courseTracks = response.getString("address");
//
//                    // after extracting all the data we are
//                    // setting that data to all our views.
//                    stationsName.setText(courseName);
//                    stationsAddress.setText(courseTracks);
//
//                    // we are using picasso to load the image from url.
//
//                } catch (JSONException e) {
//                    // if we do not extract data from json object properly.
//                    // below line of code is use to handle json exception
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            // this is the error listener method which
//            // we will call if we get any error from API.
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // below line is use to display a toast message along with our error.
//                Toast.makeText(StationProfile.this, "Fail to get data..", Toast.LENGTH_SHORT).show();
//            }
//        });
//        // at last we are adding our json
//        // object request to our request
//        // queue to fetch all the json data.
//        requestQueue.add(jsonObjectRequest);


        //on click method for the editstation button
        editStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(getApplicationContext(), "Vehicle Registered", Toast.LENGTH_LONG).show();

//                Intent intent = new Intent(StationProfile.this, VehicleOwnerProfile.class);
//                startActivity(intent);
                //calling the submitform method
                submitForm(view);

            }
        });
    }

    //submit form method to edit details
    public void submitForm(View view){

        String id = "3fa85f64-5717-4562-b3fc-2c963f66afa6";
        String sName = stationsName.getText().toString();
        String sAddress = stationsAddress.getText().toString();
        String sEmail = stationsEmail.getText().toString();

        //if there is any empty fields
        if(sName.equals("") || sAddress.equals("") || sEmail.equals("") ){

            //display an error message
            Toast.makeText(getApplicationContext(), "Please Don't Leave Any Fields Empty", Toast.LENGTH_SHORT).show();
        }
        else {

                    String postURL = BaseURL+"FuelStation/UpdateStation/";
                    RequestQueue requestQueue = Volley.newRequestQueue(this);
                    JSONObject postData = new JSONObject();

                    try{

                        postData.put("stationName",sName);
                        postData.put("address",sAddress);
                        postData.put("id",id);

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


                    //add to the request queue
                    requestQueue.add(jsonObjectRequest);
                    //move to the station profile page
                    Intent intent = new Intent(StationProfile.this, StationProfile.class);
                    startActivity(intent);


        }

    }


    //method to get station details from the db
    public void getDataFromRequest() {


        String id = "ab9aaf48-b68d-4aca-be8a-37c516907440";
        String getUrl = "https://fuelmanagementsystem.azurewebsites.net/FuelStation/GetStationById?id="+id;
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

                                stationsEmail.setText(stationObject.getString("stationName"));
                                stationsAddress.setText(stationObject.getString("address"));

                                stationList.add(station);
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

//    public void getData(){
//        String username = "gr@gmail.com";
//        Cursor result = mydb.getUserDetails(username);
//
//        StringBuffer buffer = new StringBuffer();
//
//
//        stationsName.setText(result.getString(0));
//        stationsPassword.setText(result.getString(1));
//
//    }


}