package com.example.mahmo.retrofit.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mahmo.retrofit.R;

public class Choose extends AppCompatActivity {
    private Button employees ,list ,logo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        employees = (Button)findViewById(R.id.employee);
        list = (Button)findViewById(R.id.auth) ;
        logo = (Button)findViewById(R.id.logobuilder) ;

        //add ,delete ,update employees in the Database
        employees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Done
                Intent i = new Intent(Choose.this ,MainActivity.class);
                startActivity(i);
            }
        });
        //display a list of users
        //request a authorize method in server to get
        //send token when request method
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Choose.this,ListAuth.class);
                startActivity(i);
            }
        });
        //logo builder app , send files to the server
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Choose.this,UploadPhoto.class);
                startActivity(i);
            }
        });

    }
}
