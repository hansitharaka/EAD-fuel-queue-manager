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

public class RegisterVehicle extends AppCompatActivity {

    Button regVehicleButton;
    EditText regVehicleOwnerName,regVehicleNo,regVOwnerEmail,regVOwnerPassword,regVOwnerRePassword;
    String role;
    dbhelper mydb;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_vehicle);

        regVehicleButton = (Button) findViewById(R.id.regVehicleButton);
        regVehicleOwnerName = (EditText) findViewById(R.id.regVehicleOwnerName);
        regVehicleNo = (EditText) findViewById(R.id.regVehicleNo);
        regVOwnerEmail = (EditText) findViewById(R.id.regVOwnerEmail);
        regVOwnerPassword = (EditText) findViewById(R.id.regVOwnerPassword);
        regVOwnerRePassword = (EditText) findViewById(R.id.regVOwnerRePassword);
        textView2 = (TextView) findViewById(R.id.textView2);

        String text = "Already have an account? Login";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {

                Intent intent = new Intent(RegisterVehicle.this,MainActivity2.class);
                startActivity(intent);

            }

        };

        ss.setSpan(clickableSpan,25,30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView2.setText(ss);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());


        mydb = new dbhelper(this);

        regVehicleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(getApplicationContext(), "Vehicle Registered", Toast.LENGTH_LONG).show();

                String username = regVehicleOwnerName.getText().toString();
                String vehicleNo = regVehicleNo.getText().toString();
                String ownerEmail = regVOwnerEmail.getText().toString();
                String password = regVOwnerPassword.getText().toString();
                String repassword = regVOwnerRePassword.getText().toString();
                role = "0";

                if(username.equals("") || vehicleNo.equals("") || ownerEmail.equals("") || password.equals("") || repassword.equals("")){

                    Toast.makeText(getApplicationContext(), "Please Fill All the Fields", Toast.LENGTH_SHORT).show();
                }
                else {

                    if(password.equals(repassword)){

                        Boolean userCheckResult = mydb.checkUser(username);

                        if(userCheckResult == false){


                            Boolean regResult = mydb.insertData(username,password,role);

                            if( regResult == true){

                                Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterVehicle.this, MainActivity2.class);
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

//                Intent intent = new Intent(RegisterVehicle.this, MainActivity2.class);
//                startActivity(intent);
            }
        });
    }

}