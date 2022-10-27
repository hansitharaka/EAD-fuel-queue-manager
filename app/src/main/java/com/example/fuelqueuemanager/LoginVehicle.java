package com.example.fuelqueuemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import DBhelper.dbhelper;

public class LoginVehicle extends AppCompatActivity {

    Button login;
    EditText username, password;
    dbhelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_vehicle);

        login = (Button) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        mydb = new dbhelper(this);

        //login button onclick method
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //assign values
                String userName = username.getText().toString();
                String Password = password.getText().toString();

                //if any fields are empty
                if(userName.equals("") || Password.equals("")){

                    //display an error toast message
                    Toast.makeText(getApplicationContext(), "Please Enter Credentials", Toast.LENGTH_LONG).show();

                }
                else {

                    //check for the credentials
                    Boolean credResult = mydb.checkCredentialsVehicle(userName,Password);

                    //if credentials match
                    if (credResult == true){

                        //assign user role
//                        String UserRole = String.valueOf(mydb.getRole(userName));
//                        String user = mydb.getUsername(userName);

                        String user = mydb.getUsernameVehicle(userName);
                        System.out.println("Username"+userName);

                        Intent intent;

                        //if user role is a vehicle owner


                            //move to the vehicle owner main landing page
                            intent = new Intent(LoginVehicle.this, VehicleOwnerMain.class);
                            intent.putExtra("user",userName);
                            startActivity(intent);



                    }
                    else {

                        //display password does not match message
                        Toast.makeText(getApplicationContext(), "Your Password Does Not Match", Toast.LENGTH_LONG).show();

                    }

                }


//                Toast.makeText(getApplicationContext(), "Successfully Logged In", Toast.LENGTH_LONG).show();
//
//                Intent intent = new Intent(MainActivity2.this, StationProfile.class);
//                startActivity(intent);
            }
        });

    }
}