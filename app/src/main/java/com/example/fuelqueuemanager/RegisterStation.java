package com.example.fuelqueuemanager;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import DBhelper.dbhelper;

public class RegisterStation extends AppCompatActivity {

    Button regStationButton;
    EditText regStationName,regStationAddress,regStationEmail,regStationPassword,regStationRePassword;
    dbhelper mydb;
    String role;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_station);

        regStationButton = (Button) findViewById(R.id.regStationButton);
        regStationName = (EditText) findViewById(R.id.regStationName);
        regStationAddress = (EditText) findViewById(R.id.regStationAddress);
        regStationEmail = (EditText) findViewById(R.id.regStationEmail);
        regStationPassword = (EditText) findViewById(R.id.regStationPassword);
        regStationRePassword = (EditText) findViewById(R.id.regStationRePassword);
        textView2 = (TextView) findViewById(R.id.textView2);

        String text = "Already have an account? Login";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {

                Intent intent = new Intent(RegisterStation.this,MainActivity2.class);
                startActivity(intent);

            }

        };

        ss.setSpan(clickableSpan,25,30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView2.setText(ss);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());

        mydb = new dbhelper(this);

        regStationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = regStationName.getText().toString();
                String stationAddress = regStationAddress.getText().toString();
                String stationEmail = regStationEmail.getText().toString();
                String password = regStationPassword.getText().toString();
                String repassword = regStationRePassword.getText().toString();
                role = "1";

                if(username.equals("") || stationAddress.equals("") || stationEmail.equals("") || password.equals("") || repassword.equals("")){

                    Toast.makeText(getApplicationContext(), "Please Fill All the Fields", Toast.LENGTH_SHORT).show();
                }
                else {

                    if(password.equals(repassword)){

                        Boolean userCheckResult = mydb.checkUser(username);

                        if(userCheckResult == false){


                            Boolean regResult = mydb.insertData(username,password,role);

                            if( regResult == true){

                                Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterStation.this, MainActivity2.class);
                                startActivity(intent);
                            }
                            else {

                                Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();

                            }

                        }
                        else{
                            Toast.makeText(getApplicationContext(), "User Already Exists", Toast.LENGTH_SHORT).show();
                        }

//
                    }
                    else {

                        Toast.makeText(getApplicationContext(), "Your Password Does Not Match", Toast.LENGTH_SHORT).show();

                    }




                }


//                Toast.makeText(getApplicationContext(), "Station Registered", Toast.LENGTH_LONG).show();

//                Intent intent = new Intent(RegisterStation.this, RegisterVehicle.class);
//                startActivity(intent);
            }
        });
    }



}